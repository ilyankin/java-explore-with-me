package ru.practicum.ewm.controllers.adminapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.clients.adminapi.user.UserClient;
import ru.practicum.ewm.dtos.user.NewUserRequest;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/users")
public class UserController {
    private final UserClient userClient;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam Set<Long> ids,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return userClient.getUsers(ids, from, size);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody NewUserRequest userRequest) {
        return userClient.createUser(userRequest);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> delete(@PathVariable @Positive Long userId) {
        return userClient.deleteUser(userId);
    }
}
