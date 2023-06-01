package com.lunaticdevs.urlredirector.controller;

import com.lunaticdevs.urlredirector.dto.UserDTO;
import com.lunaticdevs.urlredirector.service.UserService;
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
 * author: Saransh Kumar
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
    public String registerUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult result) {
        log.debug("Registering user with username: {}", userDTO.getUsername());
        if (result.hasErrors()) {
            return "register";
        }
        userService.save(userDTO);
        return "redirect:/login";
    }
}
