package ru.practicum.ewm.getters.user;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.exceptions.EntityNotFoundException;
import ru.practicum.ewm.exceptions.user.UserByIdNotFoundException;
import ru.practicum.ewm.getters.ThrownGettable;
import ru.practicum.ewm.models.entities.user.User;
import ru.practicum.ewm.repositories.user.UserRepository;

@Component
@RequiredArgsConstructor
public class UserGetter implements ThrownGettable<User, Long> {
    private final UserRepository userRepository;

    @Override
    public User getOrThrow(Long userId) throws EntityNotFoundException {
        val user = userRepository.findById(userId);
        if (user.isEmpty()) throw new UserByIdNotFoundException(userId);
        return user.get();
    }
}
