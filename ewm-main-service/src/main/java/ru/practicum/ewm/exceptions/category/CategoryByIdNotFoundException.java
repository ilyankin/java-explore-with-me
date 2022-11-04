package ru.practicum.ewm.exceptions.category;

import ru.practicum.ewm.exceptions.EntityNotFoundException;

public class CategoryByIdNotFoundException extends EntityNotFoundException {
    public CategoryByIdNotFoundException(Long categoryId) {
        super("category", "id", categoryId);
    }
}
