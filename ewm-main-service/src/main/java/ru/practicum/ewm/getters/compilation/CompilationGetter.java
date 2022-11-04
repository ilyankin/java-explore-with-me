package ru.practicum.ewm.getters.compilation;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.exceptions.EntityNotFoundException;
import ru.practicum.ewm.exceptions.compilation.CompilationByIdNotFoundException;
import ru.practicum.ewm.getters.ThrownGettable;
import ru.practicum.ewm.models.entities.compilation.Compilation;
import ru.practicum.ewm.repositories.compilation.CompilationRepository;

@Component
@RequiredArgsConstructor
public class CompilationGetter implements ThrownGettable<Compilation, Long> {
    private final CompilationRepository compilationRepository;

    @Override
    public Compilation getOrThrow(Long compilationId) throws EntityNotFoundException {
        val compilation = compilationRepository.findById(compilationId);
        if (compilation.isEmpty()) throw new CompilationByIdNotFoundException(compilationId);
        return compilation.get();
    }
}
