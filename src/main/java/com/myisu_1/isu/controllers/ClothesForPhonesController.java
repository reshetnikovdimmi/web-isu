package com.myisu_1.isu.controllers;

import com.myisu_1.isu.service.ClothesForPhonesServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ClothesForPhonesController {
    @Autowired
    private ClothesForPhonesServise loadingDBServise;
    @GetMapping("/ClothesForPhones")
    public String home(Model model) {
        loadingDBServise.Loading();
        model.addAttribute("ClothingMatching", loadingDBServise.LoadingBrendDisting());

        return "ClothesForPhones";
    }
}
