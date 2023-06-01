package com.lunaticdevs.urlredirector.controller;

import com.lunaticdevs.urlredirector.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * author: Saransh Kumar
 */
@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleUserNotFoundException(UserNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login?error");
        return modelAndView;
    }
}
