package ru.practicum.ewm.user.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.user.dto.NewUserDto;
import ru.practicum.ewm.user.dto.UserDto;
import ru.practicum.ewm.user.mapper.UserMapper;
import ru.practicum.ewm.user.repository.UserRepository;
import ru.practicum.ewm.user.getter.UserGetter;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserGetter userGetter;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(NewUserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.to(userDto)));
    }

    @Override
    public Collection<UserDto> getAllUsers(long[] ids, int from, int size) {
        val page = PageRequest.of(from / size, size);
        if (ids == null) {
            return userMapper.toDto(userRepository.findAll(page).getContent());
        }
        return userMapper.toDto(userRepository.findByIdIn(ids, page).getContent());
    }

    @Override
    public void deleteUser(long userId) {
        userGetter.getOrThrow(userId);
        userRepository.deleteById(userId);
    }
}
