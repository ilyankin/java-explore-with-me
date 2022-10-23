package ru.practicum.ewm.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.user.dto.NewUserDto;
import ru.practicum.ewm.user.dto.UserDto;
import ru.practicum.ewm.user.service.UserService;

import java.util.Collection;


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
