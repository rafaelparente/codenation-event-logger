package com.rafaelparente.eventlogger.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super("An account with the username '" + username + "' already exists.");
    }

}
