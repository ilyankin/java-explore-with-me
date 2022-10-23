package ru.practicum.ewm.other.getter;

import ru.practicum.ewm.other.exception.EntityNotFoundException;

public interface ThrownGettable<T, K> {
    T getOrThrow(K k) throws EntityNotFoundException;
}
