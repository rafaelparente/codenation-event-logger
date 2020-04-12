package com.rafaelparente.eventlogger.services;

import com.rafaelparente.eventlogger.models.Event;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {

    List<?> findMultiple(Pageable pageable);

    Event save(Event event);

}
