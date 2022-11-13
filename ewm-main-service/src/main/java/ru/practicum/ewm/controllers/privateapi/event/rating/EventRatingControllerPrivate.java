package ru.practicum.ewm.controllers.privateapi.event.rating;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;
import ru.practicum.ewm.service.privateapi.rating.EventRatingServicePrivate;

/**
 * The controller processes requests from rating's private API of the application.
 *
 * @author Izenkyt
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}")
public class EventRatingControllerPrivate {
    private final EventRatingServicePrivate ratingService;

    @PatchMapping("/like")
    public void like(@PathVariable long userId, @RequestParam long eventId) throws ConditionsAreNotMetException {
        ratingService.likeEvent(userId, eventId);
    }

    @PatchMapping("/dislike")
    public void dislike(@PathVariable long userId, @RequestParam long eventId) throws ConditionsAreNotMetException {
        ratingService.dislikeEvent(userId, eventId);
    }
}
