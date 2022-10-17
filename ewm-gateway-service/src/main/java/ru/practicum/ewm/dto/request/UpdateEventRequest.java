package ru.practicum.ewm.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class UpdateEventRequest {
    @Positive
    long eventId;
    @Length(min = 3, max = 120)
    String title;
    @Length(min = 20, max = 200)
    String annotation;
    @Positive
    Long category;
    @Length(min = 20, max = 7000)
    String description;
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    Boolean paid;
    @PositiveOrZero
    Integer participantLimit;
}
