package com.rafaelparente.eventlogger.services;

import com.rafaelparente.eventlogger.models.Event;
import com.rafaelparente.eventlogger.models.EventLevel;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventService {

    List<?> findMultiple(Pageable pageable,
                         Optional<EventLevel> level,
                         Optional<String> description,
                         Optional<String> source,
                         Optional<Integer> year,
                         Optional<Integer> month,
                         Optional<Integer> day,
                         Optional<Integer> quantity);

    Event save(Event event);

}
