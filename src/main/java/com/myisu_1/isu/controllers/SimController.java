package com.myisu_1.isu.controllers;


import com.myisu_1.isu.models.RTK.AndroidMatrixRTK;
import com.myisu_1.isu.models.RTK.MatrixRTK;
import com.myisu_1.isu.models.SIM.*;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class SimController {


    @Autowired
    private PostRepositoriy authorization_shop;
    @Autowired
    private RemanisSimRepository remanisSimrepository;
    @Autowired
    private SimAndRtkTableRepositoriy simAndRtkTableRepositoriy;
    @Autowired
    private SaleSimModemRepository saleSimModemRepository;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository_1m;
    @Autowired
    private MatrixRTKRepository matrixRTKRepository;

    SvodSimList simList = new SvodSimList();

    List<SimSvod> simSvodList;

    @GetMapping("/SIM")
    public String sim(Model model) {

        simList.setRemanisSimList((List<RemanisSim>) remanisSimrepository.findAll());
        simList.setSaleSim_1ms((List<SaleSim_1m>) saleSimModemRepository_1m.findAll());
        simList.setSaleSim_6ms((List<SaleSim_6m>) saleSimModemRepository.findAll());
        simList.setAuthorization_ttList((List<authorization_tt>) authorization_shop.findAll());
        simList.setSimAndRtkTables(simAndRtkTableRepositoriy.findAll());
        simList.setMatrixRTKList(matrixRTKRepository.findAll());

        simList.parse2();
        model.addAttribute("zakazSimT2m", simList.zakazSim("t2m"));
        model.addAttribute("shop", simList.getAuthorization_ttList());
        model.addAttribute("MatrixRTK", simList.MatrixRTK());
        return "SIM";
    }

    @ResponseBody
    @RequestMapping(value = "updateShops/{Shop}/{t2}", method = RequestMethod.GET)
    public Iterable<SimSvod> update(@PathVariable("Shop") String shop, @PathVariable("t2") String t2) {

        simSvodList = new ArrayList<>();
        simSvodList = (List<SimSvod>) simList.parse3(shop, t2);

        return simList.parse3(shop, t2);
    }
    @ResponseBody
    @RequestMapping(value = "updateRTK/{Shop}", method = RequestMethod.GET)
    public Iterable<AndroidMatrixRTK> updateRTK(@PathVariable("Shop") String shop) {

System.out.println(shop);


        return simList.AndroidMatrixRTKItog(shop);
    }


    @PostMapping(path = "/simos")

    private ResponseEntity simos(@RequestBody RemanisSim sim) {
        System.out.println(sim.getShop() + "--" + sim.getId() + "--" + simSvodList.get(sim.getId()).getNameSim());
        return ResponseEntity.ok(simSvodList);
    }


}
