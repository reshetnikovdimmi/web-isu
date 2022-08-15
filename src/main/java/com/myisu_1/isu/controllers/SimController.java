package com.myisu_1.isu.controllers;


import com.myisu_1.isu.models.Distinct;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.RemanisSimRarus;
import com.myisu_1.isu.models.SIM.SimAndRtkTable;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.PostRepositoriy;
import com.myisu_1.isu.repo.RemanisSimRarusrepository;
import com.myisu_1.isu.repo.RemanisSimRepository;
import com.myisu_1.isu.repo.SimAndRtkTableRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/SIM")
    public String sim(Model model) {
        model.addAttribute("shop", authorization_shop.findAll());
        return "SIM";
    }

    @ResponseBody
    @RequestMapping(value = "updateShops/{Shop}", method = RequestMethod.GET)
    public Iterable<RemanisSim> update(@PathVariable("Shop") String shop) {

        List<RemanisSim> remanisSims = new ArrayList<>();
        List<RemanisSim> remanisSimList = (List<RemanisSim>)remanisSimrepository.findAll();
        List<authorization_tt> authorization_ttList = (List<authorization_tt>) authorization_shop.findAll();
        List<SimAndRtkTable> simAndRtkTables = simAndRtkTableRepositoriy.findAll();

        for(int i =0;i<authorization_ttList.size();i++){
            System.out.println(authorization_ttList.get(i).getName());
            if(shop.equals(authorization_ttList.get(i).getName())){
                System.out.println(shop);
                for (int j =0;j < remanisSimList.size();j++){
                    if (authorization_ttList.get(i).getShopIskra().equals(remanisSimList.get(j).getShop())){

                        remanisSims.add(new RemanisSim(remanisSimList.get(j).getId(),
                                remanisSimList.get(j).getShop(),
                                remanisSimList.get(j).getNameSimAndModem(),
                                remanisSimList.get(j).getRemainsSimModem()));
                    }
                }
            }
        }

        return remanisSims;
    }


}
