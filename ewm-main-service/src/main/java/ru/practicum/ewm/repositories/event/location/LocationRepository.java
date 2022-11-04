package ru.practicum.ewm.repositories.event.location;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.models.entities.event.location.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
