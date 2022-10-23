package ru.practicum.ewm.participation.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.event.getter.EventGetter;
import ru.practicum.ewm.other.exception.ConditionsAreNotMetException;
import ru.practicum.ewm.other.exception.EntityNotFoundException;
import ru.practicum.ewm.participation.dto.ParticipationRequestDto;
import ru.practicum.ewm.participation.mapper.ParticipationRequestMapper;
import ru.practicum.ewm.participation.model.PRStatus;
import ru.practicum.ewm.participation.model.ParticipationRequest;
import ru.practicum.ewm.participation.repository.ParticipationRequestRepository;
import ru.practicum.ewm.user.getter.UserGetter;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationRequestRepository prRepository;
    private final UserGetter userGetter;
    private final EventGetter eventGetter;
    private final ParticipationRequestMapper prMapper;

    @Override
    public Collection<ParticipationRequestDto> getParticipationRequests(long userId) {
        return prMapper.toDto(prRepository.findAllByRequesterId(userId));
    }

    @Override
    public ParticipationRequestDto createParticipationRequest(long userId, long eventId) {
        val requester = userGetter.getOrThrow(userId);
        val event = eventGetter.getOrThrow(eventId);

        if (!event.getState().isPublished())
            throw new ConditionsAreNotMetException("You cannot participate in an unpublished event.");


        if (event.getInitiator().getId() == userId)
            throw new ConditionsAreNotMetException(
                    "The initiator of the event cannot request participation in his own event.");

        if (event.getParticipantLimit() != 0 && event.getConfirmedRequests().size() == event.getParticipantLimit())
            throw new ConditionsAreNotMetException("The number of participation requests has reached the limit.");

        val request = new ParticipationRequest();
        if (!event.isRequestModeration()) {
            request.setStatus(PRStatus.CONFIRMED);
        }

        request.setRequester(requester);
        request.setEvent(event);

        return prMapper.toDto(prRepository.save(request));
    }

    @Override
    public ParticipationRequestDto cancelParticipationRequest(long userId, long requestId) {
        userGetter.getOrThrow(userId);
        val request = prRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("participation request", "id", requestId));

        if (request.getRequester().getId() != userId)
            throw new ConditionsAreNotMetException("Only the requester can cancel the participation request.");

        request.setStatus(PRStatus.CANCELED);
        return prMapper.toDto(prRepository.save(request));
    }
}
