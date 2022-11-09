package ru.practicum.ewm.service.privateapi.participation;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.exceptions.participation.ParticipationRequestInitializedByEventInitiatorException;
import ru.practicum.ewm.exceptions.participation.ParticipationRequestInvalidStatusException;
import ru.practicum.ewm.exceptions.participation.ParticipationRequestLimitReachedException;
import ru.practicum.ewm.exceptions.participation.ParticipationRequestToUnpublishedEventException;
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
public class ParticipationRequestServiceImpl implements ParticipationRequestService {
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
            throw new ParticipationRequestToUnpublishedEventException();

        if (event.getInitiator().getId() == userId)
            throw new ParticipationRequestInitializedByEventInitiatorException("create");

        if (event.getParticipantLimit() != 0 && event.getConfirmedRequests().size() == event.getParticipantLimit())
            throw new ParticipationRequestLimitReachedException();

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
            throw new ParticipationRequestInitializedByEventInitiatorException("cancel");

        val status = request.getStatus();
        if (status.isCanceled())
            throw new ParticipationRequestInvalidStatusException("cancel", status);

        request.setStatus(PRStatus.CANCELED);
        return prMapper.toDto(prRepository.save(request));
    }
}
