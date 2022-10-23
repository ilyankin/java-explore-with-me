package ru.practicum.ewm.compilation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.practicum.ewm.compilation.dto.CompilationDto;
import ru.practicum.ewm.compilation.dto.NewCompilationDto;
import ru.practicum.ewm.compilation.model.Compilation;
import ru.practicum.ewm.event.mapper.EventMapper;
import ru.practicum.ewm.event.repository.EventRepository;

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
