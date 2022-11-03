package ru.practicum.ewm.service.publicapi.compilation;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.getters.compilation.CompilationGetter;
import ru.practicum.ewm.mappers.compilation.CompilationMapper;
import ru.practicum.ewm.models.dtos.compilation.CompilationDto;
import ru.practicum.ewm.models.entities.compilation.Compilation;
import ru.practicum.ewm.models.entities.compilation.QCompilation;
import ru.practicum.ewm.repositories.compilation.CompilationRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CompilationServicePublicImpl implements CompilationServicePublic {

    private final CompilationRepository compilationRepository;
    private final CompilationMapper compilationMapper;
    private final CompilationGetter compilationGetter;

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
