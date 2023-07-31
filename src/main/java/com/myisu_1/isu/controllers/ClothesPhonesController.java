package com.myisu_1.isu.controllers;

import com.myisu_1.isu.service.ClothesPhonesServise;
import com.myisu_1.isu.service.ClothingMatchingServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;

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
    @RequestMapping(value = "/remainsGroupShop/{models}", method = RequestMethod.GET)

    private String remainsGroupShop(@PathVariable("models") String models, Model model) {

        model.addAttribute("RemainsGroupShop", clothesPhonesServise.remainsGroupShop(models));

        return "ClothesPhones::remainsGroupShopGlass";
    }
    @RequestMapping(value = "/remainsGroupShopCase/{models}", method = RequestMethod.GET)

    private String remainsGroupShopCase(@PathVariable("models") String models, Model model) {

        model.addAttribute("RemainsGroupShop", clothesPhonesServise.remainsGroupShop(models));

        return "ClothesPhones::remainsGroupShopCase";
    }
    @RequestMapping(value = "/remainsGroupShopCoverBook/{models}", method = RequestMethod.GET)

    private String remainsGroupShopCoverBook(@PathVariable("models") String models, Model model) {

        model.addAttribute("RemainsGroupShop", clothesPhonesServise.remainsGroupShop(models));

        return "ClothesPhones::remainsGroupShopCoverBook";
    }
    @RequestMapping(value = "/remainsCashGlass/{models}", method = RequestMethod.GET)

    private String remainsCashGlass(@PathVariable("models") String models, Model model) {

        model.addAttribute("RemainsCashGlass", clothesPhonesServise.remainsCashGlass(models));

        return "ClothesPhones::remainsCashGlass";
    }
}
