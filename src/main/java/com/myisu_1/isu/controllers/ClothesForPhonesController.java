package com.myisu_1.isu.controllers;

import com.myisu_1.isu.service.ClothesForPhonesServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ClothesForPhonesController {
    @Autowired
    private ClothesForPhonesServise clothesForPhonesServise;
    @GetMapping("/ClothesForPhones")
    public String home(Model model) {
        clothesForPhonesServise.Loading();

        model.addAttribute("BrendRemanisGlass", clothesForPhonesServise.remainderSaleClothing("Glass"));
        model.addAttribute("BrendRemanisCase", clothesForPhonesServise.remainderSaleClothing("Case"));
        model.addAttribute("BrendRemanisCoverBook", clothesForPhonesServise.remainderSaleClothing("CoverBook"));
        return "ClothesForPhones";
    }
    @PostMapping(path = "/tableGlasShop")

    private String tableGlass(@RequestBody String sim, Model model) {
        model.addAttribute("GlassShop",clothesForPhonesServise.tableShopRemanis(sim.replaceAll("text=", "").replaceAll("[+]", " "),"Glass"));

        return "ClothesForPhones::GlassShop";
    }

    @PostMapping(path = "/tableCaseShop")

    private String tableCase(@RequestBody String sim, Model model) {
        model.addAttribute("CaseShop",clothesForPhonesServise.tableShopRemanis(sim.replaceAll("text=", "").replaceAll("[+]", " "),"Case"));

        return "ClothesForPhones::CaseShop";
    }

    @PostMapping(path = "/tableCoverBookShop")

    private String tableCoverBook(@RequestBody String sim, Model model) {
        model.addAttribute("CoverBookShop",clothesForPhonesServise.tableShopRemanis(sim.replaceAll("text=", "").replaceAll("[+]", " "),"CoverBook"));

        return "ClothesForPhones::CoverBook";
    }
}
