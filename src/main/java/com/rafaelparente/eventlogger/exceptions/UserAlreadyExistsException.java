package com.rafaelparente.eventlogger.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super("An account for the username '" + username + "' already exists.");
    }

}
