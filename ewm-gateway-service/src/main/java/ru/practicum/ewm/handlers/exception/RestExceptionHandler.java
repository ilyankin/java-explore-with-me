package ru.practicum.ewm.handlers.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.server.ServerNotActiveException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        val apiError = ApiError.builder()
                .message("Some entity field values seem to fail validation (see the errors field)")
                .reason("Validation error.")
                .status(HttpStatus.BAD_REQUEST.name())
                .build();
        apiError.addValidationErrors(e.getBindingResult().getFieldErrors());
        apiError.addValidationError(e.getBindingResult().getGlobalErrors());
        return apiError;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiError handleMethodArgumentNotValidException(final Exception e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Method not allowed.")
                .status(HttpStatus.METHOD_NOT_ALLOWED.name())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiError handleMissingServletRequestParameterException(final Exception e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Missing request parameter.")
                .status(HttpStatus.BAD_REQUEST.name())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiError handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("JSON entity failed to parse.")
                .status(HttpStatus.BAD_REQUEST.name())
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

    private interface BindingError {

    }

    @Value
    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private static class ApiError {
        @JsonProperty("errors")
        List<BindingError> bindingErrors = new ArrayList<>();
        String message;
        String reason;
        String status;
        @Builder.Default
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp = LocalDateTime.now();

        private void addBindingError(BindingError bindingError) {
            bindingErrors.add(bindingError);
        }

        private void addValidationError(String object, String field, Object rejectedValue, String message) {
            addBindingError(new ValidationError(object, field, rejectedValue, message));
        }

        private void addValidationError(FieldError fieldError) {
            this.addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(),
                    fieldError.getDefaultMessage());
        }

        public void addValidationErrors(List<FieldError> fieldErrors) {
            fieldErrors.forEach(this::addValidationError);
        }

        private void addValidationError(ObjectError objectError) {
            addBindingError(new ValidationError(objectError.getObjectName(), null, null,
                    objectError.getDefaultMessage()));
        }

        public void addValidationError(List<ObjectError> globalErrors) {
            globalErrors.forEach(this::addValidationError);
        }
    }

    @Value
    private static class ValidationError implements BindingError {
        String object;
        String field;
        Object rejectedValue;
        String message;
    }
}
