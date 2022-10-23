package ru.practicum.ewm.compilation.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewm.event.model.dto.EventShortDto;

import java.util.Collection;

@Value
@Builder
@Jacksonized
public class CompilationDto {
    Long id;
    Boolean pinned;
    String title;
    Collection<EventShortDto> events;
}
