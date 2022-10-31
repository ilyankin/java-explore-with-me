package ru.practicum.ewm.mappers.compilation;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.practicum.ewm.mappers.event.EventMapper;
import ru.practicum.ewm.models.dtos.compilation.CompilationDto;
import ru.practicum.ewm.models.dtos.compilation.NewCompilationDto;
import ru.practicum.ewm.models.entities.compilation.Compilation;
import ru.practicum.ewm.repositories.event.EventRepository;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public abstract class CompilationMapper {
    @Autowired
    protected EventRepository eventRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", expression = "java(eventRepository.findAllById(compilationDto.getEventIds()))")
    public abstract Compilation to(NewCompilationDto compilationDto);

    public abstract CompilationDto toDto(Compilation compilation);

    public abstract Collection<CompilationDto> toDto(Collection<Compilation> compilations);
}
