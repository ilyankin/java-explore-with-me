package ru.practicum.ewm.dto.request;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;


@Value
@Builder
@Jacksonized
public class NewUserRequest {
    @Email
    @Length(max = 256)
    String email;
    @Length(max = 128)
    String name;
}
