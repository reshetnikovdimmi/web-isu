package com.myisu_1.isu.controllers;


import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.exporte.ExselFileExporteDistributionPhones;
import com.myisu_1.isu.exporte.ExselFileExporteDistributionSim;
import com.myisu_1.isu.repo.*;
import com.myisu_1.isu.service.SimDistributionServise;
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
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SimDistributionController {
    @Autowired
    private PostRepositoriy authorization_shop;
    @Autowired
    private SimAndRtkTableRepositoriy simAndRtkTableRepositoriy;
    @Autowired
    private RemanisSimRepository remanisSimrepository;
    @Autowired
    private SaleSimModemRepository_6m saleSimModemRepository6m;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository_1m;
    @Autowired
    private SimDistributionServise simDistributionServise;




    //=======SimDistributionNew.html
    @GetMapping("/SimDistributionsNew")
    public String SimDistributionNew (Model model) {

        model.addAttribute("Accessories", simDistributionServise.remainsCash());

        return "SimDistributionsNew";

    }
    @RequestMapping(value = "/table_simT2/{nameSim}/{fragment}", method = RequestMethod.GET)

    private String accessoriesCategoryMaxSale(@PathVariable("nameSim") String nameSim,@PathVariable("fragment") String fragment, Model model) {
        OrderRecommendations or = simDistributionServise.nameSimShop();

        model.addAttribute("RemanSimCash", or.getIndicatorPhoneSach().stream().filter(r -> r.getGroup().equals(nameSim)).collect(Collectors.toList()));
        model.addAttribute("RemanisPhoneGroup", or.getRemainsGroupShop().stream().filter(r ->r.getGroup()!=null && r.getGroup().equals(nameSim)).collect(Collectors.toList()));
        return "SimDistributionsNew::"+fragment;

    }

    @PostMapping("/DistributionSim")
    private ResponseEntity<OrderRecommendations> distribution(@RequestBody OrderRecommendations OR) {


        return new ResponseEntity<>(simDistributionServise.distribution(OR), HttpStatus.OK);
    }


    @RequestMapping(value = "TableDistributionSim/{shop}/{fragment}", method = RequestMethod.GET)
    private String tableDistributionButton(@PathVariable("shop")  String shop,@PathVariable("fragment") String fragment, Model model) {
        model.addAttribute("TableDistributionPhone", simDistributionServise.tableShopRemanis(shop));
        return "SimDistributionsNew::"+fragment;

    }

    @GetMapping("/exselDistributionSim")
    public void downloadExselFile(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=DistributionSim.xlsx");

        ByteArrayInputStream inputStream = ExselFileExporteDistributionPhones.exportPrisePromoFile(simDistributionServise.exselDistributionSim());

        IOUtils.copy(inputStream, response.getOutputStream());

    }

}
