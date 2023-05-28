package com.lunaticdevs.urlredirector.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: Saransh Kumar
 */
@Controller
@RequestMapping("/admin/dashboard")
public class AdminDashboardController {

    @GetMapping
    public String adminDashboardPage() {
        return "admin-dashboard";
    }
}
