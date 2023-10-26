package com.lunaticdevs.urlific.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Saransh Kumar
 */

@Controller
@RequestMapping("/user")
public record UserController() {

    @GetMapping("/profile")
    public String profilePage() {
        return "user/profile";
    }

    @GetMapping("/settings")
    public String settingsPage() {
        return "user/settings";
    }
}
