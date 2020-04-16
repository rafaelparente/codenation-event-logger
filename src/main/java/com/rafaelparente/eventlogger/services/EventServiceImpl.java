package com.rafaelparente.eventlogger.services;

import com.rafaelparente.eventlogger.models.Event;
import com.rafaelparente.eventlogger.models.EventLevel;
import com.rafaelparente.eventlogger.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<?> findMultiple(Pageable pageable,
                                Optional<List<EventLevel>> level,
                                Optional<String> description,
                                Optional<List<String>> source,
                                Optional<List<Integer>> year,
                                Optional<List<Integer>> month,
                                Optional<List<Integer>> day,
                                Optional<List<Integer>> quantity) {
        return this.eventRepository.findMultiple(pageable, level, description, source, year, month, day, quantity).getContent();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public Event save(Event event) {
        return this.eventRepository.save(event);
    }

}
