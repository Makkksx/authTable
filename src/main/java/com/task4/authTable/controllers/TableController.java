package com.task4.authTable.controllers;

import com.task4.authTable.models.Status;
import com.task4.authTable.models.User;
import com.task4.authTable.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class TableController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/table")
       public String tableMain(Model model) {
//        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", userRepository);
        model.addAttribute("title", "Table here");
        return "table";
    }
    @GetMapping("/table/block/{ids}")
    public String block(@PathVariable("ids") String ids, Model model) {
        List<Long> list = Arrays.stream(ids.split(",")).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        Iterable<User> allById = userRepository.findAllById(list);
        for (User el:allById){
            el.setStatus(Status.BANNED);
        }
        userRepository.saveAll(allById);
        return "redirect:/table";
    }
    @GetMapping("/table/unblock/{ids}")
    public String unblock(@PathVariable("ids") String ids, Model model) {
        List<Long> list = Arrays.stream(ids.split(",")).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        Iterable<User> allById = userRepository.findAllById(list);
        for (User el:allById){
            el.setStatus(Status.ACTIVE);
        }
        userRepository.saveAll(allById);
        return "redirect:/table";
    }
    @GetMapping("/table/delete/{ids}")
    public String delete(@PathVariable("ids") String ids, Model model) {
        List<Long> list = Arrays.stream(ids.split(",")).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        userRepository.deleteAllById(list);
        return "redirect:/table";
    }
}
