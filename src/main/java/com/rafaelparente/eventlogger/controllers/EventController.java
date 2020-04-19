package com.rafaelparente.eventlogger.controllers;

import com.rafaelparente.eventlogger.assemblers.EventDTOModelAssembler;
import com.rafaelparente.eventlogger.assemblers.EventModelAssembler;
import com.rafaelparente.eventlogger.exceptions.EventNotFoundException;
import com.rafaelparente.eventlogger.models.Event;
import com.rafaelparente.eventlogger.models.EventLevel;
import com.rafaelparente.eventlogger.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventModelAssembler eventModelAssembler;

    @Autowired
    private EventDTOModelAssembler eventDTOModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<?>> findMultipleEvents(Pageable pageable,
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
        List<EntityModel<?>> events = this.eventService.findMultiple(pageable, level,
                description.map(s -> s.isEmpty() ? null : s.replace('~', '%')),
                source, year, month, day, hours, minutes, seconds, quantity)
                .stream().map(eventDTOModelAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(events,
                linkTo(EventController.class).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEventById(@PathVariable Long id) {
        Event event = this.eventService.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        EntityModel<?> entityModel = eventModelAssembler.toModel(event);

        return ResponseEntity
                .ok()
                .location(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping
    public ResponseEntity<?> saveEvent(@RequestBody Event event) {
        Event savedEvent = this.eventService.save(event);
        EntityModel<?> entityModel = eventModelAssembler.toModel(savedEvent);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeEvent(@RequestBody Event event, @PathVariable Long id) {
        Event changedEvent = this.eventService.change(event, id)
                .orElseThrow(() -> new EventNotFoundException(id));
            EntityModel<?> entityModel = eventModelAssembler.toModel(changedEvent);

            return ResponseEntity
                    .ok()
                    .location(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateEvent(@RequestBody Event event, @PathVariable Long id) {
        Event updatedEvent = this.eventService.update(event, id)
                .orElseThrow(() -> new EventNotFoundException(id));
        EntityModel<?> entityModel = eventModelAssembler.toModel(updatedEvent);

        return ResponseEntity
                .ok()
                .location(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        this.eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
