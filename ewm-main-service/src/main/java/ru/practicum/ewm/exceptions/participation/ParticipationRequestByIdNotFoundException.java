package ru.practicum.ewm.exceptions.participation;

import ru.practicum.ewm.exceptions.EntityNotFoundException;

public class ParticipationRequestByIdNotFoundException extends EntityNotFoundException {
    public ParticipationRequestByIdNotFoundException(Long participationId) {
        super("participation request", "id", participationId);
    }
}
