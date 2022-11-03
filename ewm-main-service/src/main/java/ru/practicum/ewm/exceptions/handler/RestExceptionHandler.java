package ru.practicum.ewm.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;
import ru.practicum.ewm.exceptions.EntityNotFoundException;

import java.rmi.server.ServerNotActiveException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    protected ApiError handleEntityNotFoundException(final EntityNotFoundException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason(e.getReason())
                .status(HttpStatus.NOT_FOUND.name())
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ApiError handleDataBaseException(final Exception e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Integrity constraint has been violated.")
                .status(HttpStatus.CONFLICT.name())
                .build();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ConditionsAreNotMetException.class)
    protected ApiError handleConditionsNotMetException(final ConditionsAreNotMetException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason(e.getReason())
                .status(HttpStatus.FORBIDDEN.name())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerNotActiveException.class)
    public ApiError handleServerNotActive(final Exception e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Server not active.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ApiError handleUnexpectedException(Throwable e) {
        return ApiError.builder()
                .message(e.getLocalizedMessage())
                .reason("Error occurred.")
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Value
    @Builder
    public static class ApiError {
        String message;
        String reason;
        String status;
        @Builder.Default
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp = LocalDateTime.now();
    }
}
