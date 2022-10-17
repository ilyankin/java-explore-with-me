package ru.practicum.ewm.dto.user;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Value
@Builder
@Jacksonized
public class UserDto {
    @PositiveOrZero
    long id;
    @Email
    @NotNull
    @Length(max = 256)
    String email;
    @NotBlank
    @Length(max = 128)
    String name;
}
