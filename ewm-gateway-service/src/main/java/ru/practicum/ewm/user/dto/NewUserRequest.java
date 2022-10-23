package ru.practicum.ewm.user.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Value
@Builder
@Jacksonized
public class NewUserRequest {
    @Email
    @NotEmpty
    @Length(max = 256)
    String email;
    @NotBlank
    @Length(max = 128)
    String name;
}
