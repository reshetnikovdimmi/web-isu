package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.accessories.SettingAccessories;
import com.myisu_1.isu.repo.SettingAccessoriesRepositoriy;
import com.myisu_1.isu.service.AccessoriesServise;
import com.myisu_1.isu.service.PhoneServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

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
    @ResponseBody
    @RequestMapping(value = "AccessoriesCategoryNomenclatureShop/{shop}", method = RequestMethod.GET)
    public Map<String, Map<String, Map<String, String>>> AccessoriesCategoryNomenclatureShop(@PathVariable("shop") String shop) {

        return accessoriesServise.accessoriesCategoryNomenclatureShop(shop);

    }
}
