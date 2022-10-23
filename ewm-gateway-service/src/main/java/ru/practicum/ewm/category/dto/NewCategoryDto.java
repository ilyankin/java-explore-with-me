package ru.practicum.ewm.category.dto;

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
