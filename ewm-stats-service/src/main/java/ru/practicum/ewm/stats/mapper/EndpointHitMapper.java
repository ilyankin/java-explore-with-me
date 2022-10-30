package ru.practicum.ewm.stats.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.stats.model.EndpointHit;
import ru.practicum.ewm.stats.model.dto.EndpointHitDto;

@Mapper(componentModel = "spring")
public interface EndpointHitMapper {
    @Mapping(target = "id", ignore = true)
    EndpointHit to(EndpointHitDto endpointHitDto);

    EndpointHitDto toDto(EndpointHit endpointHit);
}
