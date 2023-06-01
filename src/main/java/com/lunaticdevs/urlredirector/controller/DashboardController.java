package com.lunaticdevs.urlredirector.controller;

import com.lunaticdevs.urlredirector.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: Saransh Kumar
 */
@Controller
@RequestMapping("/dashboard")
public record DashboardController(UserService userService) {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String dashboardPage() {
        return "dashboard";
    }

    @GetMapping("/statistics")
    @PreAuthorize("hasRole('USER')")
    public String statisticPage() {
        return "statistics";
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public void createPage() {
        return ;
    }
}
