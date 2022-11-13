package ru.practicum.ewm.controllers.adminapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.models.dtos.user.NewUserDto;
import ru.practicum.ewm.models.dtos.user.UserDto;
import ru.practicum.ewm.service.privateapi.user.UserService;

import java.util.Collection;

/**
 * The controller processes requests from user's private API of the application.
 *
 * @author Izenkyt
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto create(@RequestBody NewUserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping
    public Collection<UserDto> getAll(@RequestParam(required = false) long[] ids,
                                      @RequestParam int from,
                                      @RequestParam int size) {
        return userService.getAllUsers(ids, from, size);
    }

    @DeleteMapping("{userId}")
    public void delete(@PathVariable long userId) {
        userService.deleteUser(userId);
    }
}
