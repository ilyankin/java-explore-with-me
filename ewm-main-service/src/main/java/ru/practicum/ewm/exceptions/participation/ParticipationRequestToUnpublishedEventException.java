package ru.practicum.ewm.exceptions.participation;

import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;

public class ParticipationRequestToUnpublishedEventException extends ConditionsAreNotMetException {
    public ParticipationRequestToUnpublishedEventException() {
        super("You cannot participate in an unpublished event.");
    }
}
