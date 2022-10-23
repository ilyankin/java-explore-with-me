package ru.practicum.ewm.other.exception;

public class EntityNotFoundException extends RuntimeException {
    public <T> EntityNotFoundException(String entity, String fieldName, T fieldValue) {
        super(String.format("The %s not found %s=%s.", entity, fieldName, fieldValue));
    }
}
