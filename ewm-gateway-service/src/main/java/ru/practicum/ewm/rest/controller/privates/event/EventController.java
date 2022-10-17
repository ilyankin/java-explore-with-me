package ru.practicum.ewm.rest.controller.privates.event;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.event.NewEventDto;
import ru.practicum.ewm.dto.request.UpdateEventRequest;
import ru.practicum.ewm.rest.client.privates.event.EventClient;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/events")
public class EventController {
    private final EventClient eventClient;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @PathVariable @Positive Long userId,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return eventClient.getEvents(userId, from, size);
    }

    @GetMapping("{eventId}")
    public ResponseEntity<Object> get(@PathVariable @Positive Long userId,
                                      @PathVariable @Positive Long eventId) {
        return eventClient.getEvent(userId, eventId);
    }

    @PatchMapping
    public ResponseEntity<Object> update(@PathVariable @Positive Long userId,
                                         @RequestBody @Valid UpdateEventRequest eventRequest) {
        return eventClient.updateEvent(userId, eventRequest);
    }

    @PostMapping
    public ResponseEntity<Object> create(@PathVariable @Positive Long userId,
                                         @RequestBody NewEventDto event) {
        return eventClient.createEvent(userId, event);
    }

    @PatchMapping("{eventId}")
    public ResponseEntity<Object> cancel(@PathVariable @Positive Long userId,
                                         @PathVariable @Positive Long eventId) {
        return eventClient.cancelEvent(userId, eventId);
    }

    @GetMapping("{eventId}/requests")
    public ResponseEntity<Object> getEventRequests(@PathVariable @Positive Long userId,
                                                   @PathVariable @Positive Long eventId) {
        return eventClient.getEventRequests(userId, eventId);
    }

    @PatchMapping("{eventId}/requests/{reqId}/confirm")
    public ResponseEntity<Object> confirmEventRequest(@PathVariable @Positive Long userId,
                                                      @PathVariable @Positive Long eventId,
                                                      @PathVariable @Positive Long reqId) {
        return eventClient.confirmEventRequest(userId, eventId, reqId);
    }

    @PatchMapping("{eventId}/requests/{reqId}/reject")
    public ResponseEntity<Object> rejectEventRequest(@PathVariable @Positive Long userId,
                                                     @PathVariable @Positive Long eventId,
                                                     @PathVariable @Positive Long reqId) {
        return eventClient.rejectEventRequest(userId, eventId, reqId);
    }
}
