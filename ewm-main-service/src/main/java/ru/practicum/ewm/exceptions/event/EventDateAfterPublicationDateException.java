package ru.practicum.ewm.exceptions.event;

import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;

public class EventDateAfterPublicationDateException extends ConditionsAreNotMetException {
    public EventDateAfterPublicationDateException(int hours, int minutes) {
        super(String.format("The event date must be %s hours and %s minutes after than publication date",
                hours, minutes));
    }
}
