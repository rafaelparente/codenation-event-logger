package com.rafaelparente.eventlogger.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime date;

    @NotNull
    @Positive
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
