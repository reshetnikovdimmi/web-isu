package com.myisu_1.isu.controllers;

import com.myisu_1.isu.service.ClothesPhonesServise;
import com.myisu_1.isu.service.ClothingMatchingServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ClothesPhonesController {
    @Autowired
    private ClothingMatchingServise clothingMatchingServise;
    @Autowired
    private ClothesPhonesServise clothesPhonesServise;
    @GetMapping("/ClothesPhones")
    public String home(Model model) {
        model.addAttribute("RemainsGroupCash", clothesPhonesServise.remainsGroupCash());
        model.addAttribute("OrderRecommendations", clothesPhonesServise.orderRecommendations());
        return "ClothesPhones";
    }
}
