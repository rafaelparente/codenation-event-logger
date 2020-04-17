package com.rafaelparente.eventlogger.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class Log {

    @Id
    private Long id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Event event;

    @NotNull
    private String text;

    @JsonFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime time;

}
