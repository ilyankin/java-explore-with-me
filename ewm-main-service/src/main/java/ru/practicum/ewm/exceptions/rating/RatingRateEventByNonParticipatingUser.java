package ru.practicum.ewm.exceptions.rating;

import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;

public class RatingRateEventByNonParticipatingUser extends ConditionsAreNotMetException {
    public RatingRateEventByNonParticipatingUser() {
        super("The user without confirmed participation request cannot rate this event");
    }
}
