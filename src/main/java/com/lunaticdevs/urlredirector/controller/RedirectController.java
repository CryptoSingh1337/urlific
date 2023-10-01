package com.lunaticdevs.urlredirector.controller;

import com.lunaticdevs.urlredirector.entity.Link;
import com.lunaticdevs.urlredirector.exception.LinkNotFoundException;
import com.lunaticdevs.urlredirector.service.LinkService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @author Saransh Kumar
 */

@Slf4j
@Controller
@RequestMapping("/link")
public record RedirectController(LinkService linkService) {

    @GetMapping("/{username}/{name}")
    public void redirect(@PathVariable String username, @PathVariable String name,
                         HttpServletResponse response) throws IOException {
        try {
            Link link = linkService.getByUsernameAndName(username, name);
            response.sendRedirect(link.getRedirectUrl());
        } catch (LinkNotFoundException e) {
            response.sendRedirect("/");
        }
    }
}
