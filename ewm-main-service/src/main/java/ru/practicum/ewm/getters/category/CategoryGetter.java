package ru.practicum.ewm.getters.category;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.exceptions.EntityNotFoundException;
import ru.practicum.ewm.getters.ThrownGettable;
import ru.practicum.ewm.models.entities.category.Category;
import ru.practicum.ewm.repositories.category.CategoryRepository;

@Component
@RequiredArgsConstructor
public class CategoryGetter implements ThrownGettable<Category, Long> {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getOrThrow(Long categoryId) throws EntityNotFoundException {
        val category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) throw new EntityNotFoundException("Category", "id", categoryId);
        return category.get();
    }
}
