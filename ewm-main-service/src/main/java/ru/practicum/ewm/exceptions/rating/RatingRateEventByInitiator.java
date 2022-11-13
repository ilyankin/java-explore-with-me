package ru.practicum.ewm.exceptions.rating;

import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;

public class RatingRateEventByInitiator extends ConditionsAreNotMetException {
    public RatingRateEventByInitiator() {
        super("The event initiator cannot rete its own events");
    }
}
