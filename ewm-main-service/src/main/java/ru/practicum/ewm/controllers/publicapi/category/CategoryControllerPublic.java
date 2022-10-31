package ru.practicum.ewm.controllers.publicapi.category;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.models.dtos.category.CategoryDto;
import ru.practicum.ewm.service.publicapi.category.CategoryServicePublic;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoryControllerPublic {
    private final CategoryServicePublic categoryService;

    @GetMapping("{catId}")
    public CategoryDto get(@PathVariable("catId") long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @GetMapping
    public Collection<CategoryDto> getAll(@RequestParam int from, @RequestParam int size) {
        return categoryService.getAllCategories(from, size);
    }
}
