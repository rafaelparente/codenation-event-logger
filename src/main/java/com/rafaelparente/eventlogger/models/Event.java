package com.rafaelparente.eventlogger.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private EventLevel level;

    @NotNull
    private String description;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull
    @Setter(AccessLevel.NONE) private Log log;

    @NotNull
    private String source;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate date;

    @NotNull
    private Integer quantity;

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
