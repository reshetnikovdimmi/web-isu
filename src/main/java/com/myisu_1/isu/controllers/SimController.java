package com.myisu_1.isu.controllers;


import com.myisu_1.isu.models.SIM.*;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
import com.myisu_1.isu.service.SimDistributionServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
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
    private SaleSimModemRepository_6m saleSimModemRepository6m;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository_1m;
    @Autowired
    private MatrixRTKRepository matrixRTKRepository;
    @Autowired
    private ShopPlanSimRepository shopPlanSimRepository;
    @Autowired
    private SimDistributionServise simDistributionServise;

    SvodSimList simList = new SvodSimList();

    List<SimSvod> simSvodList;

    @GetMapping("/SIM")
    public String sim(Model model) {

        simList.setRemanisSimList((List<RemanisSim>) remanisSimrepository.findAll());
        simList.setSaleSim_1ms((List<SaleSim_1m>) saleSimModemRepository_1m.findAll());
        simList.setSaleSim_6ms((List<SaleSim_6m>) saleSimModemRepository6m.findAll());
        simList.setAuthorization_ttList((List<authorization_tt>) authorization_shop.findAll());
        simList.setSimAndRtkTables(simAndRtkTableRepositoriy.findAll());
        simList.setMatrixRTKList(matrixRTKRepository.findAll());
        simList.setShopPlanSimsList((List<ShopPlanSim>) shopPlanSimRepository.findAll());

        simList.parse2();
        model.addAttribute("zakazSimT2m", simList.zakazSim("t2m"));
        model.addAttribute("shop", simList.getAuthorization_ttList());
        model.addAttribute("distributionModel", simDistributionServise.distributionModel());
        model.addAttribute("matrixRTK", simDistributionServise.getMatrixRTK());
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
    @RequestMapping(value = "AddSimPlan/{plan}/{shops}/{nameSim}", method = RequestMethod.GET)
    public String AddSimPlan(@PathVariable("plan") Integer plan, @PathVariable("shops") String shops, @PathVariable("nameSim") String nameSim) {
        System.out.println(nameSim);
        if (shopPlanSimRepository.existsByShopAndNameSimModem(shops, nameSim)) {
            shopPlanSimRepository.updatePlanSim(plan, shops, nameSim.replaceAll("_","/"));
        } else {
            shopPlanSimRepository.save(new ShopPlanSim(shops, nameSim.replaceAll("_","/"), plan));
        }


        return "SIM";

    }
    @PostMapping("/loadExelRTK")
    public String matrixT2Import(@RequestParam("loadExelRTK") MultipartFile loadExelRTK, Model model) throws IOException, ParseException {



        model.addAttribute("time", simDistributionServise.loadExelRTK(loadExelRTK));


        return "SIM";
    }

}
