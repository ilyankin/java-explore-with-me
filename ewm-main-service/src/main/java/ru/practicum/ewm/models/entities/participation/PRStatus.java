package ru.practicum.ewm.models.entities.participation;

/**
 * The entity representing a status of participation.
 *
 * @author Izenkyt
 */
public enum PRStatus {
    /**
     * Default status for newly created participation requests.
     */
    PENDING("pending"),
    /**
     * Status marking confirmed a participation request.
     * Unable to confirm the participation request if a participation limit of the event has already been reached.
     */
    CONFIRMED("confirmed"),
    /**
     * Status marking rejected a participation request.
     * This status is applied when the event initiator rejects participation requests.
     */
    REJECTED("rejected"),
    /**
     * Status marking canceled a participation request.
     * This status is applied when the requester cancels own participation requests.
     */
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
