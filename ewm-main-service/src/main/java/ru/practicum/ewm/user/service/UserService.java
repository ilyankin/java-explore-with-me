package ru.practicum.ewm.user.service;

import ru.practicum.ewm.user.dto.NewUserDto;
import ru.practicum.ewm.user.dto.UserDto;

import java.util.Collection;


public interface UserService {
    UserDto createUser(NewUserDto userDto);

    Collection<UserDto> getAllUsers(long[] ids, int from, int size);

    void deleteUser(long userId);
}
