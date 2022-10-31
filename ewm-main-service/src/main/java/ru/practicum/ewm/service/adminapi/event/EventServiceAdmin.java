package ru.practicum.ewm.service.adminapi.event;

import ru.practicum.ewm.controllers.adminapi.event.params.AdminEventFilterParams;
import ru.practicum.ewm.models.dtos.event.AdminUpdateEventRequest;
import ru.practicum.ewm.models.dtos.event.EventFullDto;

import java.util.Collection;

public interface EventServiceAdmin {
    Collection<EventFullDto> getAllEventsByAdminParams(AdminEventFilterParams adminEventFilterParams);

    EventFullDto adminUpdateEvent(long eventId, AdminUpdateEventRequest eventUpdateDto);

    EventFullDto publishEvent(long eventId);

    EventFullDto rejectEvent(long eventId);
}
