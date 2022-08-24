package com.myisu_1.isu.controllers;

import com.myisu_1.isu.repo.PostRepositoriy;
import com.myisu_1.isu.repo.SimAndRtkTableRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimDistributionController {
    @Autowired
    private PostRepositoriy authorization_shop;
    @Autowired
    private SimAndRtkTableRepositoriy simAndRtkTableRepositoriy;

    @GetMapping("/SimDistribution")
    public String SimDistribution (Model model) {


        model.addAttribute("shop", authorization_shop.findAll());
        model.addAttribute("simAndRtkTable", simAndRtkTableRepositoriy.findAll());

        return "SimDistribution";
    }
}
