package ru.practicum.ewm.participation.service;

import ru.practicum.ewm.participation.dto.ParticipationRequestDto;

import java.util.Collection;
import java.util.List;

public interface ParticipationService {
    Collection<ParticipationRequestDto> getParticipationRequests(long userId);

    ParticipationRequestDto createParticipationRequest(long userId, long eventId);

    ParticipationRequestDto cancelParticipationRequest(long userId, long requestId);
}
