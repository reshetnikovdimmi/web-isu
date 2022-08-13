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
}