package com.rafaelparente.eventlogger.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Log {

    @Id
    @JsonIgnore
    private Long id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Event event;

    @NotNull
    private String text;

}
