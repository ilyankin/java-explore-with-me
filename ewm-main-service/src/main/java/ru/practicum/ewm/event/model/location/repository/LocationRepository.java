package ru.practicum.ewm.event.model.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.event.model.location.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
