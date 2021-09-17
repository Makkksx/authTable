package com.task4.authTable.controllers;

import com.task4.authTable.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Start page");
        model.addAttribute("googleUsers", userRepository.findUsersByClientName("Google").size());
        model.addAttribute("faceBookUsers", userRepository.findUsersByClientName("Facebook").size());
        model.addAttribute("gitHubUsers", userRepository.findUsersByClientName("GitHub").size());
        return "home";
    }
}
