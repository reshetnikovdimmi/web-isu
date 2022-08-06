package com.myisu_1.isu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimDistributionController {
    @GetMapping("/SimDistribution")
    public String SimDistribution (Model model) {

        return "SimDistribution";
    }
}
