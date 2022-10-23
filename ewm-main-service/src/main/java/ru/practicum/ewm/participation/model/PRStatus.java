package ru.practicum.ewm.participation.model;

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
