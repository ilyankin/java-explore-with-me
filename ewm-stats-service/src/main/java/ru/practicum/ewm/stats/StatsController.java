package ru.practicum.ewm.stats;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.stats.model.dto.EndpointHitDto;
import ru.practicum.ewm.stats.model.dto.ViewStats;
import ru.practicum.ewm.stats.service.StatsService;
import ru.practicum.ewm.stats.util.UrlUtil;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class StatsController {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final StatsService statsService;

    @PostMapping("/hit")
    public EndpointHitDto create(@RequestBody EndpointHitDto endpointHit) {
        return statsService.createStatistics(endpointHit);
    }

    @GetMapping("/stats")
    public Collection<ViewStats> stats(@RequestParam String start,
                                       @RequestParam String end,
                                       @RequestParam(required = false) Set<String> uris,
                                       @RequestParam(required = false, defaultValue = "false") boolean unique) {
        val dateTimes = UrlUtil.decodeLocalDateTime(formatter, start, end);
        val startDateTime = dateTimes.get(0);
        val endDateTime = dateTimes.get(1);
        return statsService.getStatistics(startDateTime, endDateTime, uris, unique);
    }
}
