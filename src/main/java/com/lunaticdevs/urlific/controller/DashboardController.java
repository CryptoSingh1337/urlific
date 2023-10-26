package com.lunaticdevs.urlific.controller;

import com.lunaticdevs.urlific.dto.LinkDTO;
import com.lunaticdevs.urlific.service.LinkService;
import com.lunaticdevs.urlific.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * @author Saransh Kumar
 */

@Slf4j
@Controller
@RequestMapping("/dashboard")
public record DashboardController(UserService userService, LinkService linkService) {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ModelAndView dashboardPage() {
        ModelAndView modelAndView = new ModelAndView("dashboard/index");
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        modelAndView.addObject("links", new ArrayList<>());
        modelAndView.addObject("link", new LinkDTO());
        return modelAndView;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public String create(@Valid @ModelAttribute("link") LinkDTO linkDTO) {
        log.debug("Link DTO: {}", linkDTO);
        linkService.save(linkDTO);
        return "redirect:/dashboard";
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public String update(@Valid @ModelAttribute("link") LinkDTO linkDTO) {
        linkService.update(linkDTO);
        return "redirect:/dashboard";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public String delete(@RequestParam String id) {
        linkService.delete(id);
        return "redirect:/dashboard";
    }
}
