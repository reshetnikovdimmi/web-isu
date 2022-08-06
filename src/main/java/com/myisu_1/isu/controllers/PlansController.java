package com.myisu_1.isu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlansController {
    @GetMapping("/plans")
    public String plans (Model model) {

        return "plans";
    }
}
