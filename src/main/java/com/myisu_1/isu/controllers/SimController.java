package com.myisu_1.isu.controllers;


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

    List<SimSvod> simSvodList;
    List<SaleSim_6m> saleSim_6ms;
    List<SaleSim_1m> saleSim_1ms;

    @GetMapping("/SIM")
    public String sim(Model model) {
        model.addAttribute("shop", authorization_shop.findAll());
        return "SIM";
    }

    @ResponseBody
    @RequestMapping(value = "updateShops/{Shop}", method = RequestMethod.GET)
    public Iterable<SimSvod> update(@PathVariable("Shop") String shop) {

        simSvodList = new ArrayList<>();
        List<RemanisSim> remanisSimList = (List<RemanisSim>) remanisSimrepository.findAll();
        List<authorization_tt> authorization_ttList = (List<authorization_tt>) authorization_shop.findAll();
        List<SimAndRtkTable> simAndRtkTables = simAndRtkTableRepositoriy.findAll();
        saleSim_6ms = (List<SaleSim_6m>) saleSimModemRepository.findAll();
        saleSim_1ms = (List<SaleSim_1m>) saleSimModemRepository_1m.findAll();

        for (int i = 0; i < authorization_ttList.size(); i++) {
            if (shop.equals(authorization_ttList.get(i).getName())) {
                for (int j = 0; j < simAndRtkTables.size(); j++) {
                    if (authorization_ttList.get(i).getSimT2().equals(simAndRtkTables.get(j).getView())) {
                        for (int k = 0; k < remanisSimList.size(); k++) {
                            if (authorization_ttList.get(i).getShopIskra().equals(remanisSimList.get(k).getShop()) && simAndRtkTables.get(j).getNameSpark().trim().equals(remanisSimList.get(k).getNameSimAndModem())
                                    || authorization_ttList.get(i).getName().equals(remanisSimList.get(k).getShop()) && simAndRtkTables.get(j).getNameRainbow().trim().equals(remanisSimList.get(k).getNameSimAndModem())) {
                                String shopIskra = authorization_ttList.get(i).getShopIskra();
                                String shopRaduga = authorization_ttList.get(i).getName();
                                String simIskra = simAndRtkTables.get(j).getNameSpark().trim();
                                String simRaduga = simAndRtkTables.get(j).getNameRainbow().trim();
                                simSvodList.add(new SimSvod(k, idDlan(), remanisSimList.get(k).getNameSimAndModem(), sale_6(shopIskra, shopRaduga, simIskra, simRaduga), Sele(shopIskra, shopRaduga, simIskra, simRaduga), String.valueOf(remanisSimList.get(k).getRemainsSimModem()), 0, 0, authorization_ttList.get(i).getName()));

                            }
                        }
                    }
                }
            }
        }

        return simSvodList;
    }

    private String Sele(String shopIskra, String shopRaduga, String simIskra, String simRaduga) {
        String sale_1 = null;
        for (int i = 0; i < saleSim_1ms.size(); i++) {

            if (shopIskra.equals(saleSim_1ms.get(i).getShop()) && simIskra.equals(saleSim_1ms.get(i).getNameSimAndModem()) || shopRaduga.equals(saleSim_1ms.get(i).getShop()) && simRaduga.equals(saleSim_1ms.get(i).getNameSimAndModem())) {
                sale_1 = String.valueOf(saleSim_1ms.get(i).getRemainsSimModem());
            }
        }


        return sale_1;
    }

    private String sale_6(String shopIskra, String shopRaduga, String simIskra, String simRaduga) {
        String sale_6 = null;
        for (int i = 0; i < saleSim_6ms.size(); i++) {

            if (shopIskra.equals(saleSim_6ms.get(i).getShop()) && simIskra.equals(saleSim_6ms.get(i).getNameSimAndModem()) || shopRaduga.equals(saleSim_6ms.get(i).getShop()) && simRaduga.equals(saleSim_6ms.get(i).getNameSimAndModem())) {
                sale_6 = String.valueOf(saleSim_6ms.get(i).getRemainsSimModem()/6);
            }
        }


        return sale_6;
    }

    private int idDlan() {


        return 5;
    }

    @PostMapping(path = "/simos")

    private ResponseEntity simos(@RequestBody RemanisSim sim) {
        System.out.println(sim.getShop() + "--" + sim.getId() + "--" + simSvodList.get(sim.getId()).getNameSim());

        return ResponseEntity.ok(simSvodList);
    }


}
