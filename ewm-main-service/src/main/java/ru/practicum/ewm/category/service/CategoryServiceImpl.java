package ru.practicum.ewm.category.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.category.dto.CategoryDto;
import ru.practicum.ewm.category.dto.CategoryNewDto;
import ru.practicum.ewm.category.mapper.CategoryMapper;
import ru.practicum.ewm.category.reopository.CategoryRepository;
import ru.practicum.ewm.category.getter.CategoryGetter;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
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

    @Override
    public CategoryDto getCategory(long categoryId) {
        return categoryMapper.toDto(categoryGetter.getOrThrow(categoryId));
    }

    @Override
    public Collection<CategoryDto> getAllCategories(int from, int size) {
        return categoryMapper.toDto(categoryRepository.findAll(PageRequest.of(from / size, size)).getContent());
    }
}
