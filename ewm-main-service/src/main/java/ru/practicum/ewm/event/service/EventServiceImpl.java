package ru.practicum.ewm.event.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.category.getter.CategoryGetter;
import ru.practicum.ewm.event.controller.params.AdminEventFilterParams;
import ru.practicum.ewm.event.controller.params.PublicEventFilterParams;
import ru.practicum.ewm.event.controller.params.RequestMetaData;
import ru.practicum.ewm.event.controller.params.SortType;
import ru.practicum.ewm.event.getter.EventGetter;
import ru.practicum.ewm.event.mapper.EventMapper;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.event.model.EventState;
import ru.practicum.ewm.event.model.QEvent;
import ru.practicum.ewm.event.model.dto.*;
import ru.practicum.ewm.event.model.location.mapper.LocationMapper;
import ru.practicum.ewm.event.model.location.repository.LocationRepository;
import ru.practicum.ewm.event.repository.EventRepository;
import ru.practicum.ewm.other.exception.ConditionsAreNotMetException;
import ru.practicum.ewm.participation.dto.ParticipationRequestDto;
import ru.practicum.ewm.participation.getter.ParticipationRequestGetter;
import ru.practicum.ewm.participation.mapper.ParticipationRequestMapper;
import ru.practicum.ewm.participation.model.PRStatus;
import ru.practicum.ewm.participation.repository.ParticipationRequestRepository;
import ru.practicum.ewm.stats.StatsClient;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.ewm.user.getter.UserGetter;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final ParticipationRequestRepository prRepository;
    private final EventGetter eventGetter;
    private final UserGetter userGetter;
    private final CategoryGetter categoryGetter;
    private final ParticipationRequestGetter prGetter;
    private final EventMapper eventMapper;
    private final LocationMapper locationMapper;
    private final ParticipationRequestMapper prMapper;

    private final StatsClient statsClient;

    @Override
    public Collection<EventFullDto> getAllEventsByAdminParams(AdminEventFilterParams params) {
        val conditions = new BooleanBuilder();

        val users = params.getUserIds();
        if (users != null && users.length != 0) {
            conditions.and(QEvent.event.initiator.id.in(users));
        }

        val states = params.getStates();
        if (states != null && states.length != 0) {
            conditions.and(QEvent.event.state.in(states));
        }

        val categories = params.getCategoryIds();
        if (categories != null && categories.length != 0) {
            conditions.and(QEvent.event.category.id.in(categories));
        }

        val rangeStart = params.getRangeStart();
        conditions.and(QEvent.event.eventDate.after(Objects.requireNonNullElseGet(rangeStart, LocalDateTime::now)));

        val rangeEnd = params.getRangeEnd();
        if (rangeEnd != null) {
            conditions.and(QEvent.event.eventDate.before(rangeEnd));
        }

        val size = params.getSize();
        val page = PageRequest.of(params.getFrom() / size, size);
        return eventMapper.toDto(eventRepository.findAll(conditions, page).getContent());
    }

    @Override
    public EventFullDto adminUpdateEvent(long eventId, AdminUpdateEventRequest eventUpdateDto) {
        val event = eventMapper.update(eventGetter.getOrThrow(eventId), eventUpdateDto);
        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventFullDto publishEvent(long eventId) {
        val event = eventGetter.getOrThrow(eventId);

        val state = event.getState();
        if (state.isPublished())
            throw new ConditionsAreNotMetException("The event with id=" + eventId + " has already been published.");

        if (!state.isPending())
            throw new ConditionsAreNotMetException("Only pending events can be published.");

        if (event.getEventDate().isBefore(LocalDateTime.now().plusHours(1)))
            throw new ConditionsAreNotMetException("The event date must be no earlier than one hour from the publication.");

        event.setPublishedOn(LocalDateTime.now());
        event.setState(EventState.PUBLISHED);
        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventFullDto rejectEvent(long eventId) {
        val event = eventGetter.getOrThrow(eventId);

        if (event.getState().isPublished())
            throw new ConditionsAreNotMetException("The event should not be published.");

        event.setState(EventState.CANCELED);
        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public Collection<EventFullDto> privateGetAllEvents(long userId, int from, int size) {
        val page = PageRequest.of(from / size, size, toSort(SortType.EVENT_DATE));
        return eventMapper.toDto(eventRepository.findAll(QEvent.event.initiator.id.eq(userId), page).getContent());
    }

    @Override
    public EventFullDto privateGetEvent(long userId, long eventId) {
        val event = eventGetter.getOrThrow(eventId);

        verifyInitiator(userId, event);

        return eventMapper.toDto(event);
    }

    @Override
    public EventFullDto privateUpdateEvent(long userId, UpdateEventRequest eventUpdateDto) {
        userGetter.getOrThrow(userId);

        val event = eventGetter.getOrThrow(eventUpdateDto.getEventId());
        val state = event.getState();

        if (state.isPublished())
            throw new ConditionsAreNotMetException("The event with id=" + event.getId() + " has already been published.");

        if (state.isCanceled())
            event.setState(EventState.PENDING);

        return eventMapper.toDto(eventMapper.update(event, eventUpdateDto));
    }

    @Override
    public EventFullDto privateCreateEvent(long userId, NewEventDto eventDto) {
        val user = userGetter.getOrThrow(userId);
        val category = categoryGetter.getOrThrow(eventDto.getCategoryId());
        val location = locationRepository.save(locationMapper.to(eventDto.getLocation()));
        val event = eventMapper.create(new Event(), eventDto, user, category, location);
        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventFullDto privateCancelEvent(long userId, long eventId) {
        val event = eventGetter.getOrThrow(eventId);

        if (!event.getState().isPending())
            throw new ConditionsAreNotMetException("The event id=" + eventId + " already published or canceled");

        verifyInitiator(userId, event);

        event.setState(EventState.CANCELED);
        return eventMapper.toDto(event);
    }

    @Override
    public Collection<EventShortDto> getAllEventsByPublicParams(PublicEventFilterParams params,
                                                                RequestMetaData requestMetaData) {

        val conditions = new BooleanBuilder();

        conditions.and(QEvent.event.state.eq(EventState.PUBLISHED));

        val text = params.getText();
        if (text != null) {
            conditions.andAnyOf(
                    QEvent.event.annotation.likeIgnoreCase("%" + text + "%"),
                    QEvent.event.description.likeIgnoreCase("%" + text + "%")
            );
        }

        val categories = params.getCategoryIds();
        if (categories != null && categories.length != 0) {
            conditions.and(QEvent.event.category.id.in(categories));
        }

        val paid = params.getPaid();
        if (paid != null) {
            conditions.and(QEvent.event.paid.eq(paid));
        }

        val rangeStart = params.getRangeStart();
        conditions.and(QEvent.event.eventDate.after(Objects.requireNonNullElseGet(rangeStart, LocalDateTime::now)));

        val rangeEnd = params.getRangeEnd();
        if (rangeEnd != null) {
            conditions.and(QEvent.event.eventDate.before(rangeEnd));
        }

        val onlyAvailable = params.isOnlyAvailable();
        if (onlyAvailable) {
            conditions.and(QEvent.event.participantLimit.lt(QEvent.event.confirmedRequests.size()));
        }

        val size = params.getSize();
        val page = PageRequest.of(params.getFrom() / size, size, toSort(params.getSortType()));
        val events = eventRepository.findAll(conditions.getValue(), page).getContent();
        for (Event event : events) {
            sendStatistics(new RequestMetaData(
                    requestMetaData.getRemoteAddress(), requestMetaData.getRequestURI() + "/"  + event.getId()));
        }
        return eventMapper.toShortDto(events);
    }

    @Override
    public EventFullDto getEvent(long eventId, RequestMetaData requestMetaData) {
        val event = eventGetter.getOrThrow(eventId);
        sendStatistics(requestMetaData);
        return eventMapper.toDto(event);
    }


    @Override
    public Collection<ParticipationRequestDto> getAllParticipationRequests(long userId, long eventId) {
        val event = eventGetter.getOrThrow(eventId);

        verifyInitiator(userId, event);

        return prMapper.toDto(prRepository.findAllByEventId(eventId));
    }

    @Override
    @Transactional
    public ParticipationRequestDto confirmParticipationRequest(long userId, long eventId, long reqId) {
        val request = prGetter.getOrThrow(reqId);
        val event = eventGetter.getOrThrow(eventId);

        if (request.getStatus().isConfirmed()) {
            throw new ConditionsAreNotMetException("The participant request id=" + reqId
                    + " has been already confirmed");
        }

        verifyInitiator(userId, event);

        val participantLimit = event.getParticipantLimit();
        if (participantLimit != 0 && participantLimit == event.getConfirmedRequests().size()) {
            throw new ConditionsAreNotMetException("Participant limit is full");
        }

        request.setStatus(PRStatus.CONFIRMED);
        val requestDto = prMapper.toDto(prRepository.save(request));

        event.getConfirmedRequests().add(request);

        if (participantLimit == event.getConfirmedRequests().size()) {
            val requests = prRepository.findAllByEventId(eventId);
            for (var r : requests) r.setStatus(PRStatus.REJECTED);
            prRepository.saveAll(requests);
        }

        return requestDto;
    }

    @Override
    public ParticipationRequestDto rejectParticipationRequest(long userId, long eventId, long reqId) {
        val request = prGetter.getOrThrow(reqId);
        val event = eventGetter.getOrThrow(eventId);

        if (request.getStatus().isRejected()) {
            throw new ConditionsAreNotMetException("The participant request id=" + reqId
                    + " has been already rejected");
        }

        verifyInitiator(userId, event);

        request.setStatus(PRStatus.REJECTED);

        return prMapper.toDto(prRepository.save(request));
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

    private static void verifyInitiator(long userId, Event event) {
        if (userId != event.getInitiator().getId())
            throw new ConditionsAreNotMetException("The user id=" + userId
                    + " isn't initiator of the event id=" + event.getId());
    }
}
