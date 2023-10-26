package com.lunaticdevs.urlific.exception;

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
