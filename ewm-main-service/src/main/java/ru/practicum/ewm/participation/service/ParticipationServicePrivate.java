package ru.practicum.ewm.participation.service;

import ru.practicum.ewm.participation.dto.ParticipationRequestDto;

import java.util.Collection;

public interface ParticipationServicePrivate {
    Collection<ParticipationRequestDto> getAllParticipationRequests(long userId, long eventId);

    ParticipationRequestDto confirmParticipationRequest(long userId, long eventId, long reqId);

    ParticipationRequestDto rejectParticipationRequest(long userId, long eventId, long reqId);
}
