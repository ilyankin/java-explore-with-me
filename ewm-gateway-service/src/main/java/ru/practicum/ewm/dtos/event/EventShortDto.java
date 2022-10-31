package ru.practicum.ewm.dtos.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.ewm.dtos.category.CategoryDto;
import ru.practicum.ewm.dtos.user.UserShortDto;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class EventShortDto {
    @NotBlank
    @Length(min = 20, max = 2000)
    String annotation;
    @Valid
    @NotNull
    CategoryDto category;
    @PositiveOrZero
    long confirmedRequests;
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    @PositiveOrZero
    long id;
    @Valid
    @NotNull
    UserShortDto initiator;
    boolean paid;
    @NotBlank
    @Length(min = 3, max = 120)
    String title;
    @PositiveOrZero
    long views;
}
