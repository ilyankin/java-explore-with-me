package ru.practicum.ewm.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.ewm.constraint.NullOrNotBlank;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class UpdateEventRequest {
    @NullOrNotBlank
    @Length(min = 20, max = 2000)
    String annotation;
    @Positive
    @JsonProperty("category")
    Long categoryId;
    @NullOrNotBlank
    @Length(min = 20, max = 7000)
    String description;
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    @Positive
    long eventId;
    Boolean paid;
    @PositiveOrZero
    Integer participantLimit;
    @NullOrNotBlank
    @Length(min = 3, max = 120)
    String title;
}
