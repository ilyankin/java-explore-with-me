package ru.practicum.ewm.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.event.client.EventClientAdmin;
import ru.practicum.ewm.event.dto.AdminUpdateEventRequest;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Set;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/events")
public class EventControllerAdmin {
    private final EventClientAdmin eventClientAdmin;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(required = false) Set<Long> users,
                                         @RequestParam(required = false) Set<String> states,
                                         @RequestParam(required = false) Set<Long> categories,
                                         @RequestParam(required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String rangeStart,
                                         @RequestParam(required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String rangeEnd,
                                         @RequestParam(required = false, defaultValue = "0") Integer from,
                                         @RequestParam(required = false, defaultValue = "10") Integer size) {
        return eventClientAdmin.getAllEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PutMapping("{eventId}")
    public ResponseEntity<Object> update(@PathVariable @Positive Long eventId,
                                         @RequestBody @Valid AdminUpdateEventRequest updateEventRequest) {
        return eventClientAdmin.updateEvent(eventId, updateEventRequest);
    }

    @PatchMapping("{eventId}/publish")
    public ResponseEntity<Object> publish(@PathVariable @Positive Long eventId) {
        return eventClientAdmin.publishEvent(eventId);
    }

    @PatchMapping("{eventId}/reject")
    public ResponseEntity<Object> reject(@PathVariable @Positive Long eventId) {
        return eventClientAdmin.rejectEvent(eventId);
    }
}
