package com.rafaelparente.eventlogger.repositories;

import com.rafaelparente.eventlogger.models.Event;
import com.rafaelparente.eventlogger.dto.EventDTO;
import com.rafaelparente.eventlogger.models.EventLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    @Query("SELECT new com.rafaelparente.eventlogger.dto.EventDTO(e.level, e.description, e.source, e.date, e.quantity) FROM Event e " +
            "WHERE (COALESCE(:level) is null or e.level in :level) " +
            "and (:description is null or e.description like :description) " +
            "and (COALESCE(:source) is null or e.source in :source) " +
            "and (COALESCE(:year) is null or year(e.date) in :year) " +
            "and (COALESCE(:month) is null or month(e.date) in :month) " +
            "and (COALESCE(:day) is null or day(e.date) in :day) " +
            "and (COALESCE(:quantity) is null or e.quantity in :quantity)")
    Page<EventDTO> findMultiple(Pageable pageable,
                                @Param("level") Optional<List<EventLevel>> level,
                                @Param("description") Optional<String> description,
                                @Param("source") Optional<List<String>> source,
                                @Param("year") Optional<List<Integer>> year,
                                @Param("month") Optional<List<Integer>> month,
                                @Param("day") Optional<List<Integer>> day,
                                @Param("quantity") Optional<List<Integer>> quantity);

}
