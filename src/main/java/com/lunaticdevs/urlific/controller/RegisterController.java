package com.lunaticdevs.urlific.controller;

import com.lunaticdevs.urlific.dto.UserDTO;
import com.lunaticdevs.urlific.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/verify")
    public ModelAndView verifyEmail(@RequestParam String token) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login?success");
        userService.verifyEmail(token);
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
        log.info("Registering user with username: {}", userDTO.getUsername());
        userService.save(userDTO);
        modelAndView.setViewName("redirect:/login?verify");
        return modelAndView;
    }
}
