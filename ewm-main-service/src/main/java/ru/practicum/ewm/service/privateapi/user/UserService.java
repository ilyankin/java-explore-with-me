package ru.practicum.ewm.service.privateapi.user;

import ru.practicum.ewm.models.dtos.user.NewUserDto;
import ru.practicum.ewm.models.dtos.user.UserDto;

import java.util.Collection;


public interface UserService {
    UserDto createUser(NewUserDto userDto);

    Collection<UserDto> getAllUsers(long[] ids, int from, int size);

    void deleteUser(long userId);
}
