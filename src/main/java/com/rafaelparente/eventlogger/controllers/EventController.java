package com.rafaelparente.eventlogger.controllers;

import com.rafaelparente.eventlogger.models.Event;
import com.rafaelparente.eventlogger.models.EventLevel;
import com.rafaelparente.eventlogger.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<?> findMultiple(Pageable pageable,
                                @RequestParam Optional<EventLevel> level,
                                @RequestParam Optional<String> description,
                                @RequestParam Optional<String> source,
                                @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> date,
                                @RequestParam Optional<Integer> quantity) {
        return this.eventService.findMultiple(pageable, level, description, source, date, quantity);
    }

    @PostMapping
    public Event save(@RequestBody Event event) {
        return this.eventService.save(event);
    }

}
