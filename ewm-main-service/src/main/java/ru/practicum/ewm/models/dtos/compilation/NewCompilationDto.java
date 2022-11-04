package ru.practicum.ewm.models.dtos.compilation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Collection;

@Value
@Builder
@Jacksonized
public class NewCompilationDto {
    String title;
    boolean pinned;
    @JsonProperty("events")
    Collection<Long> eventIds;
}
