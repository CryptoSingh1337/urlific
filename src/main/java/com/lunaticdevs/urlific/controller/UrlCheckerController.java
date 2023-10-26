package com.lunaticdevs.urlific.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Saransh Kumar
 */

@Controller
@RequestMapping("/url-checker")
public record UrlCheckerController() {

    @GetMapping
    public String getUrlCheckerPage() {
        return "url-checker";
    }
}
