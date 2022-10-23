package ru.practicum.ewm.participation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.participation.dto.ParticipationRequestDto;
import ru.practicum.ewm.participation.model.ParticipationRequest;
import ru.practicum.ewm.user.model.User;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ParticipationRequestMapper {
    @Mapping(target = "requester", source = "requester.id")
    @Mapping(target = "event", source = "event.id")
    ParticipationRequestDto toDto(ParticipationRequest participationRequests);

    Collection<ParticipationRequestDto> toDto(Collection<ParticipationRequest> participationRequests);
}
