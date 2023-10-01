package com.lunaticdevs.urlredirector.exception;

/**
 * @author Saransh Kumar
 */

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        this("Username or email already in use");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
