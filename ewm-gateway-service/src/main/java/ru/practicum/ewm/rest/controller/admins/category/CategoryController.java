package ru.practicum.ewm.rest.controller.admins.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.category.NewCategoryDto;
import ru.practicum.ewm.rest.client.admins.category.CategoryClient;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories")
public class CategoryController {
    private final CategoryClient categoryClient;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody NewCategoryDto categoryDto) {
        return categoryClient.createCategory(categoryDto);
    }

    @PatchMapping
    public ResponseEntity<Object> update(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryClient.updateCategory(categoryDto);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<Object> delete(@PathVariable("catId") @Positive Long categoryId) {
        return categoryClient.deleteCategory(categoryId);
    }
}
