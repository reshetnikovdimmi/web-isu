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

        model.addAttribute("RemainsCash", clothesPhonesServise.remainsCash(models));

        return "ClothesPhones::remainsCashGlass";
    }
    @RequestMapping(value = "/remainsCashCase/{models}", method = RequestMethod.GET)

    private String remainsCashCase(@PathVariable("models") String models, Model model) {

        model.addAttribute("RemainsCash", clothesPhonesServise.remainsCash(models));

        return "ClothesPhones::remainsCashCase";
    }
    @RequestMapping(value = "/remainsCashCoverBook/{models}", method = RequestMethod.GET)

    private String remainsCashCoverBook(@PathVariable("models") String models, Model model) {

        model.addAttribute("RemainsCash", clothesPhonesServise.remainsCash(models));

        return "ClothesPhones::remainsCashCoverBook";
    }
    @RequestMapping(value = "/remainsGroupShopGlassAll/{models}/{shop}", method = RequestMethod.GET)

    private String remainsGroupShopGlassAll(@PathVariable("models") String models,@PathVariable("shop") String shop, Model model) {

        model.addAttribute("remainsGroupShopAll", clothesPhonesServise.remainsGroupShopAll(models,shop));

        return "ClothesPhones::remainsGroupShopGlassAll";
    }
}
