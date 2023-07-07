package com.myisu_1.isu.controllers;

import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.models.*;
import com.myisu_1.isu.repo.*;
import com.myisu_1.isu.service.BonusesServise;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Controller
public class bonusesController {

    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private PromoRepositoriy promoRepositoriy;
    @Autowired
    private SalesRepositoriy salesRepositoriy;
    @Autowired
    private SuppliersRepositoriy suppliersRepositoriy;
    @Autowired
    private ComboRepositoriy comboRepositoriy;
    @Autowired
    private ValueVUERepositoriy valueVUERepositoriy;
    @Autowired
    private PostRepositoriy authorizationTt;
    @Autowired
    private BonusesServise bonusesServise;




    @GetMapping("/bonuses")
    public String bonuses(Model model) {
        model.addAttribute("optionsShop",authorizationTt.getShopList());
        model.addAttribute("optionsPhone",phoneRepositoriy.getPhoneList());
        model.addAttribute("optionsProvider",suppliersRepositoriy.getProviderList());


        return "bonuses";
    }
    @RequestMapping(value = "/dropDownListModelGB/{phone}", method = RequestMethod.GET)
    private String dropDownListModelGB(@PathVariable("phone") String phone, Model model) {
        model.addAttribute("optionsBrend",phoneRepositoriy.getModel_GBList(phone));
        return "bonuses::dropDownListModelGB";

    }

    @PostMapping("/buttonShowAll")
    private ResponseEntity<List<Bonuses>> buttonShowAll(@RequestBody Bonuses bonuses, Model model) {

        return new ResponseEntity<>(bonusesServise.bonusesShowAll(bonuses), HttpStatus.OK);


    }

    @PostMapping("/loadBonusesAll")
    private ResponseEntity<List<Bonuses>> loadBonusesAll(@RequestBody Bonuses bonuses, Model model) {

        return new ResponseEntity<>(bonusesServise.bonusesSumAll(bonuses), HttpStatus.OK);


    }
    @PostMapping("/loadBonusesNoT2")
    private ResponseEntity<List<Bonuses>> loadBonusesNoT2(@RequestBody Bonuses bonuses, Model model) {

        return new ResponseEntity<>(bonusesServise.bonusesNoT2(bonuses), HttpStatus.OK);


    }


}
