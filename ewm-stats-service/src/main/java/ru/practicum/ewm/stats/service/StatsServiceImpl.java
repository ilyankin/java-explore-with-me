package ru.practicum.ewm.stats.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.stats.mapper.EndpointHitMapper;
import ru.practicum.ewm.stats.model.dto.EndpointHitDto;
import ru.practicum.ewm.stats.model.dto.ViewStats;
import ru.practicum.ewm.stats.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Service
@AllArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;
    private final EndpointHitMapper endpointHitMapper;

    @Override
    public EndpointHitDto createStatistics(EndpointHitDto endpointHitDto) {
        return endpointHitMapper.toDto(statsRepository.save(endpointHitMapper.to(endpointHitDto)));
    }

    @Override
    public Collection<ViewStats> getStatistics(LocalDateTime start, LocalDateTime end,
                                               Set<String> uris, boolean unique) {
        if (uris == null) {
            return unique ? statsRepository.findAllUnique(start, end) : statsRepository.findAll(start, end);
        }

        val viewStats = new ArrayList<ViewStats>();

        ViewStats stats;
        for (String uri : uris) {
            if (unique) {
                stats = statsRepository.findUnique(start, end, uri);
            } else {
                stats = statsRepository.find(start, end, uri);
            }
            viewStats.add(stats);
        }

        return viewStats;
    }
}
