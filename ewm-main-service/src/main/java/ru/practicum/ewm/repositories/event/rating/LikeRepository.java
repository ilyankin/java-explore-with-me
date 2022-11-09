package ru.practicum.ewm.repositories.event.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.models.entities.event.rating.Like;


public interface LikeRepository extends JpaRepository<Like, Like.PK> {
}
