package ru.practicum.ewm.service.publicapi.category;

import ru.practicum.ewm.models.dtos.category.CategoryDto;

import java.util.Collection;

public interface CategoryServicePublic {
    CategoryDto getCategory(long categoryId);

    Collection<CategoryDto> getAllCategories(int from, int size);
}
