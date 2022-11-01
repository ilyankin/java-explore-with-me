package ru.practicum.ewm.service.adminapi.event;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.controllers.adminapi.event.params.AdminEventFilterParams;
import ru.practicum.ewm.event.model.QEvent;
import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;
import ru.practicum.ewm.getters.event.EventGetter;
import ru.practicum.ewm.mappers.event.EventMapper;
import ru.practicum.ewm.models.dtos.event.AdminUpdateEventRequest;
import ru.practicum.ewm.models.dtos.event.EventFullDto;
import ru.practicum.ewm.models.entities.event.EventState;
import ru.practicum.ewm.repositories.event.EventRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EventServiceAdminImpl implements EventServiceAdmin {
    private final EventRepository eventRepository;
    private final EventGetter eventGetter;
    private final EventMapper eventMapper;

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
}