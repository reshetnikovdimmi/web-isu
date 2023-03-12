package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Phone.ButtonsPhone;
import com.myisu_1.isu.repo.ButtonsPhoneRepositoriy;
import com.myisu_1.isu.service.ButtonsPhoneServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ButtonsPhoneController {
    @Autowired
    private ButtonsPhoneRepositoriy buttonsPhoneRepositoriy;
    @Autowired
    private ButtonsPhoneServise buttonsPhoneServise;
    @GetMapping("/ButtonsPhone")
    public String bonuses(Model model) {

        model.addAttribute("Phone", buttonsPhoneServise.findAllButtonsPhone());
        return "ButtonsPhone";
    }
    @GetMapping("/ButtonsPhoneDistribution")
    public String ButtonsPhoneDistribution(Model model) {

        model.addAttribute("Phone", buttonsPhoneServise.graduationPhone());
        return "ButtonsPhoneDistribution";
    }
    @ResponseBody
    @RequestMapping(value = "update_ButtonsPhone/{id}", method = RequestMethod.GET)
    public Optional<ButtonsPhone> update(@PathVariable("id") int id) {
        System.out.println(buttonsPhoneRepositoriy.findById(id));
        return buttonsPhoneRepositoriy.findById(id);
    }

    @PostMapping("/add_ButtonsPhone")
    public String add_ButtonsPhone(@RequestParam int ID,@RequestParam String Brend,@RequestParam String Model,Model model) {
System.out.println(ID);

        if (ID != 0) {
            buttonsPhoneRepositoriy.save((new ButtonsPhone(ID,Brend, Model)));
        } else {
            buttonsPhoneRepositoriy.save((new ButtonsPhone(Brend, Model)));
        }

        model.addAttribute("Phone", buttonsPhoneServise.findAllButtonsPhone());
        return "ButtonsPhone";
    }
    @PostMapping("/delet_ButtonsPhone")
    public String delet(@RequestParam int IDButtonPhone, Model model) {
        buttonsPhoneRepositoriy.deleteById(IDButtonPhone);

        model.addAttribute("Phone", buttonsPhoneServise.findAllButtonsPhone());
        return "ButtonsPhone";
    }

    @ResponseBody
    @RequestMapping(value = "ButtonsPhones", method = RequestMethod.GET)
    public Map<String,Map<String,String>> price() {

        return buttonsPhoneServise.graduationPhone();
    }
    @PostMapping(path = "/tableShopRemanisSele")

    private String tableShopRemanisSele(@RequestBody String sim, Model model) {
        System.out.println(sim.replaceAll("text=", "").replaceAll("[+]", " "));
        model.addAttribute("GlassShop",buttonsPhoneServise.tableShopRemanis(sim.replaceAll("text=", "").replaceAll("[+]", " ")));

        return "ButtonsPhone::graduation";
    }
}
