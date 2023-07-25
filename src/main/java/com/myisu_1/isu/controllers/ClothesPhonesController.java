package com.myisu_1.isu.controllers;

import com.myisu_1.isu.service.ClothingMatchingServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ClothesPhonesController {
    @Autowired
    private ClothingMatchingServise clothingMatchingServise;
    @GetMapping("/ClothesPhones")
    public String home(Model model) {
        clothingMatchingServise.Loading();

        model.addAttribute("BrendRemanisGlass", clothingMatchingServise.remainderSaleClothing("Glass"));
        model.addAttribute("BrendRemanisCase", clothingMatchingServise.remainderSaleClothing("Case"));
        model.addAttribute("BrendRemanisCoverBook", clothingMatchingServise.remainderSaleClothing("CoverBook"));
        return "ClothesPhones";
    }
}
