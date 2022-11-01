package ru.practicum.ewm.service.privateapi.participation;

import ru.practicum.ewm.models.dtos.participation.ParticipationRequestDto;

import java.util.Collection;

/**
 * The service contains methods for implementing the participation request's private API application
 *
 * @author Izenkyt
 */
public interface ParticipationRequestService {

    /**
     * Gets participation requests made by the user
     *
     * @param userId - user id
     * @return collection of {@link ParticipationRequestDto} - direct participation request dto
     */
    Collection<ParticipationRequestDto> getParticipationRequests(long userId);


    /**
     * Creates participation request by the user
     *
     * @param userId  - user id
     * @param eventId - event id
     * @return {@link ParticipationRequestDto} - direct participation request dto
     */
    ParticipationRequestDto createParticipationRequest(long userId, long eventId);

    /**
     * Cancel participation request by the user
     *
     * @param userId    - user id
     * @param requestId - participation request id
     * @return {@link ParticipationRequestDto} - direct participation request dto
     */
    ParticipationRequestDto cancelParticipationRequest(long userId, long requestId);
}
