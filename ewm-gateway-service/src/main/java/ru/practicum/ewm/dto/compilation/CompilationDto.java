package ru.practicum.ewm.dto.compilation;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.dto.event.EventShortDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;

@Value
@Builder
@Jacksonized
public class CompilationDto {
    @PositiveOrZero
    long id;
    @NotBlank
    @Length(max = 256)
    String title;
    boolean pinned;
    @NotNull
    Collection<EventShortDto> events;
}
