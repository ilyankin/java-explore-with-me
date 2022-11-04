package ru.practicum.ewm.exceptions.compilation;

import ru.practicum.ewm.exceptions.EntityNotFoundException;

public class CompilationByIdNotFoundException extends EntityNotFoundException {
    public CompilationByIdNotFoundException(Long compilationId) {
        super("compilation", "id", compilationId);
    }
}
