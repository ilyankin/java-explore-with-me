package ru.practicum.ewm.models.entities.participation;

/**
 * The entity representing status of  participation
 *
 * @author Izenkyt
 */
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
