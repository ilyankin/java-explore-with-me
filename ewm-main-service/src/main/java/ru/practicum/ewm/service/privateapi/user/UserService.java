package ru.practicum.ewm.service.privateapi.user;

import ru.practicum.ewm.models.dtos.participation.ParticipationRequestDto;
import ru.practicum.ewm.models.dtos.user.NewUserDto;
import ru.practicum.ewm.models.dtos.user.UserDto;

import java.util.Collection;


/**
 * The service contains methods for implementing the user's admin API application
 *
 * @author Izenkyt
 */
public interface UserService {

    /**
     * Creates a user
     *
     * @param userDto - dto for creating {@link ru.practicum.ewm.models.entities.user.User}
     * @return {@link ParticipationRequestDto} - direct user dto
     */
    UserDto createUser(NewUserDto userDto);

    /**
     * Gets collection of users
     *
     * @param ids  - array of user's id
     * @param from - number of event that need to be skipped to form the returned collection of users
     * @param size - number of users in the returned collection of users
     * @return collection of {@link UserDto} - direct user dto
     */
    Collection<UserDto> getAllUsers(long[] ids, int from, int size);

    /**
     * Delete a user
     *
     * @param userId - user id
     */
    void deleteUser(long userId);
}
