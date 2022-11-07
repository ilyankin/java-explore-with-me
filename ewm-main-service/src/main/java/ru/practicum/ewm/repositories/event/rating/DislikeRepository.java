package ru.practicum.ewm.repositories.event.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.models.entities.event.rating.Dislike;

public interface DislikeRepository extends JpaRepository<Dislike, Dislike.PK> {
}
