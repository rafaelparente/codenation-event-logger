package com.rafaelparente.eventlogger.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rafaelparente.eventlogger.models.EventLevel;
import lombok.Value;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Value
@Relation(collectionRelation = "events")
public class EventDTO {

    @JsonIgnore
    private Long id;

    private EventLevel level;

    private String description;

    private String source;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    private Integer quantity;

}
