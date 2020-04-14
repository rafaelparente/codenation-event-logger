package com.rafaelparente.eventlogger.repositories;

import com.rafaelparente.eventlogger.models.Event;
import com.rafaelparente.eventlogger.dto.EventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    @Query("SELECT new com.rafaelparente.eventlogger.dto.EventDTO(e.level, e.description, e.source, e.date, e.quantity) FROM Event e")
    Page<EventDTO> findMultiple(Pageable pageable);

}
