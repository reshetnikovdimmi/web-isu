package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.SIM.SimAndRtkTable;
import com.myisu_1.isu.models.price_promo;
import com.myisu_1.isu.repo.SimAndRtkTableRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class SimSetupController {
List<SimAndRtkTable> simAndRtkTables;
    @Autowired
    private SimAndRtkTableRepositoriy simAndRtkTableRepositoriy;

    @GetMapping("/SimSetup")
    public String SimSetup(Model model) {
        simAndRtkTables = simAndRtkTableRepositoriy.findAll();
        model.addAttribute("SIM_TELE2", simAndRtkTables);
        return "SimSetup";
    }

    @PostMapping("/add_SIM")
    public String add_phone(@RequestParam int IDupdateSIM,
                            @RequestParam String view,
                            @RequestParam String nameSpark,
                            @RequestParam String nameRarus,
                            @RequestParam String distributionModel,
                            @RequestParam String toOrder,
                            Model model) {
        System.out.println(IDupdateSIM);

        if (IDupdateSIM != 0) {
            simAndRtkTableRepositoriy.save((new SimAndRtkTable(IDupdateSIM,view,nameSpark,nameRarus,distributionModel,toOrder)));
        }else {
            simAndRtkTableRepositoriy.save((new SimAndRtkTable(view,nameSpark,nameRarus,distributionModel,toOrder)));
        }

        model.addAttribute("SIM_TELE2", simAndRtkTableRepositoriy.findAll());
        return "SimSetup";
    }

    @ResponseBody
    @RequestMapping(value = "update_SIM/{id}", method = RequestMethod.GET)
    public Optional<SimAndRtkTable> update(@PathVariable("id") int id) {

        return simAndRtkTableRepositoriy.findById(id);
    }
    @PostMapping("/delet_SIM")
    public String delet(@RequestParam int ID,Model model) {
        simAndRtkTableRepositoriy.deleteById(ID);
        model.addAttribute("SIM_TELE2", simAndRtkTableRepositoriy.findAll());
        return "SimSetup";
    }
}
