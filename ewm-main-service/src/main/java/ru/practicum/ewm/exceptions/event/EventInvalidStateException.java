package ru.practicum.ewm.exceptions.event;

import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;
import ru.practicum.ewm.models.entities.event.EventState;

public class EventInvalidStateException extends ConditionsAreNotMetException {
    public EventInvalidStateException(String action, EventState eventState) {
        super("Unable " + action + " this event because it is in the " + eventState.getString() + " state");
    }
}
