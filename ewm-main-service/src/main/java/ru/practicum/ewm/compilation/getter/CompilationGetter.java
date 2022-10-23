package ru.practicum.ewm.compilation.getter;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.compilation.model.Compilation;
import ru.practicum.ewm.compilation.repository.CompilationRepository;
import ru.practicum.ewm.other.exception.EntityNotFoundException;
import ru.practicum.ewm.other.getter.ThrownGettable;

@Component
@RequiredArgsConstructor
public class CompilationGetter implements ThrownGettable<Compilation, Long> {
    private final CompilationRepository compilationRepository;

    @Override
    public Compilation getOrThrow(Long compilationId) throws EntityNotFoundException {
        val compilation = compilationRepository.findById(compilationId);
        if (compilation.isEmpty()) throw new EntityNotFoundException("Compilation", "id", compilationId);
        return compilation.get();
    }
}
