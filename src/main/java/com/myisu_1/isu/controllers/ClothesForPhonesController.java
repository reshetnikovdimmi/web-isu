package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import com.myisu_1.isu.service.ClothesForPhonesServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ResponseBody
    @RequestMapping(value = "tableGlasShops/{Shop}/{brend}/{View}", method = RequestMethod.GET)
    public List<List<Glass>> update(@PathVariable("Shop") String shop, @PathVariable("brend") String brend, @PathVariable("View") String view, Model model) {
       return clothesForPhonesServise.tableShopBrend(shop,brend,view);

    }
    @ResponseBody
    @RequestMapping(value = "orderЕable/{brend}/{View}", method = RequestMethod.GET)
    public List<Glass> orderЕable(@PathVariable("brend") String brend, @PathVariable("View") String view, Model model) {
        return clothesForPhonesServise.tableOrderЕable(brend,view);

    }
    @ResponseBody
    @RequestMapping(value = "warehouseRemnants/{Shop}/{brend}/{View}", method = RequestMethod.GET)
    public List<Glass> warehouseRemnants(@PathVariable("Shop") String shop,@PathVariable("brend") String brend, @PathVariable("View") String view, Model model) {

        return clothesForPhonesServise.warehouseRemnants(shop,brend,view);

    }
    @ResponseBody
    @RequestMapping(value = "movingWarehouse/{Shop}/{brend}/{View}", method = RequestMethod.GET)
    public List<Glass> movingWarehouse(@PathVariable("Shop") String shop,@PathVariable("brend") String brend, @PathVariable("View") String view, Model model) {

        return clothesForPhonesServise.movingWarehouse(shop,brend,view);

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
