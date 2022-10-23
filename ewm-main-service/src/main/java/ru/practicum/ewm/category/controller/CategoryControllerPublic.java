package ru.practicum.ewm.category.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.category.dto.CategoryDto;
import ru.practicum.ewm.category.service.CategoryServicePublic;

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
