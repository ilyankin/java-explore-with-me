package ru.practicum.ewm.service.adminapi.category;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.getters.category.CategoryGetter;
import ru.practicum.ewm.mappers.category.CategoryMapper;
import ru.practicum.ewm.models.dtos.category.CategoryDto;
import ru.practicum.ewm.models.dtos.category.CategoryNewDto;
import ru.practicum.ewm.repositories.category.CategoryRepository;

@Service
@AllArgsConstructor
public class CategoryServiceAdminImpl implements CategoryServiceAdmin {
    private final CategoryRepository categoryRepository;
    private final CategoryGetter categoryGetter;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CategoryNewDto categoryDto) {
        val category = categoryRepository.save(categoryMapper.to(categoryDto));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        val category = categoryGetter.getOrThrow(categoryDto.getId());
        if (categoryDto.getName() != null) category.setName(categoryDto.getName());
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(long categoryId) {
        categoryGetter.getOrThrow(categoryId);
        categoryRepository.deleteById(categoryId);
    }
}
