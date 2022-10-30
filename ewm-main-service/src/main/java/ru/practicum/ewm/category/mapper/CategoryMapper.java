package ru.practicum.ewm.category.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.category.dto.CategoryDto;
import ru.practicum.ewm.category.dto.CategoryNewDto;
import ru.practicum.ewm.category.model.Category;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Collection<CategoryDto> toDto(Collection<Category> category);

    Category to(CategoryDto categoryDto);

    @Mapping(target = "id", ignore = true)
    Category to(CategoryNewDto categoryNewDto);
}
