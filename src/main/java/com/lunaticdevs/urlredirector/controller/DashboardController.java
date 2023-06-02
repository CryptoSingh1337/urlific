package com.lunaticdevs.urlredirector.controller;

import com.lunaticdevs.urlredirector.dto.LinkDTO;
import com.lunaticdevs.urlredirector.entity.User;
import com.lunaticdevs.urlredirector.service.LinkService;
import com.lunaticdevs.urlredirector.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/dashboard")
public record DashboardController(UserService userService, LinkService linkService) {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ModelAndView dashboardPage() {
        ModelAndView modelAndView = new ModelAndView("dashboard/index");
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        modelAndView.addObject("links", linkService.getAllByUsername(username));
        modelAndView.addObject("link", new LinkDTO());
        return modelAndView;
    }

    @GetMapping("/statistics")
    @PreAuthorize("hasRole('USER')")
    public String statisticPage() {
        return "dashboard/statistics";
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public String createPage(@Valid @ModelAttribute("link") LinkDTO linkDTO) {
        log.debug("Link DTO: {}", linkDTO);
        linkService.save(linkDTO);
        return "redirect:/dashboard";
    }
}
