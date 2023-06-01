package com.lunaticdevs.urlredirector.exception;

/**
 * author: Saransh Kumar
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        this("User not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
