package com.rafaelparente.eventlogger.exceptions.advice;

import com.rafaelparente.eventlogger.exceptions.EventNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EventNotFoundAdvice {

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String eventNotFoundHandler(EventNotFoundException ex) {
        return ex.getMessage();
    }

}
