package ru.practicum.ewm.service.privateapi.rating;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.exceptions.rating.RatingRateEventByInitiator;
import ru.practicum.ewm.exceptions.rating.RatingRateEventByNonParticipatingUser;
import ru.practicum.ewm.getters.event.EventGetter;
import ru.practicum.ewm.getters.user.UserGetter;
import ru.practicum.ewm.models.entities.event.Event;
import ru.practicum.ewm.models.entities.event.rating.Dislike;
import ru.practicum.ewm.models.entities.event.rating.Like;
import ru.practicum.ewm.models.entities.user.User;
import ru.practicum.ewm.repositories.event.rating.DislikeRepository;
import ru.practicum.ewm.repositories.event.rating.LikeRepository;

import javax.transaction.Transactional;


@Service
@AllArgsConstructor
public class EventRatingServicePrivateImpl implements EventRatingServicePrivate {
    private final LikeRepository likeRepository;
    private final DislikeRepository dislikeRepository;
    private final EventGetter eventGetter;
    private final UserGetter userGetter;

    @Override
    @Transactional
    public void likeEvent(long userId, long eventId) {
        val event = eventGetter.getOrThrow(eventId);
        val liker = userGetter.getOrThrow(userId);
        validate(liker, event);

        val maybeLike = likeRepository.findById(new Like.PK(event, liker));
        val maybeDislike = dislikeRepository.findById(new Dislike.PK(event, liker));

        if (maybeLike.isEmpty() && maybeDislike.isEmpty()) {
            likeRepository.save(new Like(new Like.PK(event, liker)));
        } else if (maybeLike.isEmpty()) {
            val dislike = maybeDislike.get();
            dislikeRepository.delete(dislike);
            likeRepository.save(new Like(new Like.PK(event, liker)));
        } else {
            val like = maybeLike.get();
            likeRepository.delete(like);
        }
    }

    @Override
    @Transactional
    public void dislikeEvent(long userId, long eventId) {
        val event = eventGetter.getOrThrow(eventId);
        val disliker = userGetter.getOrThrow(userId);
        validate(disliker, event);

        val maybeLike = likeRepository.findById(new Like.PK(event, disliker));
        val maybeDislike = dislikeRepository.findById(new Dislike.PK(event, disliker));

        if (maybeLike.isEmpty() && maybeDislike.isEmpty()) {
            dislikeRepository.save(new Dislike(new Dislike.PK(event, disliker)));
        } else if (maybeDislike.isEmpty()) {
            val like = maybeLike.get();
            likeRepository.delete(like);
            dislikeRepository.save(new Dislike(new Dislike.PK(event, disliker)));
        } else {
            val dislike = maybeDislike.get();
            dislikeRepository.delete(dislike);
        }
    }

    private void validate(User user, Event event) {
        if (event.getInitiator().equals(user))
            throw new RatingRateEventByInitiator();

        if (!isParticipatedUser(event, user))
            throw new RatingRateEventByNonParticipatingUser();
    }

    private boolean isParticipatedUser(Event event, User user) {
        for (val request : event.getConfirmedRequests()) {
            if (user.equals(request.getRequester())) {
                return true;
            }
        }
        return false;
    }
}
