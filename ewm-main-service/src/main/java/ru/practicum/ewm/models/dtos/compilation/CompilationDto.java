package ru.practicum.ewm.models.dtos.compilation;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewm.models.dtos.event.EventShortDto;

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
