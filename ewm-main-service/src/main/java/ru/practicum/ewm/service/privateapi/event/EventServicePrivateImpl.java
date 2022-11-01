package ru.practicum.ewm.service.privateapi.event;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.controllers.publicapi.event.params.SortType;
import ru.practicum.ewm.event.model.QEvent;
import ru.practicum.ewm.exceptions.ConditionsAreNotMetException;
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

    private static void verifyInitiator(long userId, Event event) {
        if (userId != event.getInitiator().getId())
            throw new ConditionsAreNotMetException("The user id=" + userId
                    + " isn't initiator of the event id=" + event.getId());
    }

    @Override
    public Collection<EventFullDto> privateGetAllEvents(long userId, int from, int size) {
        val page = PageRequest.of(from / size, size, toSort(SortType.EVENT_DATE));
        return eventMapper.toDto(eventRepository.findAll(QEvent.event.initiator.id.eq(userId), page).getContent());
    }

    @Override
    public EventFullDto privateGetEvent(long userId, long eventId) {
        val event = eventGetter.getOrThrow(eventId);

        verifyInitiator(userId, event);

        return eventMapper.toDto(event);
    }

    @Override
    public EventFullDto privateUpdateEvent(long userId, UpdateEventRequest eventUpdateDto) {
        userGetter.getOrThrow(userId);

        val event = eventGetter.getOrThrow(eventUpdateDto.getEventId());
        val state = event.getState();

        if (state.isPublished())
            throw new ConditionsAreNotMetException("The event with id=" + event.getId() + " has already been published.");

        if (state.isCanceled())
            event.setState(EventState.PENDING);

        return eventMapper.toDto(eventMapper.update(event, eventUpdateDto));
    }

    @Override
    public EventFullDto privateCreateEvent(long userId, NewEventDto eventDto) {
        val user = userGetter.getOrThrow(userId);
        val category = categoryGetter.getOrThrow(eventDto.getCategoryId());
        val location = locationRepository.save(locationMapper.to(eventDto.getLocation()));
        val event = eventMapper.create(new Event(), eventDto, user, category, location);
        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventFullDto privateCancelEvent(long userId, long eventId) {
        val event = eventGetter.getOrThrow(eventId);

        if (!event.getState().isPending())
            throw new ConditionsAreNotMetException("The event id=" + eventId + " already published or canceled");

        verifyInitiator(userId, event);

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

        if (request.getStatus().isConfirmed()) {
            throw new ConditionsAreNotMetException("The participant request id=" + reqId
                    + " has been already confirmed");
        }

        verifyInitiator(userId, event);

        val participantLimit = event.getParticipantLimit();
        if (participantLimit != 0 && participantLimit == event.getConfirmedRequests().size()) {
            throw new ConditionsAreNotMetException("Participant limit is full");
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

        if (request.getStatus().isRejected()) {
            throw new ConditionsAreNotMetException("The participant request id=" + reqId
                    + " has been already rejected");
        }

        verifyInitiator(userId, event);

        request.setStatus(PRStatus.REJECTED);

        return prMapper.toDto(prRepository.save(request));
    }

    private Sort toSort(SortType sortType) {
        switch (sortType) {
            case EVENT_DATE:
                return Sort.by("eventDate").descending();
            case VIEWS:
                return Sort.by("views").descending();
            default:
                throw new IllegalArgumentException("Unknown sort type: " + sortType);
        }
    }
}