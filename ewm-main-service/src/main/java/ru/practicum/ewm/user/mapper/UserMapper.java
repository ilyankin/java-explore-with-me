package ru.practicum.ewm.user.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.user.dto.NewUserDto;
import ru.practicum.ewm.user.dto.UserDto;
import ru.practicum.ewm.user.model.User;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    Collection<UserDto> toDto(Collection<User> users);

    User to(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    User to(NewUserDto userDto);
}
