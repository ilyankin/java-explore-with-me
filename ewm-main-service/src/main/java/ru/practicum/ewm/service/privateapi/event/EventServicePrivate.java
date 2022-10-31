package ru.practicum.ewm.service.privateapi.event;

import ru.practicum.ewm.models.dtos.event.EventFullDto;
import ru.practicum.ewm.models.dtos.event.NewEventDto;
import ru.practicum.ewm.models.dtos.event.UpdateEventRequest;

import java.util.Collection;

public interface EventServicePrivate {
    Collection<EventFullDto> privateGetAllEvents(long userId, int from, int size);

    EventFullDto privateGetEvent(long userId, long eventId);

    EventFullDto privateUpdateEvent(long userId, UpdateEventRequest updateEventDto);

    EventFullDto privateCreateEvent(long userId, NewEventDto eventDto);

    EventFullDto privateCancelEvent(long userId, long eventId);
}
