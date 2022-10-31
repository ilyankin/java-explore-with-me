package ru.practicum.ewm.getters.participation;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.exceptions.EntityNotFoundException;
import ru.practicum.ewm.getters.ThrownGettable;
import ru.practicum.ewm.models.entities.participation.ParticipationRequest;
import ru.practicum.ewm.repositories.participation.ParticipationRequestRepository;

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
