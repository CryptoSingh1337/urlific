package com.lunaticdevs.urlredirector.controller;

import com.lunaticdevs.urlredirector.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * author: Saransh Kumar
 */
@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException e) {
        return "redirect:/login?error";
    }
}
