package ru.practicum.ewm.mappers.event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.practicum.ewm.models.dtos.event.*;
import ru.practicum.ewm.models.entities.category.Category;
import ru.practicum.ewm.models.entities.event.Event;
import ru.practicum.ewm.models.entities.event.location.Location;
import ru.practicum.ewm.models.entities.user.User;
import ru.practicum.ewm.repositories.category.CategoryRepository;
import ru.practicum.ewm.repositories.participation.ParticipationRequestRepository;
import ru.practicum.ewm.service.publicapi.event.StatsClient;

import java.util.Collection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class EventMapper {
    @Autowired
    protected CategoryRepository categoryRepository;
    @Autowired
    protected ParticipationRequestRepository prRepository;

    @Autowired
    protected StatsClient statsClient;

    @Mapping(target = "confirmedRequests", expression = "java(event.getConfirmedRequests().size())")
    @Mapping(target = "likes", expression = "java(event.getLikes().size())")
    @Mapping(target = "dislikes", expression = "java(event.getDislikes().size())")
    @Mapping(target = "views", expression = "java(statsClient.getEventViews(event.getId()))")
    public abstract EventFullDto toDto(Event event);

    public abstract Collection<EventFullDto> toDto(Collection<Event> events);

    @Mapping(source = "initiator.id", target = "initiator.id")
    @Mapping(source = "initiator.name", target = "initiator.name")
    @Mapping(target = "confirmedRequests", expression = "java(event.getConfirmedRequests().size())")
    @Mapping(target = "likes", expression = "java(event.getLikes().size())")
    @Mapping(target = "dislikes", expression = "java(event.getDislikes().size())")
    @Mapping(target = "views", expression = "java(statsClient.getEventViews(event.getId()))")
    public abstract EventShortDto toShortDto(Event event);

    public abstract Collection<EventShortDto> toShortDto(Collection<Event> events);

    @Mapping(target = "views", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "dislikes", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "publishedOn", ignore = true)
    @Mapping(target = "initiator", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "confirmedRequests", ignore = true)
    @Mapping(target = "category",
            expression = "java(categoryRepository.findById(eventUpdateDto.getCategoryId()).orElse(null))")
    public abstract Event update(@MappingTarget Event event, AdminUpdateEventRequest eventUpdateDto);


    @Mapping(target = "views", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "dislikes", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "requestModeration", ignore = true)
    @Mapping(target = "publishedOn", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "initiator", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "confirmedRequests", ignore = true)
    @Mapping(target = "id", source = "eventId")
    @Mapping(target = "category",
            expression = "java(categoryRepository.findById(eventUpdateDto.getCategoryId()).orElse(null))")
    public abstract Event update(@MappingTarget Event event, UpdateEventRequest eventUpdateDto);

    @Mapping(target = "views", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "dislikes", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "publishedOn", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "confirmedRequests", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "initiator", source = "user")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "location", source = "location")
    public abstract Event create(@MappingTarget Event event, NewEventDto eventDto, User user, Category category,
                                 Location location);
}
