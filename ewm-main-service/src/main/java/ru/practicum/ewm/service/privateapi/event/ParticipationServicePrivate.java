package ru.practicum.ewm.service.privateapi.event;

import ru.practicum.ewm.models.dtos.participation.ParticipationRequestDto;

import java.util.Collection;

public interface ParticipationServicePrivate {
    Collection<ParticipationRequestDto> getAllParticipationRequests(long userId, long eventId);

    ParticipationRequestDto confirmParticipationRequest(long userId, long eventId, long reqId);

    ParticipationRequestDto rejectParticipationRequest(long userId, long eventId, long reqId);
}
