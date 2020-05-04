package com.rafaelparente.eventlogger.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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

    @NotNull
    @JsonIgnore
    private String username;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    @ApiModelProperty(required = true)
    private EventLevel level;

    @NotNull
    @ApiModelProperty(required = true)
    private String description;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull
    @ApiModelProperty(required = true)
    @Setter(AccessLevel.NONE) private Log log;

    @NotNull
    @ApiModelProperty(required = true)
    private String source;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @ApiModelProperty(example = "2020-05-03 20:51:55")
    private LocalDateTime date;

    @NotNull
    @Positive
    @ApiModelProperty(allowableValues = "range[1, infinity]", example = "1")
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
