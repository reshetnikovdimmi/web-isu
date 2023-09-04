package com.myisu_1.isu.controllers;


import com.myisu_1.isu.repo.*;
import com.myisu_1.isu.service.SimDistributionServise;
import com.myisu_1.isu.service.SimPlanOrderServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;


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
    @Autowired
    private SimPlanOrderServise simPlanOrderServise;




    @GetMapping("/SIM")
    public String sim(Model model) {
        model.addAttribute("zakazSimT2m", simPlanOrderServise.orderSim());
        model.addAttribute("shop", authorization_shop.findAll());
        model.addAttribute("distributionModel", simDistributionServise.distributionModel());
        model.addAttribute("matrixRTK", simDistributionServise.getMatrixRTK());
        return "SIM";
    }



    @RequestMapping(value = "updateShops/{Shop}", method = RequestMethod.GET)
    public String update(@PathVariable("Shop") String shop,Model model ) {

        model.addAttribute("SimT2", simPlanOrderServise.simPlanOrder(shop));

        return "SIM::SimT2";
    }

    @RequestMapping(value = "updateShopsMts/{Shop}", method = RequestMethod.GET)
    public String updateMts(@PathVariable("Shop") String shop,Model model ) {

        model.addAttribute("SimT2", simPlanOrderServise.simPlanOrder(shop));

        return "SIM::Mts";
    }


    @ResponseBody
    @RequestMapping(value = "AddSimPlan/{id}/{plan}", method = RequestMethod.GET)
    public String AddSimPlan(@PathVariable("id") Integer id, @PathVariable("plan") Integer plan) {
        System.out.println(id);
        shopPlanSimRepository.updatePlanSim(id, plan);
          return "SIM";

    }
    @PostMapping("/loadExelRTK")
    public String matrixT2Import(@RequestParam("loadExelRTK") MultipartFile loadExelRTK, Model model) throws IOException, ParseException {



        model.addAttribute("time", simDistributionServise.loadExelRTK(loadExelRTK));


        return "SIM";
    }

}
