package ru.practicum.ewm.service.publicapi.category;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.getters.category.CategoryGetter;
import ru.practicum.ewm.mappers.category.CategoryMapper;
import ru.practicum.ewm.models.dtos.category.CategoryDto;
import ru.practicum.ewm.repositories.category.CategoryRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CategoryServicePublicImpl implements CategoryServicePublic {
    private final CategoryRepository categoryRepository;
    private final CategoryGetter categoryGetter;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto getCategory(long categoryId) {
        return categoryMapper.toDto(categoryGetter.getOrThrow(categoryId));
    }

    @Override
    public Collection<CategoryDto> getAllCategories(int from, int size) {
        return categoryMapper.toDto(categoryRepository.findAll(PageRequest.of(from / size, size)).getContent());
    }
}
