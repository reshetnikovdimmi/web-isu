package com.myisu_1.isu.controllers;


import com.myisu_1.isu.models.SIM.*;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
import com.myisu_1.isu.service.SimDistributionServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    SvodSimList simList = new SvodSimList();


    @GetMapping("/SimDistribution")
    public String SimDistribution (Model model) {

            simList.setRemanisSimList((List<RemanisSim>) remanisSimrepository.findAll());
            simList.setSaleSim_1ms((List<SaleSim_1m>) saleSimModemRepository_1m.findAll());
            simList.setSaleSim_6ms((List<SaleSim_6m>) saleSimModemRepository6m.findAll());
            simList.setAuthorization_ttList((List<authorization_tt>) authorization_shop.findAll());
            simList.setSimAndRtkTables(simAndRtkTableRepositoriy.findAll());

        simList.parse2();

        model.addAttribute("shop", simList.getAuthorization_ttList());
        model.addAttribute("simAndRtkTable", simList.getSimAndRtkTables());

        return "SimDistribution";

    }

    @ResponseBody
    @RequestMapping(value = "updateShopsSimDistribution/{Shop}/{t2}", method = RequestMethod.GET)
    public Iterable<SimSvod> update(@PathVariable("Shop") String shop, @PathVariable("t2") String t2) {

              return simList.parse(shop,t2);

    }
    @ResponseBody
    @RequestMapping(value = "updateShopsSimDistributionMono/{Shop}/{t2}", method = RequestMethod.GET)
    public Iterable<SimSvod> update1(@PathVariable("Shop") String shop,@PathVariable("t2") String t2) {

        return simList.parse(shop,t2);

    }
    @ResponseBody
    @RequestMapping(value = "updateSIM/{Shop}/{t2}", method = RequestMethod.GET)
    public Iterable<SimSvod> updateSIM(@PathVariable("Shop") String shop,@PathVariable("t2") String t2) {

        return simList.multiSim(shop,t2);

    }
    @ResponseBody
    @RequestMapping(value = "updateSIMmono/{Shop}/{t2}", method = RequestMethod.GET)
    public Iterable<SimSvod> updateSIMmono(@PathVariable("Shop") String shop,@PathVariable("t2") String t2) {

        return simList.multiSim(shop.replaceAll("_","/"),t2);

    }
    //=======SimDistributionNew.html
    @GetMapping("/SimDistributionsNew")
    public String SimDistributionNew (Model model) {

        model.addAttribute("Accessories", simDistributionServise.needSim());

        return "SimDistributionsNew";

    }
    @RequestMapping(value = "/NameSimShop/{nameSim}/{view}", method = RequestMethod.GET)

    private String accessoriesCategoryMaxSale(@PathVariable("nameSim") String nameSim,@PathVariable("view") String view, Model model) {

        model.addAttribute("NameSimShop", simDistributionServise.nameSimShop(nameSim.replaceAll("_","/"),view));
        return "SimDistributionsNew::NameSimShop";

    }
    @RequestMapping(value = "/RemanSimCash/{nameSim}", method = RequestMethod.GET)

    private String remanSimCash(@PathVariable("nameSim") String nameSim, Model model) {

        model.addAttribute("RemanSimCash", simDistributionServise.remanSimCash(nameSim.replaceAll("_","/")));
        return "SimDistributionsNew::RemanSimCash";

    }
    @ResponseBody
    @RequestMapping(value = "RemanSaleSimShop/{shop}", method = RequestMethod.GET)
    public Map<String,Map<String, Map<String, String>>> remanSaleSimShop(@PathVariable("shop") String shop) {

        return simDistributionServise.remanSaleSimShop(shop);

    }
}
