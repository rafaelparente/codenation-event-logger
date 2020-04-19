package com.rafaelparente.eventlogger.controllers;

import com.rafaelparente.eventlogger.exceptions.EventNotFoundException;
import com.rafaelparente.eventlogger.models.Event;
import com.rafaelparente.eventlogger.models.EventLevel;
import com.rafaelparente.eventlogger.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<?> findMultipleEvents(Pageable pageable,
                                      @RequestParam Optional<List<EventLevel>> level,
                                      @RequestParam Optional<String> description,
                                      @RequestParam Optional<List<String>> source,
                                      @RequestParam Optional<List<Integer>> year,
                                      @RequestParam Optional<List<Integer>> month,
                                      @RequestParam Optional<List<Integer>> day,
                                      @RequestParam Optional<List<Integer>> hours,
                                      @RequestParam Optional<List<Integer>> minutes,
                                      @RequestParam Optional<List<Integer>> seconds,
                                      @RequestParam Optional<List<Integer>> quantity) {
        return this.eventService.findMultiple(pageable, level,
                description.map(s -> s.isEmpty() ? null : s.replace('~', '%')),
                source, year, month, day, hours, minutes, seconds, quantity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEventById(@PathVariable Long id) {
        Event event = this.eventService.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        return ResponseEntity
                .ok()
                .body(event);
    }

    @PostMapping
    public ResponseEntity<?> saveEvent(@RequestBody Event event) {
        Event savedEvent = this.eventService.save(event);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeEvent(@RequestBody Event event, @PathVariable Long id) {
        Event changedEvent = this.eventService.change(event, id)
                .orElseThrow(() -> new EventNotFoundException(id));

            return ResponseEntity
                    .ok()
                    .body(changedEvent);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateEvent(@RequestBody Event event, @PathVariable Long id) {
        Event updatedEvent = this.eventService.update(event, id)
                .orElseThrow(() -> new EventNotFoundException(id));

        return ResponseEntity
                .ok()
                .body(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        this.eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
