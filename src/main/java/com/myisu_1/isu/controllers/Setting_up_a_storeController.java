package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.SIM.SimAndRtkTable;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.PostRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class Setting_up_a_storeController {
    @Autowired
    private PostRepositoriy shop;

    @GetMapping("/Setting_up_a_store")
    public String Setting_up_a_store(Model model) {
        model.addAttribute("shop", shop.findAll());
        return "Setting_up_a_store";
    }

    @ResponseBody
    @RequestMapping(value = "update_shop/{id}", method = RequestMethod.GET)
    public Optional<authorization_tt> update(@PathVariable("id") int id) {
        return shop.findById(id);
    }

    @PostMapping("/add_shop")
    public String add_phone(@RequestParam int IDupdateSHOP,
                            @RequestParam String LOGIN,
                            @RequestParam String PASWORD,
                            @RequestParam String NAMERAINBOW,
                            @RequestParam String CLUSTERT2,
                            @RequestParam String CLUSTERRTK,
                            @RequestParam String SIMT2,
                            @RequestParam String CLUSTERMTS,
                            @RequestParam String SIMBEE,
                            @RequestParam String SIMMF,
                            @RequestParam String NAMESPARK,
                            @RequestParam String NAMERARUS,
                            Model model) {

        if (IDupdateSHOP != 0) {
            shop.save((new authorization_tt(IDupdateSHOP, LOGIN, PASWORD, NAMERAINBOW, CLUSTERT2, CLUSTERRTK, SIMT2, CLUSTERMTS, SIMBEE, SIMMF, NAMESPARK, NAMERARUS)));
        } else {
            shop.save((new authorization_tt(LOGIN, PASWORD, NAMERAINBOW, CLUSTERT2, CLUSTERRTK, SIMT2, CLUSTERMTS, SIMBEE, SIMMF, NAMESPARK, NAMERARUS)));
        }

        model.addAttribute("shop", shop.findAll());
        return "Setting_up_a_store";
    }
    @PostMapping("/delet_shop")
    public String delet(@RequestParam int IDshop, Model model) {
        shop.deleteById(IDshop);
        model.addAttribute("shop", shop.findAll());
        return "Setting_up_a_store";
    }
}