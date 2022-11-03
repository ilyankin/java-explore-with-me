package ru.practicum.ewm.controllers.privateapi.participation;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.models.dtos.participation.ParticipationRequestDto;
import ru.practicum.ewm.service.privateapi.participation.ParticipationRequestService;

import java.util.Collection;

/**
 * The controller processes requests from participation request's private API of the application.
 *
 * @author Izenkyt
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}")
public class ParticipationRequestController {
    private final ParticipationRequestService participationRequestService;

    @GetMapping("/requests")
    public Collection<ParticipationRequestDto> get(@PathVariable long userId) {
        return participationRequestService.getParticipationRequests(userId);
    }

    @PostMapping("/requests")
    public ParticipationRequestDto create(@PathVariable long userId, @RequestParam("eventId") long eventId) {
        return participationRequestService.createParticipationRequest(userId, eventId);
    }

    @PatchMapping("/requests/{requestId}/cancel")
    public ParticipationRequestDto cancel(@PathVariable long userId, @PathVariable long requestId) {
        return participationRequestService.cancelParticipationRequest(userId, requestId);
    }
}
