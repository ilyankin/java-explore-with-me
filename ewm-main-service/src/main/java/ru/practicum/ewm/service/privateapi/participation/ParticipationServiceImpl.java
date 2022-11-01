package ru.practicum.ewm.service.privateapi.participation;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;
import ru.practicum.ewm.getters.event.EventGetter;
import ru.practicum.ewm.getters.participation.ParticipationRequestGetter;
import ru.practicum.ewm.getters.user.UserGetter;
import ru.practicum.ewm.mappers.participation.ParticipationRequestMapper;
import ru.practicum.ewm.models.dtos.participation.ParticipationRequestDto;
import ru.practicum.ewm.models.entities.participation.PRStatus;
import ru.practicum.ewm.models.entities.participation.ParticipationRequest;
import ru.practicum.ewm.repositories.participation.ParticipationRequestRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationRequestRepository prRepository;
    private final UserGetter userGetter;
    private final EventGetter eventGetter;
    private final ParticipationRequestMapper prMapper;
    private final ParticipationRequestGetter prGetter;

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
        val request = prGetter.getOrThrow(requestId);

        if (request.getRequester().getId() != userId)
            throw new ConditionsAreNotMetException("Only the requester can cancel the participation request.");

        request.setStatus(PRStatus.CANCELED);
        return prMapper.toDto(prRepository.save(request));
    }
}