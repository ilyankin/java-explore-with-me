package ru.practicum.ewm.dto.event;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.constraint.EnumValue;
import ru.practicum.ewm.dto.location.LocationDto;
import ru.practicum.ewm.dto.user.UserShortDto;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class EventFullDto {
    @PositiveOrZero
    long id;
    @NotBlank
    @Length(min = 3, max = 120)
    String title;
    @NotBlank
    @Length(min = 20, max = 200)
    String annotation;
    @Length(min = 20, max = 7000)
    String description;
    @Valid
    @NotNull
    UserShortDto initiator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdOn;
    @Future
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime publishedOn;
    @Valid
    @NotNull
    CategoryDto category;
    @EnumValue(enumClass = EventState.class)
    String state;
    @Valid
    @NotNull
    LocationDto location;
    boolean paid;
    @Builder.Default
    boolean requestModeration = true;
    @PositiveOrZero
    int participantLimit;
    @PositiveOrZero
    long confirmedRequests;
    @PositiveOrZero
    long views;
}
