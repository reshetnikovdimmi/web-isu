package com.myisu_1.isu.controllers;

import com.myisu_1.isu.repo.SettingAccessoriesRepositoriy;
import com.myisu_1.isu.service.AccessoriesServise;
import com.myisu_1.isu.service.PhoneServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccessoriesController {

    @Autowired
    private AccessoriesServise accessoriesServise;
    @GetMapping("/Accessories")
    public String home(Model model) {
        model.addAttribute("Accessories", accessoriesServise.AccessoriesCategory());


        return "Accessories";
    }

    @RequestMapping(value = "/AccessoriesCategoryShop/{Grup}", method = RequestMethod.GET)

    private String DelAccessoriesCategory(@PathVariable("Grup") String Grup, Model model) {

        model.addAttribute("AccessoriesCategoryShop", accessoriesServise.accessoriesCategoryShop(Grup));
        return "Accessories::AccessoriesCategoryShop";
    }
}
