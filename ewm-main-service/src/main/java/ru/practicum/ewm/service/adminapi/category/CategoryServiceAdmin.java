package ru.practicum.ewm.service.adminapi.category;

import ru.practicum.ewm.models.dtos.category.CategoryDto;
import ru.practicum.ewm.models.dtos.category.CategoryNewDto;

/**
 * The service contains methods for implementing the category's admin API application.
 *
 * @author Izenkyt
 */
public interface CategoryServiceAdmin {
    /**
     * Creates a new category.
     *
     * @param categoryDto - dto for creating {@link ru.practicum.ewm.models.entities.category.Category}
     * @return {@link CategoryDto} - direct category dto
     */
    CategoryDto createCategory(CategoryNewDto categoryDto);

    /**
     * Updates a category.
     *
     * @param categoryDto - dto for updating {@link ru.practicum.ewm.models.entities.category.Category}
     * @return {@link CategoryDto} - direct category dto
     */
    CategoryDto updateCategory(CategoryDto categoryDto);

    /**
     * Deletes a category.
     *
     * @param categoryId - category identifier
     */
    void deleteCategory(long categoryId);
}
