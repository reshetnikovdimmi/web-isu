package com.myisu_1.isu.controllers;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.exporte.ExselFileExporteClotingPhone;
import com.myisu_1.isu.exporte.ExselFileExporteDistributionImei;
import com.myisu_1.isu.exporte.ExselFileExporteDistributionPhones;
import com.myisu_1.isu.service.ClothesPhonesServise;
import com.myisu_1.isu.service.ClothingMatchingServise;
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
import java.util.stream.Collectors;

@Controller
public class ClothesPhonesController {
    @Autowired
    private ClothingMatchingServise clothingMatchingServise;
    @Autowired
    private ClothesPhonesServise clothesPhonesServise;
    @GetMapping("/ClothesPhones")
    public String home(Model model) {


        model.addAttribute("RemainsGroupCash", clothesPhonesServise.remainsGroupCash());

        return "ClothesPhones";
    }
    @RequestMapping(value = "/remainsGroupShopGlass/{models}/{fragment}", method = RequestMethod.GET)

    private String remainsGroupShop(@PathVariable("models") String models,@PathVariable("fragment")String fragment, Model model) {


        OrderRecommendations or =  clothesPhonesServise.remainsGroupShop();

        model.addAttribute("RemanSimCash", or.getIndicatorPhoneSach().stream().filter(r -> r.getGroup().equals(models)).collect(Collectors.toList()));
        model.addAttribute("RemanisPhoneGroup", or.getRemainsGroupShop().stream().filter(r ->r.getGroup()!=null && r.getGroup().equals(models)).collect(Collectors.toList()));
        return "ClothesPhones::"+fragment;
    }
    @RequestMapping(value = "TableDistributionClothes/{shop}/{fragment}", method = RequestMethod.GET)
    private String tableDistributionButton(@PathVariable("shop")  String shop,@PathVariable("fragment") String fragment, Model model) {
        model.addAttribute("TableDistributionPhone", clothesPhonesServise.remainsGroupShopAll(shop));
        return "ClothesPhones::"+fragment;

    }

    @PostMapping("/DistributionClothes")
    private ResponseEntity<OrderRecommendations> distribution(@RequestBody OrderRecommendations OR) {


        return new ResponseEntity<>(clothesPhonesServise.distribution(OR), HttpStatus.OK);
    }



    @ResponseBody
    @RequestMapping(value = "/updatingAllTables/{shop}/{models}/{nomenkl}/{kol}", method = RequestMethod.GET)
    private OrderRecommendations updatingAllTables(@PathVariable("shop") String shop,@PathVariable("models") String models, @PathVariable("nomenkl") String nomenkl, @PathVariable("kol") Integer kol) {
        return clothesPhonesServise.updatingAllTables(shop,models,nomenkl.replaceAll("_","/"),kol);
    }
    @GetMapping("/ExselFileExporteClotingPhone")
    public void exselFileExporteClotingPhone(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=DistributionClotingPhone.xlsx");

        ByteArrayInputStream inputStream = ExselFileExporteDistributionPhones.exportPrisePromoFile(clothesPhonesServise.exselFileExporteClotingPhone());

        IOUtils.copy(inputStream, response.getOutputStream());


    }
}
