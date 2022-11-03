package ru.practicum.ewm.getters.event;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.exceptions.event.EventByIdNotFoundException;
import ru.practicum.ewm.getters.ThrownGettable;
import ru.practicum.ewm.models.entities.event.Event;
import ru.practicum.ewm.repositories.event.EventRepository;


@Component
@RequiredArgsConstructor
public class EventGetter implements ThrownGettable<Event, Long> {
    private final EventRepository eventRepository;

    @Override
    public Event getOrThrow(Long eventId) {
        val event = eventRepository.findById(eventId);
        if (event.isEmpty()) throw new EventByIdNotFoundException(eventId);
        return event.get();
    }
}
