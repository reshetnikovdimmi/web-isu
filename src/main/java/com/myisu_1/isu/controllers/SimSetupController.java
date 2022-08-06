package com.myisu_1.isu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimSetupController {
    @GetMapping("/SimSetup")
    public String SimSetup (Model model) {

        return "SimSetup";
    }
}
