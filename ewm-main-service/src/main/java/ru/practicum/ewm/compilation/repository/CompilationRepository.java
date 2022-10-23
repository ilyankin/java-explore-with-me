package ru.practicum.ewm.compilation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.compilation.model.Compilation;

import javax.transaction.Transactional;

@Repository
public interface CompilationRepository extends JpaRepository<Compilation, Long>,
        QuerydslPredicateExecutor<Compilation> {

    @Modifying
    @Query(value = "insert into event_compilation (compilation_id, event_id) values (:id, :eventId)",
            nativeQuery = true)
    @Transactional
    void addEventToCompilation(@Param("id") long id, @Param("eventId") long eventId);

    @Modifying
    @Query(value = "delete from event_compilation where compilation_id = :id and event_id = :eventId",
            nativeQuery = true)
    @Transactional
    void removeEventToCompilation(@Param("id") long id, @Param("eventId") long eventId);

    @Modifying
    @Query(value = "update Compilation set pinned = true where id = :compId")
    @Transactional
    void pinCompilation(@Param("compId") long compilationId);

    @Modifying
    @Query(value = "update Compilation set pinned = false where id = :compId")
    @Transactional
    void unpinCompilation(@Param("compId") long compilationId);
}
