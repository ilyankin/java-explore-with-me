package ru.practicum.ewm.compilation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Value
@Builder
@Jacksonized
public class NewCompilationDto {
    @NotBlank
    @Length(max = 256)
    String title;
    boolean pinned;
    @Singular
    @JsonProperty("events")
    Set<Integer> eventIds;
}
