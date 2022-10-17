package ru.practicum.ewm.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.location.LocationDto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class NewEventDto {
    @PositiveOrZero
    @JsonProperty("eventId")
    long id;
    @NotBlank
    @Length(min = 3, max = 120)
    String title;
    @NotBlank
    @Length(min = 20, max = 200)
    String annotation;
    @Length(min = 20, max = 7000)
    String description;
    @Future
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    @NotNull
    @Positive
    @JsonProperty("category")
    Long categoryId;
    @Valid
    @NotNull
    LocationDto location;
    boolean paid;
    @Builder.Default
    boolean requestModeration = true;
    @PositiveOrZero
    int participantLimit;
}
