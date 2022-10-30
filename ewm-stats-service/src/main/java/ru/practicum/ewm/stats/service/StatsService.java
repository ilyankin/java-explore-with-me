package ru.practicum.ewm.stats.service;

import ru.practicum.ewm.stats.model.dto.EndpointHitDto;
import ru.practicum.ewm.stats.model.dto.ViewStats;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

public interface StatsService {
    EndpointHitDto createStatistics(EndpointHitDto endpointHitDto);

    Collection<ViewStats> getStatistics(LocalDateTime start, LocalDateTime end, Set<String> uris, boolean unique);
}
