package com.rafaelparente.eventlogger.exceptions.advice;

import com.rafaelparente.eventlogger.exceptions.UserAlreadyExistsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserAlreadyExistsAdvice {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.OK)
    public String userAlreadyExistsHandler(UserAlreadyExistsException ex) {
        return ex.getMessage();
    }

}
