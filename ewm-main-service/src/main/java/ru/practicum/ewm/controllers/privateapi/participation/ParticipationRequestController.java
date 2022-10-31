package ru.practicum.ewm.controllers.privateapi.participation;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.models.dtos.participation.ParticipationRequestDto;
import ru.practicum.ewm.service.privateapi.participation.ParticipationService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}")
public class ParticipationRequestController {
    private final ParticipationService participationService;

    @GetMapping("/requests")
    public Collection<ParticipationRequestDto> get(@PathVariable long userId) {
        return participationService.getParticipationRequests(userId);
    }

    @PostMapping("/requests")
    public ParticipationRequestDto create(@PathVariable long userId, @RequestParam("eventId") long eventId) {
        return participationService.createParticipationRequest(userId, eventId);
    }

    @PatchMapping("/requests/{requestId}/cancel")
    public ParticipationRequestDto cancel(@PathVariable long userId, @PathVariable long requestId) {
        return participationService.cancelParticipationRequest(userId, requestId);
    }
}
