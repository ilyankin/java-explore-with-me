package ru.practicum.ewm.models.entities.event;

/**
 * The entity representing a state of event
 *
 * @author Izenkyt
 */
public enum EventState {
    PENDING,
    PUBLISHED,
    CANCELED;

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
