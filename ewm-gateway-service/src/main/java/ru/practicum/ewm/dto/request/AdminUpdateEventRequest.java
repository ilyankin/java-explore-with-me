package ru.practicum.ewm.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.dto.location.LocationDto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class AdminUpdateEventRequest {
    @Length(min = 3, max = 120)
    String title;
    @NotBlank
    @Length(min = 20, max = 200)
    String annotation;
    @Positive
    @JsonProperty("category")
    Long categoryId;
    @Length(min = 20, max = 7000)
    String description;
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    @Valid
    LocationDto location;
    Boolean paid;
    Boolean requestModeration;
    @PositiveOrZero
    Integer participantLimit;
}
