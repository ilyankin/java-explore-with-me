package ru.practicum.ewm.user.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class NewUserDto {
    String name;
    String email;
}
