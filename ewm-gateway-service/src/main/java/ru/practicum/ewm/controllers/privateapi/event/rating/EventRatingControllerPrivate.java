package ru.practicum.ewm.controllers.privateapi.event.rating;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.clients.privateapi.event.rating.EventRatingClientPrivate;

import javax.validation.constraints.Positive;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}")
public class EventRatingControllerPrivate {
    private final EventRatingClientPrivate eventRatingClient;

    @PatchMapping("/like")
    public void like(@PathVariable @Positive Long userId, @RequestParam @Positive Long eventId) {
        eventRatingClient.like(userId, eventId);
    }

    @PatchMapping("/dislike")
    public void dislike(@PathVariable @Positive Long userId, @RequestParam @Positive Long eventId) {
        eventRatingClient.dislike(userId, eventId);
    }
}
