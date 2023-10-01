package com.lunaticdevs.urlredirector.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Saransh Kumar
 */

@Controller
public record LoginController() {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage() {
        return "error/403";
    }
}
