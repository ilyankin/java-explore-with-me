package ru.practicum.ewm.repositories.participation;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.models.entities.participation.ParticipationRequest;

import java.util.Collection;
import java.util.Set;

public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {
    Set<ParticipationRequest> findAllByRequesterId(long userId);

    Collection<ParticipationRequest> findAllByEventId(long eventId);
}
