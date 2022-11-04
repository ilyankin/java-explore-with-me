package ru.practicum.ewm.mappers.event.location;

import org.mapstruct.Mapper;
import ru.practicum.ewm.models.dtos.event.location.LocationDto;
import ru.practicum.ewm.models.entities.event.location.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDto toDto(Location location);

    Location to(LocationDto locationDto);
}
