package ru.practicum.ewm.stats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.ewm.stats.model.EndpointHit;
import ru.practicum.ewm.stats.model.dto.ViewStats;

import java.time.LocalDateTime;
import java.util.Collection;

public interface StatsRepository extends JpaRepository<EndpointHit, Long> {

    @Query("select " +
            "new ru.practicum.ewm.stats.model.dto.ViewStats(e.app, e.uri, COUNT(e.ip)) " +
            "from EndpointHit e " +
            "where e.uri like :uri " +
            "and e.timestamp between :start and :end " +
            "group by e.app, e.uri")
    ViewStats find(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("uri") String uri);

    @Query("select " +
            "new ru.practicum.ewm.stats.model.dto.ViewStats(e.app, e.uri, COUNT(e.ip)) " +
            "from EndpointHit e " +
            "where e.timestamp between :start and :end " +
            "group by e.app, e.uri")
    Collection<ViewStats> findAll(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("select " +
            "new ru.practicum.ewm.stats.model.dto.ViewStats(e.app, e.uri, COUNT(distinct e.ip)) " +
            "from EndpointHit e " +
            "where e.uri like :uri " +
            "and e.timestamp between :start and :end " +
            "group by e.app, e.uri")
    ViewStats findUnique(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("uri") String uri);

    @Query("select " +
            "new ru.practicum.ewm.stats.model.dto.ViewStats(e.app, e.uri, COUNT(distinct e.ip)) " +
            "from EndpointHit e " +
            "where e.timestamp between :start and :end " +
            "group by e.app, e.uri")
    Collection<ViewStats> findAllUnique(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
