package ru.practicum.ewm.exceptions.event;

import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;

public class EventRejectPublishedEventException extends ConditionsAreNotMetException {
    public EventRejectPublishedEventException() {
        super("Unable reject already published event.");
    }
}
