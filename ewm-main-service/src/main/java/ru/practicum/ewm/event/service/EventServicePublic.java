package ru.practicum.ewm.event.service;

import ru.practicum.ewm.event.controller.params.PublicEventFilterParams;
import ru.practicum.ewm.event.controller.params.RequestMetaData;
import ru.practicum.ewm.event.model.dto.EventFullDto;
import ru.practicum.ewm.event.model.dto.EventShortDto;

import java.util.Collection;

public interface EventServicePublic {
    Collection<EventShortDto> getAllEventsByPublicParams(PublicEventFilterParams params, RequestMetaData requestMetaData);

    EventFullDto getEvent(long eventId, RequestMetaData requestMetaData);
}
