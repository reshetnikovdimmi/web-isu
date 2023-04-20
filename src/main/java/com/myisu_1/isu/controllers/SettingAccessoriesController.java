package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.accessories.RangeAccessories;
import com.myisu_1.isu.models.accessories.SettingAccessories;
import com.myisu_1.isu.repo.RangeAccessoriesRepositoriy;
import com.myisu_1.isu.repo.SettingAccessoriesRepositoriy;
import com.myisu_1.isu.service.SettingAccessoriesServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;


@Controller
public class SettingAccessoriesController {
    @Autowired
    private SettingAccessoriesServise settingAccessoriesServise;
    @Autowired
    private SettingAccessoriesRepositoriy settingAccessoriesRepositoriy;
    @Autowired
    private RangeAccessoriesRepositoriy rangeAccessoriesRepositoriy;

    @GetMapping("/SettingAccessories")
    public String home(Model model) {

        model.addAttribute("Accessories", settingAccessoriesRepositoriy.findAll());


        return "SettingAccessories";
    }

    @GetMapping("/AddAccessories")
    public String AddAccessories(Model model) {

        model.addAttribute("AddAccessories", settingAccessoriesRepositoriy.findAll());
        model.addAttribute("AccessoriesCategory", rangeAccessoriesRepositoriy.findAll());

        return "AddAccessories";
    }

    @PostMapping("/add_group_accessories")
    public String add_group_accessories(@RequestParam int ID, @RequestParam String group, @RequestParam int PriceMin, @RequestParam int PriceMax, Model model) {

        if (ID != 0) {


           rangeAccessoriesRepositoriy.updateRangeAccessories(group, settingAccessoriesRepositoriy.findById(ID).get().getGroup());

            settingAccessoriesRepositoriy.save((new SettingAccessories(ID, group, PriceMin, PriceMax)));
        } else {

            settingAccessoriesRepositoriy.save((new SettingAccessories(group, PriceMin, PriceMax)));
        }

        model.addAttribute("Accessories", settingAccessoriesRepositoriy.findAll());
        return "SettingAccessories";
    }

    @PostMapping("/delet_Accessories")
    public String delet(@RequestParam int IDAccessories, Model model) {
        settingAccessoriesRepositoriy.deleteById(IDAccessories);

        model.addAttribute("Accessories", settingAccessoriesRepositoriy.findAll());
        return "SettingAccessories";
    }

    @ResponseBody
    @RequestMapping(value = "update_Accessories/{id}", method = RequestMethod.GET)
    public Optional<SettingAccessories> update(@PathVariable("id") int id) {

        return settingAccessoriesRepositoriy.findById(id);
    }


    @RequestMapping(value = "/AddAccessoriesCategory/{id}/{accessoriesName}/{accessoriesCategory}/{tele2}", method = RequestMethod.GET)

    private String addAccessoriesCategory(@PathVariable("id") int id, @PathVariable("accessoriesName") String accessoriesName, @PathVariable("accessoriesCategory") String accessoriesCategory, @PathVariable("tele2") boolean tele2, Model model) {
        if (id == 0) {
            rangeAccessoriesRepositoriy.save(new RangeAccessories(accessoriesName, accessoriesCategory, tele2));
        } else {

            rangeAccessoriesRepositoriy.save(new RangeAccessories(id,accessoriesName, accessoriesCategory, tele2));
        }


        model.addAttribute("AccessoriesCategory", rangeAccessoriesRepositoriy.findAll());
        return "AddAccessories::AccessoriesCategory";
    }

    @RequestMapping(value = "/DelAccessoriesCategory/{id}", method = RequestMethod.GET)

    private String DelAccessoriesCategory(@PathVariable("id") int id, Model model) {

        rangeAccessoriesRepositoriy.deleteById(id);

        model.addAttribute("AccessoriesCategory", rangeAccessoriesRepositoriy.findAll());
        return "AddAccessories::AccessoriesCategory";
    }
    @PostMapping("/uploadAccessories")
    public String mapReapExcelDatatoDB(@RequestParam("Accessories") MultipartFile accessories, Model model) throws IOException, ParseException {

        settingAccessoriesServise.loadAccessories(accessories);
        model.addAttribute("Accessories", settingAccessoriesRepositoriy.findAll());
        return "SettingAccessories";
    }
    @PostMapping("/uploadAccessoriesSale1")
    public String uploadAccessoriesSale1(@RequestParam("AccessoriesSale1") MultipartFile accessoriesSale1, Model model) throws IOException, ParseException {

        settingAccessoriesServise.loadAccessoriesSale1(accessoriesSale1);

        model.addAttribute("Accessories", settingAccessoriesRepositoriy.findAll());
        return "SettingAccessories";
    }
    @PostMapping("/uploadAccessoriesSale6")
    public String uploadAccessoriesSale6(@RequestParam("AccessoriesSale6") MultipartFile accessoriesSale6, Model model) throws IOException, ParseException {

        settingAccessoriesServise.loadAccessoriesSale6(accessoriesSale6);

        model.addAttribute("Accessories", settingAccessoriesRepositoriy.findAll());
        return "SettingAccessories";
    }

}
