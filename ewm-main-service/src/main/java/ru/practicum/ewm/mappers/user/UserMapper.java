package ru.practicum.ewm.mappers.user;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.models.dtos.user.NewUserDto;
import ru.practicum.ewm.models.dtos.user.UserDto;
import ru.practicum.ewm.models.entities.user.User;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    Collection<UserDto> toDto(Collection<User> users);

    User to(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    User to(NewUserDto userDto);
}
