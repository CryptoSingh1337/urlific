package com.lunaticdevs.urlific.controller;

import com.lunaticdevs.urlific.dto.UserDTO;
import com.lunaticdevs.urlific.exception.UserAlreadyExistsException;
import com.lunaticdevs.urlific.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Saransh Kumar
 */

@Slf4j
@Controller
@RequestMapping("/register")
public record RegisterController(UserService userService) {

    @GetMapping
    public ModelAndView registerPage() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new UserDTO());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView registerUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.addObject("user", userDTO);
            modelAndView.setViewName("register");
            return modelAndView;
        }
        log.debug("Registering user with username: {}", userDTO.getUsername());
        try {
            userService.save(userDTO);
            modelAndView.setViewName("redirect:/login");
        } catch (UserAlreadyExistsException e) {
            modelAndView.addObject("message", e.getMessage());
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }
}
