package ru.practicum.ewm.other.exception;

public class ConditionsAreNotMetException extends RuntimeException {
    public ConditionsAreNotMetException(String message) {
        super(message);
    }
}
