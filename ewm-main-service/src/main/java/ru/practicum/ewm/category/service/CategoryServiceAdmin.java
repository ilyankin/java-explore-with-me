package ru.practicum.ewm.category.service;

import ru.practicum.ewm.category.dto.CategoryDto;
import ru.practicum.ewm.category.dto.CategoryNewDto;

public interface CategoryServiceAdmin {
    CategoryDto createCategory(CategoryNewDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto);
    void deleteCategory(long categoryId);
}
