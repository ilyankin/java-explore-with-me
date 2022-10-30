package ru.practicum.ewm.event.model.location.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.event.model.location.dto.LocationDto;
import ru.practicum.ewm.event.model.location.mapper.LocationMapper;
import ru.practicum.ewm.event.model.location.model.Location;
import ru.practicum.ewm.event.model.location.repository.LocationRepository;
import ru.practicum.ewm.other.exception.EntityNotFoundException;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    public LocationDto createLocation(Location location) {
        return locationMapper.toDto(locationRepository.save(location));
    }

    @Override
    public LocationDto getLocation(long locationId) {
        val location = locationRepository.findById(locationId);
        if (location.isEmpty()) throw new EntityNotFoundException("Location", "id", locationId);
        return locationMapper.toDto(location.get());
    }

    @Override
    public void deleteLocation(long locationId) {
        locationRepository.deleteById(locationId);
    }
}
