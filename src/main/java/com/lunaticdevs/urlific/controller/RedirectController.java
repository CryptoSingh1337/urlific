package com.lunaticdevs.urlific.controller;

import com.lunaticdevs.urlific.entity.Link;
import com.lunaticdevs.urlific.service.LinkService;
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
        Link link = linkService.getByUsernameAndName(username, name);
        response.sendRedirect(link.getRedirectUrl());
    }
}
