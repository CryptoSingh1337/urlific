package com.lunaticdevs.urlific.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lunaticdevs.urlific.dto.UserDTO;
import com.lunaticdevs.urlific.exception.LinkNotFoundException;
import com.lunaticdevs.urlific.exception.UserAlreadyExistsException;
import com.lunaticdevs.urlific.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Saransh Kumar
 */

@Slf4j
@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException e) {
        log.error("Exception: {}", e.getMessage());
        return "redirect:/login?error";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        log.error("Exception: {}", e.getMessage());
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new UserDTO());
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(LinkNotFoundException.class)
    public String handleLinkNotFoundException(LinkNotFoundException e) {
        log.error("Exception: {}", e.getMessage());
        return "error/404";
    }

    @ExceptionHandler(JWTVerificationException.class)
    public String handleJwtVerificationException(JWTVerificationException e) {
        log.error("Exception: {}", e.getMessage());
        return "error/400";
    }
}
