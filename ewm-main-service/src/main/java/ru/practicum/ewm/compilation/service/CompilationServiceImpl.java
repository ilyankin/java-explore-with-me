package ru.practicum.ewm.compilation.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.compilation.dto.CompilationDto;
import ru.practicum.ewm.compilation.dto.NewCompilationDto;
import ru.practicum.ewm.compilation.getter.CompilationGetter;
import ru.practicum.ewm.compilation.mapper.CompilationMapper;
import ru.practicum.ewm.compilation.model.Compilation;
import ru.practicum.ewm.compilation.model.QCompilation;
import ru.practicum.ewm.compilation.repository.CompilationRepository;
import ru.practicum.ewm.event.getter.EventGetter;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
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

    @Override
    public Collection<CompilationDto> getAllCompilations(Boolean pinned, int from, int size) {
        final Page<Compilation> compilations;
        val page = PageRequest.of(from / size, size);
        if (pinned == null) {
            compilations = compilationRepository.findAll(page);
        } else {
            compilations = compilationRepository.findAll(QCompilation.compilation.pinned.eq(pinned), page);
        }
        return compilationMapper.toDto(compilations.getContent());
    }

    @Override
    public CompilationDto getCompilation(long compilationId) {
        return compilationMapper.toDto(compilationGetter.getOrThrow(compilationId));
    }
}
