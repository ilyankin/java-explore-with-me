package ru.practicum.ewm.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.dto.category.CategoryDto;
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
public class EventShortDto {
    @PositiveOrZero
    long id;
    @NotBlank
    @Length(min = 3, max = 120)
    String title;
    @NotBlank
    @Length(min = 20, max = 200)
    String annotation;
    @Valid
    @NotNull
    CategoryDto category;
    @Future
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    @Valid
    @NotNull
    UserShortDto initiator;
    boolean paid;
    @PositiveOrZero
    long confirmedRequests;
    @PositiveOrZero
    long views;
}
