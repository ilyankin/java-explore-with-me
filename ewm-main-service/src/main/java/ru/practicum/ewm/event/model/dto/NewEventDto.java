package ru.practicum.ewm.event.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.event.model.location.dto.LocationDto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class NewEventDto {
    String annotation;
    @JsonProperty("category")
    long categoryId;
    String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    LocationDto location;
    boolean paid;
    int participantLimit;
    boolean requestModeration;
    @NotBlank
    @Length(min = 3, max = 120)
    String title;
}
