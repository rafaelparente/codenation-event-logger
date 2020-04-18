package com.rafaelparente.eventlogger.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.rafaelparente.eventlogger.controllers.EventController;
import com.rafaelparente.eventlogger.dto.EventDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EventDTOModelAssembler implements RepresentationModelAssembler<EventDTO, EntityModel<EventDTO>> {

    @Override
    public EntityModel<EventDTO> toModel(EventDTO event) {
        return new EntityModel<>(event,
                linkTo(methodOn(EventController.class).findEventById(event.getId())).withSelfRel()); //linkTo(methodOn(EventController.class).all()).withRel("events")
    }

}
