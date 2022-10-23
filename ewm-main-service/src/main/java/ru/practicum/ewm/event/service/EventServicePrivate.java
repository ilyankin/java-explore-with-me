package ru.practicum.ewm.event.service;

import ru.practicum.ewm.event.model.dto.EventFullDto;
import ru.practicum.ewm.event.model.dto.UpdateEventRequest;
import ru.practicum.ewm.event.model.dto.NewEventDto;
import ru.practicum.ewm.participation.dto.ParticipationRequestDto;
import ru.practicum.ewm.participation.service.ParticipationServicePrivate;

import java.util.Collection;

public interface EventServicePrivate extends ParticipationServicePrivate {
    Collection<EventFullDto> privateGetAllEvents(long userId, int from, int size);
    EventFullDto privateGetEvent(long userId, long eventId);

    EventFullDto privateUpdateEvent(long userId, UpdateEventRequest updateEventDto);

    EventFullDto privateCreateEvent(long userId, NewEventDto eventDto);

    EventFullDto privateCancelEvent(long userId, long eventId);
}
