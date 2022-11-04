package ru.practicum.ewm.exceptions.user;

import ru.practicum.ewm.exceptions.EntityNotFoundException;

public class UserByIdNotFoundException extends EntityNotFoundException {
    public UserByIdNotFoundException(Long userId) {
        super("user", "id", userId);
    }
}
