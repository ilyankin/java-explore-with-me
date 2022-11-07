package ru.practicum.ewm.service.adminapi.event;

import ru.practicum.ewm.controllers.adminapi.event.params.AdminEventFilterParams;
import ru.practicum.ewm.models.dtos.event.AdminUpdateEventRequest;
import ru.practicum.ewm.models.dtos.event.EventFullDto;

import java.util.Collection;

/**
 * The service contains methods for implementing the event's admin API of the application.
 *
 * @author Izenkyt
 */
public interface EventServiceAdmin {

    /**
     * Gets a collection of events by admin filter params.
     *
     * @param adminEventFilterParams - set of possible filtering parameters for admin
     * @return collection of {@link EventFullDto} - full event dto
     * @see AdminEventFilterParams
     */
    Collection<EventFullDto> getAllEventsByAdminParams(AdminEventFilterParams adminEventFilterParams);

    /**
     * Updates an event by admin.
     *
     * @param eventId        - event identifier
     * @param eventUpdateDto - dto for updating {@link ru.practicum.ewm.models.entities.compilation.Compilation}
     * @return {@link EventFullDto} - full event dto
     */
    EventFullDto updateEvent(long eventId, AdminUpdateEventRequest eventUpdateDto);

    /**
     * Publishes an event (set {@code eventState=EventState.PUBLISHED}) by admin.
     *
     * @param eventId - event identifier
     * @return {@link EventFullDto} - full event dto
     * @see ru.practicum.ewm.models.entities.event.EventState
     */
    EventFullDto publishEvent(long eventId);

    /**
     * Rejects an event (set {@code eventState=EventState.CANCELED}) by admin.
     *
     * @param eventId - event identifier
     * @return {@link EventFullDto} - full event dto
     * @see ru.practicum.ewm.models.entities.event.EventState
     */
    EventFullDto rejectEvent(long eventId);
}
