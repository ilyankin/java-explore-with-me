package ru.practicum.ewm.models.dtos.user;

import lombok.Value;

@Value
public class UserDto {
    long id;
    String name;
    String email;
}
