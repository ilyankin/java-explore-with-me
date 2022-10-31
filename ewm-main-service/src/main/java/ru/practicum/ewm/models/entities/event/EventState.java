package ru.practicum.ewm.models.entities.event;

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
