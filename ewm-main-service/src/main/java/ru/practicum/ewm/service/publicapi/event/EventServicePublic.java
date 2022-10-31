package ru.practicum.ewm.service.publicapi.event;

import ru.practicum.ewm.controllers.publicapi.event.params.PublicEventFilterParams;
import ru.practicum.ewm.controllers.publicapi.event.params.RequestMetaData;
import ru.practicum.ewm.models.dtos.event.EventFullDto;
import ru.practicum.ewm.models.dtos.event.EventShortDto;

import java.util.Collection;

public interface EventServicePublic {
    Collection<EventShortDto> getAllEventsByPublicParams(PublicEventFilterParams params, RequestMetaData requestMetaData);

    EventFullDto getEvent(long eventId, RequestMetaData requestMetaData);
}
