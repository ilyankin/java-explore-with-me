package ru.practicum.ewm.models.entities.event;

/**
 * The entity representing a state of event. Only admins can change event states.
 *
 * @author Izenkyt
 */
public enum EventState {
    /**
     * Default state for newly created event.
     */
    PENDING("pending"),
    /**
     * State describing a published event.
     * Canceled event cannot be published.
     */
    PUBLISHED("published"),
    /**
     * State describing a canceled event.
     * Published event cannot be canceled.
     */
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
