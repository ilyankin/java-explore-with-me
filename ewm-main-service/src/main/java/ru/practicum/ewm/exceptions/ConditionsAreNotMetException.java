package ru.practicum.ewm.exceptions;

public class ConditionsAreNotMetException extends RuntimeException {
    public ConditionsAreNotMetException(String message) {
        super(message);
    }

    public String getReason() {
        return "For the requested operation the conditions are not met.";
    }
}
