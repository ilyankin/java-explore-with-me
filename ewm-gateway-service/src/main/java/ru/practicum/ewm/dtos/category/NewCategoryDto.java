package ru.practicum.ewm.dtos.category;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@Jacksonized
public class NewCategoryDto {
    @NotBlank
    @Length(max = 32)
    String name;
}
