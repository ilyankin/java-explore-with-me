package ru.practicum.ewm.event.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.practicum.ewm.category.model.Category;
import ru.practicum.ewm.category.reopository.CategoryRepository;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.event.model.dto.*;
import ru.practicum.ewm.event.model.location.model.Location;
import ru.practicum.ewm.participation.repository.ParticipationRequestRepository;
import ru.practicum.ewm.stats.StatsClient;
import ru.practicum.ewm.user.model.User;

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
    @Mapping(target = "views", expression = "java(statsClient.getEventViews(event.getId()))")
    public abstract EventFullDto toDto(Event event);

    public abstract Collection<EventFullDto> toDto(Collection<Event> events);

    @Mapping(source = "initiator.id", target = "initiator.id")
    @Mapping(source = "initiator.name", target = "initiator.name")
    @Mapping(target = "confirmedRequests", expression = "java(event.getConfirmedRequests().size())")
    @Mapping(target = "views", expression = "java(statsClient.getEventViews(event.getId()))")
    public abstract EventShortDto toShortDto(Event event);

    public abstract Collection<EventShortDto> toShortDto(Collection<Event> events);

    @Mapping(target = "views", ignore = true)
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
