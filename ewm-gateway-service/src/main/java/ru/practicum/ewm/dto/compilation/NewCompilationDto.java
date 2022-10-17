package ru.practicum.ewm.dto.compilation;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Value
@Builder
@Jacksonized
public class NewCompilationDto {
    @NotBlank
    @Length(max = 256)
    String title;
    boolean pinned;
    @Singular
    Collection<Integer> events;
}
