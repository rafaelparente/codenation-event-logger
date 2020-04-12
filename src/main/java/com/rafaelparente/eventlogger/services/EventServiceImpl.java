package com.rafaelparente.eventlogger.services;

import com.rafaelparente.eventlogger.models.Event;
import com.rafaelparente.eventlogger.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<?> findMultiple(Pageable pageable) {
        return this.eventRepository.findMultiple(pageable).getContent();
    }

    @Override
    public Event save(Event event) {
        return this.eventRepository.save(event);
    }

}
