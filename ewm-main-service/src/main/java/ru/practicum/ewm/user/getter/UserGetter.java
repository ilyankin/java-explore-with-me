package ru.practicum.ewm.user.getter;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.other.exception.EntityNotFoundException;
import ru.practicum.ewm.other.getter.ThrownGettable;
import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserGetter implements ThrownGettable<User, Long> {
    private final UserRepository userRepository;

    @Override
    public User getOrThrow(Long userId) throws EntityNotFoundException {
        val user = userRepository.findById(userId);
        if (user.isEmpty()) throw new EntityNotFoundException("User", "id", userId);
        return user.get();
    }
}
