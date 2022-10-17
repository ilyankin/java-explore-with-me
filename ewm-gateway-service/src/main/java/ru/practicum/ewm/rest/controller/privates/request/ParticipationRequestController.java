package ru.practicum.ewm.rest.controller.privates.request;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.rest.client.privates.request.ParticipationRequestClient;

import javax.validation.constraints.Positive;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}")
public class ParticipationRequestController {
    private final ParticipationRequestClient participationRequestClient;

    @GetMapping("/requests")
    public ResponseEntity<Object> get(@PathVariable @Positive Long userId) {
        return participationRequestClient.getParticipationRequest(userId);
    }

    @PostMapping("/requests")
    public ResponseEntity<Object> create(@PathVariable @Positive Long userId,
                                         @RequestParam @Positive Long eventId) {
        return participationRequestClient.createParticipationRequest(userId, eventId);
    }

    @PostMapping("/requests/{requestId}/cancel")
    public ResponseEntity<Object> cancel(@PathVariable @Positive Long userId, @PathVariable @Positive Long requestId) {
        return participationRequestClient.cancelParticipationRequest(userId, requestId);
    }
}
