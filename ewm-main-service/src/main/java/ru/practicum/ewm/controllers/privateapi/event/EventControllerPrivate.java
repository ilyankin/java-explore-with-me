package ru.practicum.ewm.controllers.privateapi.event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.models.dtos.event.EventFullDto;
import ru.practicum.ewm.models.dtos.event.NewEventDto;
import ru.practicum.ewm.models.dtos.event.UpdateEventRequest;
import ru.practicum.ewm.models.dtos.participation.ParticipationRequestDto;
import ru.practicum.ewm.service.privateapi.event.EventServicePrivateImpl;

import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/events")
public class EventControllerPrivate {
    private final EventServicePrivateImpl eventService;

    @GetMapping
    public Collection<EventFullDto> getAll(@PathVariable long userId, @RequestParam int from, @RequestParam int size) {
        return eventService.getAllEvents(userId, from, size);
    }

    @GetMapping("{eventId}")
    public EventFullDto get(@PathVariable @Positive Long userId,
                            @PathVariable @Positive Long eventId) {
        return eventService.getEvent(userId, eventId);
    }

    @PatchMapping
    public EventFullDto update(@PathVariable long userId, @RequestBody UpdateEventRequest updateEventDto) {
        return eventService.updateEvent(userId, updateEventDto);
    }

    @PostMapping
    public EventFullDto create(@PathVariable long userId, @RequestBody NewEventDto eventDto) {
        return eventService.createEvent(userId, eventDto);
    }

    @PatchMapping("{eventId}")
    public EventFullDto cancel(@PathVariable long userId, @PathVariable long eventId) {
        return eventService.cancelEvent(userId, eventId);
    }

    @GetMapping("{eventId}/requests")
    public Collection<ParticipationRequestDto> getParticipationRequests(@PathVariable @Positive long userId,
                                                                        @PathVariable @Positive long eventId) {
        return eventService.getAllParticipationRequests(userId, eventId);
    }

    @PatchMapping("{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto confirmParticipationRequest(@PathVariable @Positive long userId,
                                                               @PathVariable @Positive long eventId,
                                                               @PathVariable @Positive long reqId) {
        return eventService.confirmParticipationRequest(userId, eventId, reqId);
    }

    @PatchMapping("{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectParticipationRequest(@PathVariable @Positive long userId,
                                                              @PathVariable @Positive long eventId,
                                                              @PathVariable @Positive long reqId) {
        return eventService.rejectParticipationRequest(userId, eventId, reqId);
    }
}
