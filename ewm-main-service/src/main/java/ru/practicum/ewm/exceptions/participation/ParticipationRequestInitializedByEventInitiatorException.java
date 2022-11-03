package ru.practicum.ewm.exceptions.participation;

import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;

public class ParticipationRequestInitializedByEventInitiatorException extends ConditionsAreNotMetException {
    public ParticipationRequestInitializedByEventInitiatorException(String action) {
        super("The event initiator cannot " + action + " the participation request to his own event.");
    }
}
