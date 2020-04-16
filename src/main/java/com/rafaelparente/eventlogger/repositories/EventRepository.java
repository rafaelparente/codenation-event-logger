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

import java.util.Optional;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    @Query("SELECT new com.rafaelparente.eventlogger.dto.EventDTO(e.level, e.description, e.source, e.date, e.quantity) FROM Event e " +
            "WHERE (:level is null or e.level = :level) and " +
            "(:description is null or e.description LIKE :description) and " +
            "(:source is null  or e.source LIKE :source) and " +
            "(:year is null or year(e.date) = :year) and " +
            "(:month is null or month(e.date) = :month) and " +
            "(:day is null or day(e.date) = :day) and " +
            "(:quantity is null or e.quantity = :quantity)")
    Page<EventDTO> findMultiple(Pageable pageable,
                                @Param("level") Optional<EventLevel> level,
                                @Param("description") Optional<String> description,
                                @Param("source") Optional<String> source,
                                @Param("year") Optional<Integer> year,
                                @Param("month") Optional<Integer> month,
                                @Param("day") Optional<Integer> day,
                                @Param("quantity") Optional<Integer> quantity);

}
