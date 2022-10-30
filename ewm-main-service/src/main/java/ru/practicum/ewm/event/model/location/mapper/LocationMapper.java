package ru.practicum.ewm.event.model.location.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewm.event.model.location.dto.LocationDto;
import ru.practicum.ewm.event.model.location.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDto toDto(Location location);

    Location to(LocationDto locationDto);
}
