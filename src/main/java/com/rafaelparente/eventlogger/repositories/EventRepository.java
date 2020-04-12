package com.rafaelparente.eventlogger.repositories;

import com.rafaelparente.eventlogger.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    @Query("SELECT e.date, e.description, e.level, e.quantity, e.source FROM Event e")
    public Page<?> findMultiple(Pageable pageable);

}
