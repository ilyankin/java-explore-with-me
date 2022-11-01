package ru.practicum.ewm.controllers.adminapi.event;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.controllers.adminapi.event.params.AdminEventFilterParams;
import ru.practicum.ewm.models.dtos.event.AdminUpdateEventRequest;
import ru.practicum.ewm.models.dtos.event.EventFullDto;
import ru.practicum.ewm.models.entities.event.EventState;
import ru.practicum.ewm.service.adminapi.event.EventServiceAdmin;
import ru.practicum.ewm.util.UrlUtil;

import java.time.format.DateTimeFormatter;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class EventControllerAdmin {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final EventServiceAdmin eventService;

    @GetMapping("/events")
    public Collection<EventFullDto> getAll(@RequestParam(required = false) Long[] users,
                                           @RequestParam(required = false) EventState[] states,
                                           @RequestParam(required = false) Long[] categories,
                                           @RequestParam(required = false) String rangeStart,
                                           @RequestParam(required = false) String rangeEnd,
                                           @RequestParam int from, @RequestParam int size) {
        val dateTimes = UrlUtil.decodeLocalDateTime(formatter, rangeStart, rangeEnd);
        val rangeStartDateTime = dateTimes.get(0);
        val rangeEndDateTime = dateTimes.get(1);
        return eventService.getAllEventsByAdminParams(new AdminEventFilterParams(users, states, categories,
                rangeStartDateTime, rangeEndDateTime, from, size));
    }

    @PutMapping("/events/{eventId}")
    public EventFullDto update(@PathVariable long eventId, @RequestBody AdminUpdateEventRequest eventUpdateDto) {
        return eventService.updateEvent(eventId, eventUpdateDto);
    }

    @PatchMapping("/events/{eventId}/publish")
    public EventFullDto publish(@PathVariable long eventId) {
        return eventService.publishEvent(eventId);
    }

    @PatchMapping("/events/{eventId}/reject")
    public EventFullDto reject(@PathVariable long eventId) {
        return eventService.rejectEvent(eventId);
    }
}
