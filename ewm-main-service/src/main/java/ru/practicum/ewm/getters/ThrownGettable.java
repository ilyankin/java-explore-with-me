package ru.practicum.ewm.getters;

import ru.practicum.ewm.exceptions.EntityNotFoundException;

public interface ThrownGettable<T, K> {
    T getOrThrow(K k) throws EntityNotFoundException;
}
