package com.myisu_1.isu.controllers;

import com.myisu_1.isu.service.PhoneServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DistributionOfPhonesController {
    @Autowired
    private PhoneServise phoneServise;

    @GetMapping("/distributionOFphones")
    public String home(Model model) {
        phoneServise.LoadAuthorization_ttList();
        model.addAttribute("shopT2", phoneServise.shopT2());

        return "distributionOFphones";
    }
}

