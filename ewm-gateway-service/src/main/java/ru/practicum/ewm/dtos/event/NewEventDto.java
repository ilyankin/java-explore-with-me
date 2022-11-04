package ru.practicum.ewm.dtos.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.ewm.dtos.event.location.LocationDto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class NewEventDto {
    @NotBlank
    @Length(min = 20, max = 2000)
    String annotation;
    @NotNull
    @Positive
    @JsonProperty("category")
    Long categoryId;
    @NotBlank
    @Length(min = 20, max = 7000)
    String description;
    @Future
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    @Valid
    @NotNull
    LocationDto location;
    boolean paid;
    @PositiveOrZero
    int participantLimit;
    @Builder.Default
    boolean requestModeration = true;
    @NotBlank
    @Length(min = 3, max = 120)
    String title;
}
