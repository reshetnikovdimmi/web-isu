package com.myisu_1.isu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class SimController {



    @GetMapping("/SIM")
    public String sim (Model model) {

        return "SIM";
    }
}
