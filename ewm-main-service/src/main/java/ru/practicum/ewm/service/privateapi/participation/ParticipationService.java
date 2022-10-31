package ru.practicum.ewm.service.privateapi.participation;

import ru.practicum.ewm.models.dtos.participation.ParticipationRequestDto;

import java.util.Collection;

public interface ParticipationService {
    Collection<ParticipationRequestDto> getParticipationRequests(long userId);

    ParticipationRequestDto createParticipationRequest(long userId, long eventId);

    ParticipationRequestDto cancelParticipationRequest(long userId, long requestId);
}
