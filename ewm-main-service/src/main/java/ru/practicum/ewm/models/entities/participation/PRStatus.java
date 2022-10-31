package ru.practicum.ewm.models.entities.participation;

public enum PRStatus {
    PENDING,
    CONFIRMED,
    REJECTED,
    CANCELED;

    public boolean isConfirmed() {
        return this == CONFIRMED;
    }

    public boolean isRejected() {
        return this == REJECTED;
    }
}
