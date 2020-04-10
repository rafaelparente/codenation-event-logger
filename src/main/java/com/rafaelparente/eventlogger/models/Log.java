package com.rafaelparente.eventlogger.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Log {

    @Id
    private Long id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Event event;

    @NotNull
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    @CreatedDate
    private LocalDateTime createdAt;

}
