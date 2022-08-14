package com.myisu_1.isu.controllers;


import com.myisu_1.isu.models.Distinct;
import com.myisu_1.isu.models.SIM.RemanisSimRarus;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.PostRepositoriy;
import com.myisu_1.isu.repo.RemanisSimRarusrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@Controller
public class SimController {

    List<authorization_tt> shopall;
    List<Distinct> one;
    @Autowired
    private PostRepositoriy shop;
    @Autowired
    private RemanisSimRarusrepository remanisSimRarusrepository;


    @GetMapping("/SIM")
    public String sim(Model model) {
        model.addAttribute("shop", shop.findAll());
        return "SIM";
    }

    @ResponseBody
    @RequestMapping(value = "updateShops/{Shop}", method = RequestMethod.GET)
    public Iterable<RemanisSimRarus> update(@PathVariable("Shop") String shop) {
        return remanisSimRarusrepository.findAll();
    }


}
