package com.myisu_1.isu.controllers;

import com.myisu_1.isu.exporte.ExselFileExporteClotingPhone;
import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import com.myisu_1.isu.service.ClothingMatchingServise;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class ClothesForPhonesController {
    @Autowired
    private ClothingMatchingServise clothingMatchingServise;
    @GetMapping("/ClothesForPhones")
    public String home(Model model) {
        clothingMatchingServise.Loading();

        model.addAttribute("BrendRemanisGlass", clothingMatchingServise.remainderSaleClothing("Glass"));
        model.addAttribute("BrendRemanisCase", clothingMatchingServise.remainderSaleClothing("Case"));
        model.addAttribute("BrendRemanisCoverBook", clothingMatchingServise.remainderSaleClothing("CoverBook"));
        return "ClothesForPhones";
    }
    @RequestMapping(value="/tableGlasShop/{brend}", method=RequestMethod.GET)

    private String tableGlasShop(@PathVariable("brend")  String brend, Model model) {

        model.addAttribute("GlassShop", clothingMatchingServise.tableShopRemanis(brend,"Glass"));

        return "ClothesForPhones::GlassShop";
    }


    @ResponseBody
    @RequestMapping(value = "tableGlasShops/{Shop}/{brend}/{View}", method = RequestMethod.GET)
    public List<List<Glass>> update(@PathVariable("Shop") String shop, @PathVariable("brend") String brend, @PathVariable("View") String view, Model model) {
       return clothingMatchingServise.tableShopBrend(shop,brend,view);

    }
    @ResponseBody
    @RequestMapping(value = "orderЕable/{brend}/{View}", method = RequestMethod.GET)
    public List<Glass> orderЕable(@PathVariable("brend") String brend, @PathVariable("View") String view, Model model) {
        return clothingMatchingServise.tableOrderЕable(brend,view);

    }
    @ResponseBody
    @RequestMapping(value = "warehouseRemnants/{Shop}/{brend}/{View}", method = RequestMethod.GET)
    public List<Glass> warehouseRemnants(@PathVariable("Shop") String shop,@PathVariable("brend") String brend, @PathVariable("View") String view, Model model) {

        return clothingMatchingServise.warehouseRemnants(shop,brend,view);

    }
    @ResponseBody
    @RequestMapping(value = "movingWarehouse/{models}/{brend}/{kol}/{View}/{shop}", method = RequestMethod.GET)
    public List<Glass> movingWarehouse(@PathVariable("models") String models,@PathVariable("brend") String brend,@PathVariable("kol") String kol, @PathVariable("View") String view,@PathVariable("shop") String shop, Model model) {

        return clothingMatchingServise.movingWarehouse(models,brend.replaceAll("_","/"),kol,view,shop);

    }



    @RequestMapping(value="/tableCaseShop/{brend}", method=RequestMethod.GET)

    private String tableCaseShop(@PathVariable("brend")  String brend, Model model) {
        model.addAttribute("CaseShop", clothingMatchingServise.tableShopRemanis(brend,"Case"));

        return "ClothesForPhones::CaseShop";
    }



    @RequestMapping(value="/tableCoverBookShop/{brend}", method=RequestMethod.GET)

    private String tableCoverBookShop(@PathVariable("brend")  String brend, Model model) {
        model.addAttribute("CoverBookShop", clothingMatchingServise.tableShopRemanis(brend,"CoverBook"));
        return "ClothesForPhones::CoverBookShop";
    }



}
