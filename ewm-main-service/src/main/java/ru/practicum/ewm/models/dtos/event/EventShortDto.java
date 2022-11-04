package ru.practicum.ewm.models.dtos.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewm.models.dtos.category.CategoryDto;
import ru.practicum.ewm.models.dtos.user.UserShortDto;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class EventShortDto {
    long id;
    String title;
    String annotation;
    CategoryDto category;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    UserShortDto initiator;
    boolean paid;
    long confirmedRequests;
    long views;
}
