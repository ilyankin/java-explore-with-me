package ru.practicum.ewm.exceptions.participation;

import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;

public class ParticipationRequestLimitReachedException extends ConditionsAreNotMetException {
    public ParticipationRequestLimitReachedException() {
        super("The number of participation requests has reached the limit.");
    }
}
