package ru.practicum.ewm.event.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewm.event.model.location.dto.LocationDto;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class AdminUpdateEventRequest {
    String title;
    String annotation;
    @JsonProperty("category")
    Long categoryId;
    String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    LocationDto location;
    Boolean paid;
    Boolean requestModeration;
    Integer participantLimit;
}
