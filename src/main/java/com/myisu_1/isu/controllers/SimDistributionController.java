package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.SIM.*;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SimDistributionController {
    @Autowired
    private PostRepositoriy authorization_shop;
    @Autowired
    private SimAndRtkTableRepositoriy simAndRtkTableRepositoriy;
    @Autowired
    private RemanisSimRepository remanisSimrepository;
    @Autowired
    private SaleSimModemRepository saleSimModemRepository;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository_1m;
    SvodSimList simList = new SvodSimList();


    @GetMapping("/SimDistribution")
    public String SimDistribution (Model model) {

        simList.setRemanisSimList((List<RemanisSim>) remanisSimrepository.findAll());
        simList.setSaleSim_1ms((List<SaleSim_1m>) saleSimModemRepository_1m.findAll());
        simList.setSaleSim_6ms((List<SaleSim_6m>) saleSimModemRepository.findAll());
        simList.setAuthorization_ttList((List<authorization_tt>) authorization_shop.findAll());
        simList.setSimAndRtkTables(simAndRtkTableRepositoriy.findAll());

        simList.parse2();

        model.addAttribute("shop", simList.getAuthorization_ttList());
        model.addAttribute("simAndRtkTable", simList.getSimAndRtkTables());

        return "SimDistribution";

    }

    @ResponseBody
    @RequestMapping(value = "updateShopsSimDistribution/{Shop}/{t2}", method = RequestMethod.GET)
    public Iterable<SimSvod> update(@PathVariable("Shop") String shop,@PathVariable("t2") String t2) {

              return simList.parse(shop,t2);

    }
    @ResponseBody
    @RequestMapping(value = "updateSIM/{Shop}/{t2}", method = RequestMethod.GET)
    public Iterable<SimSvod> updateSIM(@PathVariable("Shop") String shop,@PathVariable("t2") String t2) {

        return simList.multiSim(shop,t2);

    }
}
