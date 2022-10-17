package ru.practicum.ewm.dto.category;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Value
@Builder
@Jacksonized
public class CategoryDto {
    @PositiveOrZero
    long id;
    @NotBlank
    @Length(max = 32)
    String name;
}
