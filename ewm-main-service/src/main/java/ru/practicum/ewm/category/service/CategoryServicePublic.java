package ru.practicum.ewm.category.service;

import ru.practicum.ewm.category.dto.CategoryDto;

import java.util.Collection;

public interface CategoryServicePublic {
    CategoryDto getCategory(long categoryId);

    Collection<CategoryDto> getAllCategories(int from, int size);
}
