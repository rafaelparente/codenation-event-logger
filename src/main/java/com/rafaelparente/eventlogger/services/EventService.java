package com.rafaelparente.eventlogger.services;

import com.rafaelparente.eventlogger.models.Event;
import com.rafaelparente.eventlogger.models.EventLevel;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventService {

    List<?> findMultiple(Pageable pageable,
                         Optional<List<EventLevel>> level,
                         Optional<String> description,
                         Optional<List<String>> source,
                         Optional<List<Integer>> year,
                         Optional<List<Integer>> month,
                         Optional<List<Integer>> day,
                         Optional<List<Integer>> quantity);

    Optional<Event> findById(Long id);

    Event save(Event event);

    Event change(Event event, Long id);

    Optional<Event> update(Event event, Long id);

    void delete(Long id);

}
