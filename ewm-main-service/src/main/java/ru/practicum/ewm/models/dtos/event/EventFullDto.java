package ru.practicum.ewm.models.dtos.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewm.models.dtos.category.CategoryDto;
import ru.practicum.ewm.models.dtos.event.location.LocationDto;
import ru.practicum.ewm.models.dtos.user.UserShortDto;
import ru.practicum.ewm.models.entities.event.EventState;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class EventFullDto {
    long id;
    String title;
    String annotation;
    String description;
    UserShortDto initiator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdOn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime publishedOn;
    CategoryDto category;
    EventState state;
    LocationDto location;
    boolean paid;
    boolean requestModeration;
    int participantLimit;
    long confirmedRequests;
    long views;
}
