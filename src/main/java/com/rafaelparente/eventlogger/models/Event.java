package com.rafaelparente.eventlogger.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    @NotNull
    private String description;

    @NotNull
    private String source;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    private LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

    @NotNull
    private Integer quantity = 1;

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
