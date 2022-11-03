package ru.practicum.ewm.service.privateapi.event;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.exceptions.event.EventInvalidStateException;
import ru.practicum.ewm.exceptions.event.EventNotOnlyByInitiatorException;
import ru.practicum.ewm.exceptions.participation.ParticipationRequestInvalidStatusException;
import ru.practicum.ewm.exceptions.participation.ParticipationRequestLimitReachedException;
import ru.practicum.ewm.getters.category.CategoryGetter;
import ru.practicum.ewm.getters.event.EventGetter;
import ru.practicum.ewm.getters.participation.ParticipationRequestGetter;
import ru.practicum.ewm.getters.user.UserGetter;
import ru.practicum.ewm.mappers.event.EventMapper;
import ru.practicum.ewm.mappers.event.location.LocationMapper;
import ru.practicum.ewm.mappers.participation.ParticipationRequestMapper;
import ru.practicum.ewm.models.dtos.event.EventFullDto;
import ru.practicum.ewm.models.dtos.event.NewEventDto;
import ru.practicum.ewm.models.dtos.event.UpdateEventRequest;
import ru.practicum.ewm.models.dtos.participation.ParticipationRequestDto;
import ru.practicum.ewm.models.entities.event.Event;
import ru.practicum.ewm.models.entities.event.EventState;
import ru.practicum.ewm.models.entities.event.QEvent;
import ru.practicum.ewm.models.entities.participation.PRStatus;
import ru.practicum.ewm.repositories.event.EventRepository;
import ru.practicum.ewm.repositories.event.location.LocationRepository;
import ru.practicum.ewm.repositories.participation.ParticipationRequestRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class EventServicePrivateImpl implements EventServicePrivate, ParticipationServicePrivate {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final ParticipationRequestRepository prRepository;
    private final EventGetter eventGetter;
    private final UserGetter userGetter;
    private final CategoryGetter categoryGetter;
    private final ParticipationRequestGetter prGetter;
    private final EventMapper eventMapper;
    private final LocationMapper locationMapper;
    private final ParticipationRequestMapper prMapper;

    @Override
    public Collection<EventFullDto> getAllEvents(long userId, int from, int size) {
        val page = PageRequest.of(from / size, size, Sort.by("eventDate").descending());
        return eventMapper.toDto(eventRepository.findAll(QEvent.event.initiator.id.eq(userId), page).getContent());
    }

    @Override
    public EventFullDto getEvent(long userId, long eventId) {
        val event = eventGetter.getOrThrow(eventId);

        verifyInitiator(userId, event);

        return eventMapper.toDto(event);
    }

    @Override
    public EventFullDto updateEvent(long userId, UpdateEventRequest eventUpdateDto) {
        userGetter.getOrThrow(userId);

        val event = eventGetter.getOrThrow(eventUpdateDto.getEventId());
        val state = event.getState();

        if (state.isPublished())
            throw new EventInvalidStateException("update", state);

        if (state.isCanceled())
            event.setState(EventState.PENDING);

        return eventMapper.toDto(eventMapper.update(event, eventUpdateDto));
    }

    @Override
    public EventFullDto createEvent(long userId, NewEventDto eventDto) {
        val user = userGetter.getOrThrow(userId);
        val category = categoryGetter.getOrThrow(eventDto.getCategoryId());
        val location = locationRepository.save(locationMapper.to(eventDto.getLocation()));
        val event = eventMapper.create(new Event(), eventDto, user, category, location);
        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventFullDto cancelEvent(long userId, long eventId) {
        val event = eventGetter.getOrThrow(eventId);
        val state = event.getState();

        verifyInitiator(userId, event);

        if (state.isPublished())
            throw new EventInvalidStateException("cancel", state);

        if (state.isCanceled())
            throw new EventInvalidStateException("cancel", state);

        event.setState(EventState.CANCELED);
        return eventMapper.toDto(event);
    }

    @Override
    public Collection<ParticipationRequestDto> getAllParticipationRequests(long userId, long eventId) {
        val event = eventGetter.getOrThrow(eventId);

        verifyInitiator(userId, event);

        return prMapper.toDto(prRepository.findAllByEventId(eventId));
    }

    @Override
    @Transactional
    public ParticipationRequestDto confirmParticipationRequest(long userId, long eventId, long reqId) {
        val request = prGetter.getOrThrow(reqId);
        val event = eventGetter.getOrThrow(eventId);

        verifyInitiator(userId, event);

        val status = request.getStatus();
        if (status.isConfirmed())
            throw new ParticipationRequestInvalidStatusException("confirm", status);

        val participantLimit = event.getParticipantLimit();
        if (participantLimit != 0 && participantLimit == event.getConfirmedRequests().size()) {
            throw new ParticipationRequestLimitReachedException();
        }

        request.setStatus(PRStatus.CONFIRMED);
        val requestDto = prMapper.toDto(prRepository.save(request));

        event.getConfirmedRequests().add(request);

        if (participantLimit == event.getConfirmedRequests().size()) {
            val requests = prRepository.findAllByEventId(eventId);
            for (var r : requests) r.setStatus(PRStatus.REJECTED);
            prRepository.saveAll(requests);
        }

        return requestDto;
    }

    @Override
    public ParticipationRequestDto rejectParticipationRequest(long userId, long eventId, long reqId) {
        val request = prGetter.getOrThrow(reqId);
        val event = eventGetter.getOrThrow(eventId);

        verifyInitiator(userId, event);

        val status = request.getStatus();
        if (status.isRejected())
            throw new ParticipationRequestInvalidStatusException("rejected", status);

        request.setStatus(PRStatus.REJECTED);

        return prMapper.toDto(prRepository.save(request));
    }

    private static void verifyInitiator(long userId, Event event) {
        if (userId != event.getInitiator().getId())
            throw new EventNotOnlyByInitiatorException(userId, event.getId());
    }
}
