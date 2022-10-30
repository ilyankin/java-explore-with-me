package ru.practicum.ewm.event.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.event.client.EventClientPrivate;
import ru.practicum.ewm.event.dto.NewEventDto;
import ru.practicum.ewm.event.dto.UpdateEventRequest;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/events")
public class EventControllerPrivate {
    private final EventClientPrivate eventClientPrivate;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @PathVariable @Positive Long userId,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return eventClientPrivate.getAllEvents(userId, from, size);
    }

    @GetMapping("{eventId}")
    public ResponseEntity<Object> get(@PathVariable @Positive Long userId,
                                      @PathVariable @Positive Long eventId) {
        return eventClientPrivate.getEvent(userId, eventId);
    }

    @PatchMapping
    public ResponseEntity<Object> update(@PathVariable @Positive Long userId,
                                         @RequestBody @Valid UpdateEventRequest eventRequest) {
        return eventClientPrivate.updateEvent(userId, eventRequest);
    }

    @PostMapping
    public ResponseEntity<Object> create(@PathVariable @Positive Long userId,
                                         @RequestBody @Valid NewEventDto event) {
        return eventClientPrivate.createEvent(userId, event);
    }

    @PatchMapping("{eventId}")
    public ResponseEntity<Object> cancel(@PathVariable @Positive Long userId,
                                         @PathVariable @Positive Long eventId) {
        return eventClientPrivate.cancelEvent(userId, eventId);
    }

    @GetMapping("{eventId}/requests")
    public ResponseEntity<Object> getParticipationRequests(@PathVariable @Positive Long userId,
                                                           @PathVariable @Positive Long eventId) {
        return eventClientPrivate.getAllParticipationRequests(userId, eventId);
    }

    @PatchMapping("{eventId}/requests/{reqId}/confirm")
    public ResponseEntity<Object> confirmParticipationRequest(@PathVariable @Positive Long userId,
                                                              @PathVariable @Positive Long eventId,
                                                              @PathVariable @Positive Long reqId) {
        return eventClientPrivate.confirmParticipationRequest(userId, eventId, reqId);
    }

    @PatchMapping("{eventId}/requests/{reqId}/reject")
    public ResponseEntity<Object> rejectParticipationRequest(@PathVariable @Positive Long userId,
                                                             @PathVariable @Positive Long eventId,
                                                             @PathVariable @Positive Long reqId) {
        return eventClientPrivate.rejectParticipationRequest(userId, eventId, reqId);
    }
}
