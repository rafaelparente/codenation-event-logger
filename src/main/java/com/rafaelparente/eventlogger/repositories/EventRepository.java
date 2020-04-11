package com.rafaelparente.eventlogger.repositories;

import com.rafaelparente.eventlogger.models.Event;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

}
