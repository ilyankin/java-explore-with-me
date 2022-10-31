package ru.practicum.ewm.controllers.adminapi.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.models.dtos.category.CategoryDto;
import ru.practicum.ewm.models.dtos.category.CategoryNewDto;
import ru.practicum.ewm.service.adminapi.category.CategoryServiceAdmin;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class CategoryControllerAdmin {
    private final CategoryServiceAdmin categoryService;

    @PostMapping("/categories")
    public CategoryDto create(@RequestBody CategoryNewDto categoryNewDto) {
        return categoryService.createCategory(categoryNewDto);
    }

    @PatchMapping("/categories")
    public CategoryDto update(@RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }

    @DeleteMapping("/categories/{catId}")
    public void delete(@PathVariable("catId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
