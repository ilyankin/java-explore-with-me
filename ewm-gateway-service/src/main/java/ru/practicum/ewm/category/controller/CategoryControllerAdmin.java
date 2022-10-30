package ru.practicum.ewm.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.category.client.CategoryClientAdmin;
import ru.practicum.ewm.category.dto.CategoryDto;
import ru.practicum.ewm.category.dto.NewCategoryDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories")
public class CategoryControllerAdmin {
    private final CategoryClientAdmin categoryClientAdmin;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody NewCategoryDto categoryDto) {
        return categoryClientAdmin.createCategory(categoryDto);
    }

    @PatchMapping
    public ResponseEntity<Object> update(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryClientAdmin.updateCategory(categoryDto);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<Object> delete(@PathVariable("catId") @Positive Long categoryId) {
        return categoryClientAdmin.deleteCategory(categoryId);
    }
}
