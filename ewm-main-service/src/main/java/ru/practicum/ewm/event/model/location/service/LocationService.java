package ru.practicum.ewm.event.model.location.service;

import ru.practicum.ewm.event.model.location.dto.LocationDto;
import ru.practicum.ewm.event.model.location.model.Location;

public interface LocationService {
    LocationDto createLocation(Location location);

    LocationDto getLocation(long locationId);

    void deleteLocation(long locationId);
}
