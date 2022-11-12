package ru.practicum.ewm.service.publicapi.event;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.controllers.publicapi.event.params.PublicEventFilterParams;
import ru.practicum.ewm.controllers.publicapi.event.params.RequestMetaData;
import ru.practicum.ewm.controllers.publicapi.event.params.SortType;
import ru.practicum.ewm.getters.event.EventGetter;
import ru.practicum.ewm.mappers.event.EventMapper;
import ru.practicum.ewm.models.dtos.event.EventFullDto;
import ru.practicum.ewm.models.dtos.event.EventShortDto;
import ru.practicum.ewm.models.dtos.stats.EndpointHitDto;
import ru.practicum.ewm.models.entities.event.Event;
import ru.practicum.ewm.models.entities.event.EventState;
import ru.practicum.ewm.models.entities.event.QEvent;
import ru.practicum.ewm.repositories.event.EventRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventServicePublicImpl implements EventServicePublic {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final EventGetter eventGetter;
    private final StatsClient statsClient;

    @Override
    public Collection<EventShortDto> getAllEventsByPublicParams(PublicEventFilterParams publicEventFilterParams,
                                                                RequestMetaData requestMetaData) {

        val conditions = new BooleanBuilder();

        conditions.and(QEvent.event.state.eq(EventState.PUBLISHED));

        val text = publicEventFilterParams.getText();
        if (text != null) {
            conditions.andAnyOf(
                    QEvent.event.annotation.likeIgnoreCase("%" + text + "%"),
                    QEvent.event.description.likeIgnoreCase("%" + text + "%")
            );
        }

        val categories = publicEventFilterParams.getCategoryIds();
        if (categories != null && categories.length != 0) {
            conditions.and(QEvent.event.category.id.in(categories));
        }

        val paid = publicEventFilterParams.getPaid();
        if (paid != null) {
            conditions.and(QEvent.event.paid.eq(paid));
        }

        val rangeStart = publicEventFilterParams.getRangeStart();
        conditions.and(QEvent.event.eventDate.after(Objects.requireNonNullElseGet(rangeStart, LocalDateTime::now)));

        val rangeEnd = publicEventFilterParams.getRangeEnd();
        if (rangeEnd != null) {
            conditions.and(QEvent.event.eventDate.before(rangeEnd));
        }

        val onlyAvailable = publicEventFilterParams.isOnlyAvailable();
        if (onlyAvailable) {
            conditions.and(QEvent.event.participantLimit.lt(QEvent.event.confirmedRequests.size()));
        }

        val size = publicEventFilterParams.getSize();
        val sortType = publicEventFilterParams.getSortType();

        var page = PageRequest.of(publicEventFilterParams.getFrom() / size, size);
        if (SortType.EVENT_DATE.equals(sortType) || SortType.VIEWS.equals(sortType)) {
            page = PageRequest.of(publicEventFilterParams.getFrom() / size, size, toSort(sortType));
        }

        var events = eventRepository.findAll(conditions.getValue(), page).getContent();

        if (SortType.LIKES.equals(sortType)) {
            events = new ArrayList<>(events);
            events.sort(Collections.reverseOrder(Comparator.comparing(event -> event.getLikes().size())));
        }

        if (SortType.DISLIKES.equals(sortType)) {
            events = new ArrayList<>(events);
            events.sort(Collections.reverseOrder(Comparator.comparing(event -> event.getDislikes().size())));
        }

        for (Event event : events) {
            sendStatistics(new RequestMetaData(
                    requestMetaData.getRemoteAddress(), requestMetaData.getRequestURI() + "/" + event.getId()));
        }
        return eventMapper.toShortDto(events);
    }

    @Override
    public EventFullDto getEvent(long eventId, RequestMetaData requestMetaData) {
        val event = eventGetter.getOrThrow(eventId);
        sendStatistics(requestMetaData);
        return eventMapper.toDto(event);
    }

    private void sendStatistics(RequestMetaData requestMetaData) {
        statsClient.createStatistics(EndpointHitDto.builder()
                .ip(requestMetaData.getRemoteAddress())
                .uri(requestMetaData.getRequestURI())
                .build());
    }

    private Sort toSort(SortType sortType) {
        switch (sortType) {
            case EVENT_DATE:
                return Sort.by("eventDate").descending();
            case VIEWS:
                return Sort.by("views").descending();
            default:
                throw new IllegalArgumentException("Unknown sort type: " + sortType);
        }
    }
}
