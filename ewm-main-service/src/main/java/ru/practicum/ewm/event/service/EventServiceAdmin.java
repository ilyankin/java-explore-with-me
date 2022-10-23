package ru.practicum.ewm.event.service;

import ru.practicum.ewm.event.controller.params.AdminEventFilterParams;
import ru.practicum.ewm.event.model.dto.AdminUpdateEventRequest;
import ru.practicum.ewm.event.model.dto.EventFullDto;

import java.util.Collection;

public interface EventServiceAdmin {
    Collection<EventFullDto> getAllEventsByAdminParams(AdminEventFilterParams adminEventFilterParams);

    EventFullDto adminUpdateEvent(long eventId, AdminUpdateEventRequest eventUpdateDto);

    EventFullDto publishEvent(long eventId);

    EventFullDto rejectEvent(long eventId);
}
