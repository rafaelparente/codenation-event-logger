package com.rafaelparente.eventlogger.controllers;

import com.rafaelparente.eventlogger.models.Event;
import com.rafaelparente.eventlogger.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public Iterable<?> findMultiple(Pageable pageable) {
        return this.eventService.findMultiple(pageable);
    }

    @PostMapping
    public Event save(@RequestBody Event event) {
        return this.eventService.save(event);
    }

}
