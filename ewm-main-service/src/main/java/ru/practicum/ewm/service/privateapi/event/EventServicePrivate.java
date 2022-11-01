package ru.practicum.ewm.service.privateapi.event;

import ru.practicum.ewm.models.dtos.event.EventFullDto;
import ru.practicum.ewm.models.dtos.event.NewEventDto;
import ru.practicum.ewm.models.dtos.event.UpdateEventRequest;

import java.util.Collection;

/**
 * The service contains methods for implementing the event's private API of the application.
 *
 * @author Izenkyt
 */
public interface EventServicePrivate {

    /**
     * Gets collection of events by the initiator (event participant).
     *
     * @param userId - initiator identifier
     * @param from   - number of events that need to be skipped to form the returned collection of events
     * @param size   - number of events in the returned collection of events
     * @return collection of {@link EventFullDto} - full event dto
     */

    Collection<EventFullDto> getAllEvents(long userId, int from, int size);

    /**
     * Gets an event by the initiator (event participant).
     *
     * @param userId  - initiator identifier
     * @param eventId - event identifier
     * @return {@link EventFullDto} - full event dto
     */

    EventFullDto getEvent(long userId, long eventId);

    /**
     * Updates an event by the initiator (event creator).
     *
     * @param userId         - initiator id
     * @param updateEventDto - dto for updating {@link ru.practicum.ewm.models.entities.compilation.Compilation}
     * @return {@link EventFullDto} - full event dto
     */
    EventFullDto updateEvent(long userId, UpdateEventRequest updateEventDto);


    /**
     * Creates a new event by the initiator (event creator).
     *
     * @param userId   - initiator identifier
     * @param eventDto - dto for creating {@link ru.practicum.ewm.models.entities.event.Event}
     * @return {@link EventFullDto} - full event dto
     */
    EventFullDto createEvent(long userId, NewEventDto eventDto);

    /**
     * Cancels an event by the initiator (event creator).
     *
     * @param userId  - initiator identifier
     * @param eventId - event identifier
     * @return {@link EventFullDto} - full event dto
     */
    EventFullDto cancelEvent(long userId, long eventId);
}
