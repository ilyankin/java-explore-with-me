package ru.practicum.ewm.participation.getter;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.other.exception.EntityNotFoundException;
import ru.practicum.ewm.other.getter.ThrownGettable;
import ru.practicum.ewm.participation.model.ParticipationRequest;
import ru.practicum.ewm.participation.repository.ParticipationRequestRepository;

@Component
@RequiredArgsConstructor
public class ParticipationRequestGetter implements ThrownGettable<ParticipationRequest, Long> {
    private final ParticipationRequestRepository prRepository;

    @Override
    public ParticipationRequest getOrThrow(Long participationId) throws EntityNotFoundException {
        val event = prRepository.findById(participationId);
        if (event.isEmpty()) throw new EntityNotFoundException("ParticipationRequest", "id", participationId);
        return event.get();
    }
}
