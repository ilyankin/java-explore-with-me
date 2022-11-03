package ru.practicum.ewm.models.entities.participation;

/**
 * The entity representing a status of participation
 *
 * @author Izenkyt
 */
public enum PRStatus {
    /**
     * Default status for newly created participation requests
     */
    PENDING("pending"),
    CONFIRMED("confirmed"),
    REJECTED("rejected"),
    CANCELED("canceled");

    private final String string;

    PRStatus(String value) {
        this.string = value;
    }

    public String getString() {
        return string;
    }

    public boolean isConfirmed() {
        return this == CONFIRMED;
    }

    public boolean isRejected() {
        return this == REJECTED;
    }

    public boolean isCanceled() {
        return this == CANCELED;
    }
}
