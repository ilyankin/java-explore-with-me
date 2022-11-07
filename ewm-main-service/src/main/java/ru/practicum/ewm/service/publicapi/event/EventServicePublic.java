package ru.practicum.ewm.service.publicapi.event;

import ru.practicum.ewm.controllers.publicapi.event.params.PublicEventFilterParams;
import ru.practicum.ewm.controllers.publicapi.event.params.RequestMetaData;
import ru.practicum.ewm.models.dtos.event.EventFullDto;
import ru.practicum.ewm.models.dtos.event.EventShortDto;

import java.util.Collection;

/**
 * The service contains methods for implementing the event's public API of the application.
 *
 * @author Izenkyt
 */
public interface EventServicePublic {

    /**
     * Gets a collection of events by public filter params.
     *
     * @param publicEventFilterParams - set of possible filtering parameters for all
     * @param requestMetaData         - request metadata for collecting statistics like a user ip and its requested endpoint
     * @return collection of {@link EventShortDto} - short event dto
     * @see PublicEventFilterParams
     */
    Collection<EventShortDto> getAllEventsByPublicParams(PublicEventFilterParams publicEventFilterParams,
                                                         RequestMetaData requestMetaData);

    /**
     * Gets an event.
     *
     * @param eventId         - event identifier
     * @param requestMetaData - request metadata for collecting statistics like a user ip and its requested endpoint
     * @return {@link EventShortDto} - short event dto
     */
    EventFullDto getEvent(long eventId, RequestMetaData requestMetaData);
}
