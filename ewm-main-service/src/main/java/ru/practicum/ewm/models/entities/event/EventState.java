package ru.practicum.ewm.models.entities.event;

/**
 * The entity representing a state of event
 *
 * @author Izenkyt
 */
public enum EventState {
    PENDING("pending"),
    PUBLISHED("published"),
    CANCELED("canceled");

    private final String string;

    EventState(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public boolean isPending() {
        return this == PENDING;
    }

    public boolean isPublished() {
        return this == PUBLISHED;
    }

    public boolean isCanceled() {
        return this == CANCELED;
    }
}
