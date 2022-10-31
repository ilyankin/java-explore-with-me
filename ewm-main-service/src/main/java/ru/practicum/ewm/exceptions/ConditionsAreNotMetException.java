package ru.practicum.ewm.exceptions;

public class ConditionsAreNotMetException extends RuntimeException {
    public ConditionsAreNotMetException(String message) {
        super(message);
    }
}
