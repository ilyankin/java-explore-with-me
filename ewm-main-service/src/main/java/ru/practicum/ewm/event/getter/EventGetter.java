package ru.practicum.ewm.event.getter;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.event.repository.EventRepository;
import ru.practicum.ewm.other.exception.EntityNotFoundException;
import ru.practicum.ewm.other.getter.ThrownGettable;


@Component
@RequiredArgsConstructor
public class EventGetter implements ThrownGettable<Event, Long> {
    private final EventRepository eventRepository;

    @Override
    public Event getOrThrow(Long eventId) {
        val event = eventRepository.findById(eventId);
        if (event.isEmpty()) throw new EntityNotFoundException("Event", "id", eventId);
        return event.get();
    }
}
