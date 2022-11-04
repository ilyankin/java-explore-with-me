package ru.practicum.ewm.dtos.user;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Value
@Builder
@Jacksonized
public class UserShortDto {
    @PositiveOrZero
    long id;
    @NotBlank
    @Length(max = 128)
    String name;
}
