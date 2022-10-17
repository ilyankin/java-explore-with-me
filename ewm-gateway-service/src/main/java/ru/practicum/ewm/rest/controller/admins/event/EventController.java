package ru.practicum.ewm.rest.controller.admins.event;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.request.AdminUpdateEventRequest;
import ru.practicum.ewm.rest.client.admins.event.EventClient;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/events")
public class EventController {
    private final EventClient eventClient;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(required = false) List<Long> users,
                                         @RequestParam(required = false) List<String> states,
                                         @RequestParam(required = false) List<Long> categories,
                                         @RequestParam(required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String rangeStart,
                                         @RequestParam
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String rangeEnd,
                                         @RequestParam(required = false, defaultValue = "0") Integer from,
                                         @RequestParam(required = false, defaultValue = "10") Integer size) {
        return eventClient.getAllEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PutMapping("{eventId}")
    public ResponseEntity<Object> update(@PathVariable @Positive Long eventId,
                                         @RequestBody @Valid AdminUpdateEventRequest updateEventRequest) {
        return eventClient.updateEvent(eventId, updateEventRequest);
    }

    @PatchMapping("{eventId}/publish")
    public ResponseEntity<Object> publish(@PathVariable @Positive Long eventId) {
        return eventClient.publishEvent(eventId);
    }

    @PatchMapping("{eventId}/reject")
    public ResponseEntity<Object> reject(@PathVariable @Positive Long eventId) {
        return eventClient.rejectEvent(eventId);
    }
}
