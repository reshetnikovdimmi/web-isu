package com.myisu_1.isu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String home (Model model) {
        model.addAttribute("title", "Главная 5555");
        return "home";
    }

}
