package ru.practicum.ewm.category.getter;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.category.model.Category;
import ru.practicum.ewm.category.reopository.CategoryRepository;
import ru.practicum.ewm.other.exception.EntityNotFoundException;
import ru.practicum.ewm.other.getter.ThrownGettable;

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
