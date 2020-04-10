package com.rafaelparente.eventlogger.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Setter(AccessLevel.NONE) private Log log;

    @NotNull
    private EventLevel level;

    public void setLog(Log log) {
        if (log == null) {
            if (this.log != null) {
                this.log.setEvent(null);
            }
        } else {
            log.setEvent(this);
        }
        this.log = log;
    }

}
