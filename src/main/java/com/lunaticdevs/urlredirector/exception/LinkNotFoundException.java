package com.lunaticdevs.urlredirector.exception;

/**
 * @author Saransh Kumar
 */

public class LinkNotFoundException extends RuntimeException {

    public LinkNotFoundException() {
        this("Link not found");
    }

    public LinkNotFoundException(String message) {
        super(message);
    }
}
