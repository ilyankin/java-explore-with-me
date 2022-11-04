package ru.practicum.ewm.exceptions.event;

import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;

public class EventNotOnlyByInitiatorException extends ConditionsAreNotMetException {
    public EventNotOnlyByInitiatorException(long userId, long eventId) {
        super("The user id=" + userId + " isn't initiator of the event id=" + eventId);
    }
}
