package ru.practicum.ewm.service.privateapi.rating;

/**
 * The service contains methods for implementing the rating's private API of the application.
 *
 * @author Izenkyt
 */
public interface EventRatingServicePrivate {
    /**
     * Likes an event by the initiator (event participant).
     *
     * @param userId  - initiator identifier
     * @param eventId - event identifier
     */
    void likeEvent(long userId, long eventId);

    /**
     * Dislike an event by the initiator (event participant).
     *
     * @param userId  - initiator identifier
     * @param eventId - event identifier
     */
    void dislikeEvent(long userId, long eventId);
}
