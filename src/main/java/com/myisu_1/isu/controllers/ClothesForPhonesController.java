package com.myisu_1.isu.controllers;

import com.myisu_1.isu.exporte.ExselFileExporteClotingPhone;
import com.myisu_1.isu.exporte.ExselFileExporteDistributionPhones;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import com.myisu_1.isu.models.Phone.DistributionPhone;
import com.myisu_1.isu.service.ClothesForPhonesServise;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private ClothesForPhonesServise clothesForPhonesServise;
    @GetMapping("/ClothesForPhones")
    public String home(Model model) {
        clothesForPhonesServise.Loading();

        model.addAttribute("BrendRemanisGlass", clothesForPhonesServise.remainderSaleClothing("Glass"));
        model.addAttribute("BrendRemanisCase", clothesForPhonesServise.remainderSaleClothing("Case"));
        model.addAttribute("BrendRemanisCoverBook", clothesForPhonesServise.remainderSaleClothing("CoverBook"));
        return "ClothesForPhones";
    }
    @RequestMapping(value="/tableGlasShop/{brend}", method=RequestMethod.GET)

    private String tableGlasShop(@PathVariable("brend")  String brend, Model model) {

        model.addAttribute("GlassShop",clothesForPhonesServise.tableShopRemanis(brend.replaceAll("_", "/"),"Glass"));

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
    @RequestMapping(value = "movingWarehouse/{models}/{brend}/{kol}/{View}/{shop}", method = RequestMethod.GET)
    public List<Glass> movingWarehouse(@PathVariable("models") String models,@PathVariable("brend") String brend,@PathVariable("kol") String kol, @PathVariable("View") String view,@PathVariable("shop") String shop, Model model) {

        return clothesForPhonesServise.movingWarehouse(models,brend.replaceAll("_","/"),kol,view,shop);

    }

    @PostMapping(path = "/tableCaseShop")

    private String tableCase(@RequestBody String sim, Model model) {
        model.addAttribute("CaseShop",clothesForPhonesServise.tableShopRemanis(sim.replaceAll("text=", "").replaceAll("[+]", " "),"Case"));

        return "ClothesForPhones::CaseShop";
    }

    @PostMapping(path = "/tableCoverBookShop")

    private String tableCoverBook(@RequestBody String sim, Model model) {
        model.addAttribute("CoverBookShop",clothesForPhonesServise.tableShopRemanis(sim.replaceAll("text=", "").replaceAll("[+]", " "),"CoverBook"));
        return "ClothesForPhones::CoverBookShop";
    }


    @GetMapping("/ExselFileExporteClotingPhone")
    public void exselFileExporteClotingPhone(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=DistributionClotingPhone.xlsx");

        ByteArrayInputStream inputStream = ExselFileExporteClotingPhone.exportClotingPhone(clothesForPhonesServise.exselFileExporteClotingPhone());

        IOUtils.copy(inputStream, response.getOutputStream());


    }
}
