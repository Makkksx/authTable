package com.task4.authTable.controllers;

import com.task4.authTable.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @GetMapping("/login")
    public String getLoginPage(Model model,
                               @RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }
}
