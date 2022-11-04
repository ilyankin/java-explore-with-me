package ru.practicum.ewm.exceptions.event;

import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;

public class EventTryRejectPublishedEventException extends ConditionsAreNotMetException {
    public EventTryRejectPublishedEventException() {
        super("Unable reject already published event.");
    }
}
