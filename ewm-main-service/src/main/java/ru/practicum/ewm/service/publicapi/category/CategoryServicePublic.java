package ru.practicum.ewm.service.publicapi.category;

import ru.practicum.ewm.models.dtos.category.CategoryDto;

import java.util.Collection;

/**
 * The service contains methods for implementing the category's public API application.
 *
 * @author Izenkyt
 */
public interface CategoryServicePublic {
    /**
     * Gets a category.
     *
     * @param categoryId - category identifier
     * @return {@link CategoryDto} - direct category dto
     */
    CategoryDto getCategory(long categoryId);

    /**
     * Gets a collection of categories.
     *
     * @param from - number of categories that need to be skipped to form the returned collection of categories
     * @param size - number of categories in the returned collection of categories
     * @return collection of {@link CategoryDto} - direct category dto
     */
    Collection<CategoryDto> getAllCategories(int from, int size);
}
