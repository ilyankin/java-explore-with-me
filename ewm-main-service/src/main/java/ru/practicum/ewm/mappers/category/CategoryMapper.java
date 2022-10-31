package ru.practicum.ewm.mappers.category;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.models.dtos.category.CategoryDto;
import ru.practicum.ewm.models.dtos.category.CategoryNewDto;
import ru.practicum.ewm.models.entities.category.Category;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Collection<CategoryDto> toDto(Collection<Category> category);

    Category to(CategoryDto categoryDto);

    @Mapping(target = "id", ignore = true)
    Category to(CategoryNewDto categoryNewDto);
}
