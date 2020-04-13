package com.rafaelparente.eventlogger.repositories;

import com.rafaelparente.eventlogger.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    @Query("SELECT e.level, e.date, e.description, e.source, e.quantity FROM Event e")
    Page<?> findMultiple(Pageable pageable);

}
