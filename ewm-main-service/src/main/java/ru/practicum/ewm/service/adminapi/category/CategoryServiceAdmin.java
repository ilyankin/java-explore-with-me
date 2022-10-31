package ru.practicum.ewm.service.adminapi.category;

import ru.practicum.ewm.models.dtos.category.CategoryDto;
import ru.practicum.ewm.models.dtos.category.CategoryNewDto;

public interface CategoryServiceAdmin {
    CategoryDto createCategory(CategoryNewDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto);

    void deleteCategory(long categoryId);
}
