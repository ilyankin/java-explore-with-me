package ru.practicum.ewm.exceptions.event;

import ru.practicum.ewm.exceptions.EntityNotFoundException;

public class EventByIdNotFoundException extends EntityNotFoundException {
    public EventByIdNotFoundException(Long eventId) {
        super("event", "id", eventId);
    }
}
