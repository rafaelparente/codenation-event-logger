package com.rafaelparente.eventlogger.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    @NotNull
    private Log log;

    @NotNull
    private EventLevel level;

    public Event(EventLevel level, Log log) {
        this.level = level;
        this.log = log;
        this.log.setEvent(this);
    }

}
