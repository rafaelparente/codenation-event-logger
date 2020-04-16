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
                                @RequestParam Optional<Integer> year,
                                @RequestParam Optional<Integer> month,
                                @RequestParam Optional<Integer> day,
                                @RequestParam Optional<Integer> quantity) {
        return this.eventService.findMultiple(pageable, level,
                description.map((s) -> s.replace('~', '%')),
                source.map((s) -> s.replace('~', '%')),
                date.map((d) -> Optional.of(d.getYear())).orElse(year),
                date.map((d) -> Optional.of(d.getMonthValue())).orElse(month),
                date.map((d) -> Optional.of(d.getDayOfMonth())).orElse(day),
                quantity);
    }

    @PostMapping
    public Event save(@RequestBody Event event) {
        return this.eventService.save(event);
    }

}
