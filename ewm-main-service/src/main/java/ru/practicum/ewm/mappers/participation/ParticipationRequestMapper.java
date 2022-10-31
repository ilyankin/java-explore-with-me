package ru.practicum.ewm.mappers.participation;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.models.dtos.participation.ParticipationRequestDto;
import ru.practicum.ewm.models.entities.participation.ParticipationRequest;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ParticipationRequestMapper {
    @Mapping(target = "requester", source = "requester.id")
    @Mapping(target = "event", source = "event.id")
    ParticipationRequestDto toDto(ParticipationRequest participationRequests);

    Collection<ParticipationRequestDto> toDto(Collection<ParticipationRequest> participationRequests);
}
