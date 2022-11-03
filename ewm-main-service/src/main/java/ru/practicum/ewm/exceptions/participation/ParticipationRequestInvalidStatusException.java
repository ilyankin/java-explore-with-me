package ru.practicum.ewm.exceptions.participation;

import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;
import ru.practicum.ewm.models.entities.participation.PRStatus;

public class ParticipationRequestInvalidStatusException extends ConditionsAreNotMetException {
    public ParticipationRequestInvalidStatusException(String action, PRStatus status) {
        super("Unable " + action + " this participation request because it is in the "
                + status.getString() + " status");
    }
}
