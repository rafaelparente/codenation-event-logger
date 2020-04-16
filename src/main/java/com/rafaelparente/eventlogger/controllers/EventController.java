package com.rafaelparente.eventlogger.controllers;

import com.rafaelparente.eventlogger.models.Event;
import com.rafaelparente.eventlogger.models.EventLevel;
import com.rafaelparente.eventlogger.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<?> findMultiple(Pageable pageable,
                                @RequestParam Optional<List<EventLevel>> level,
                                @RequestParam Optional<String> description,
                                @RequestParam Optional<List<String>> source,
                                @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> date,
                                @RequestParam Optional<List<Integer>> year,
                                @RequestParam Optional<List<Integer>> month,
                                @RequestParam Optional<List<Integer>> day,
                                @RequestParam Optional<List<Integer>> quantity) {
        return this.eventService.findMultiple(pageable, level,
                description.map(s -> s.isEmpty() ? null : s.replace('~', '%')),
                source,
                date.map((d) -> Optional.of(Collections.singletonList(d.getYear()))).orElse(year),
                date.map((d) -> Optional.of(Collections.singletonList(d.getMonthValue()))).orElse(month),
                date.map((d) -> Optional.of(Collections.singletonList(d.getDayOfMonth()))).orElse(day),
                quantity);
    }

    @GetMapping("/{id}")
    public Optional<Event> findById(@PathVariable Long id) {
        return this.eventService.findById(id);
    }

    @PostMapping
    public Event save(@RequestBody Event event) {
        return this.eventService.save(event);
    }

}
