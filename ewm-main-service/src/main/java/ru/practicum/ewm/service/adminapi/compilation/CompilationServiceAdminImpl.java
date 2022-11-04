package ru.practicum.ewm.service.adminapi.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.getters.compilation.CompilationGetter;
import ru.practicum.ewm.getters.event.EventGetter;
import ru.practicum.ewm.mappers.compilation.CompilationMapper;
import ru.practicum.ewm.models.dtos.compilation.CompilationDto;
import ru.practicum.ewm.models.dtos.compilation.NewCompilationDto;
import ru.practicum.ewm.repositories.compilation.CompilationRepository;

@Service
@RequiredArgsConstructor
public class CompilationServiceAdminImpl implements CompilationServiceAdmin {
    private final CompilationRepository compilationRepository;
    private final CompilationMapper compilationMapper;
    private final CompilationGetter compilationGetter;
    private final EventGetter eventGetter;

    @Override
    public CompilationDto createCompilation(NewCompilationDto compilationDto) {
        return compilationMapper.toDto(compilationRepository.save(compilationMapper.to(compilationDto)));
    }

    @Override
    public void deleteCompilation(long compilationId) {
        compilationRepository.deleteById(compilationId);
    }

    @Override
    public void addEventToCompilation(long compilationId, long eventId) {
        compilationGetter.getOrThrow(compilationId);
        eventGetter.getOrThrow(eventId);
        compilationRepository.addEventToCompilation(compilationId, eventId);
    }

    @Override
    public void removeEventFromCompilation(long compilationId, long eventId) {
        compilationRepository.removeEventToCompilation(compilationId, eventId);
    }

    @Override
    public void pinCompilation(long compilationId) {
        compilationRepository.pinCompilation(compilationId);
    }

    @Override
    public void unpinCompilation(long compilationId) {
        compilationRepository.unpinCompilation(compilationId);
    }
}
