package com.myisu_1.isu.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.models.*;
import com.myisu_1.isu.models.bonuses.BonusesPaid;
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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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
    @Autowired
    private BonusesPaidRepository bonusesPaidRepository;




    @GetMapping("/bonuses")
    public String bonuses(Model model) {
        model.addAttribute("optionsShop",authorizationTt.getShopList());
        model.addAttribute("optionsPhone",phoneRepositoriy.getPhoneList());
        model.addAttribute("optionsProvider",suppliersRepositoriy.getProviderList());
        model.addAttribute("Amount",bonusesPaidRepository.findAll());
        return "bonuses";
    }
    @RequestMapping(value = "/dropDownListModelGB/{phone}", method = RequestMethod.GET)
    private String dropDownListModelGB(@PathVariable("phone") String phone, Model model) {
        model.addAttribute("optionsBrend",phoneRepositoriy.getModel_GBList(phone));
        return "bonuses::dropDownListModelGB";
    }

    @PostMapping("/buttonShowAll")
    private ResponseEntity<List<Bonuses>> buttonShowAll(@RequestBody Bonuses bonuses) {
        return new ResponseEntity<>(bonusesServise.bonusesShowAll(bonuses), HttpStatus.OK);
    }

    @PostMapping("/loadBonusesAll")
    private ResponseEntity<List<Bonuses>> loadBonusesAll(@RequestBody Bonuses bonuses) {
        return new ResponseEntity<>(bonusesServise.bonusesSumAll(bonuses), HttpStatus.OK);
    }

    @PostMapping("/loadBonusesNoT2")
    private ResponseEntity<List<Bonuses>> loadBonusesNoT2(@RequestBody Bonuses bonuses) {
        return new ResponseEntity<>(bonusesServise.bonusesNoT2(bonuses), HttpStatus.OK);
    }

    @PostMapping(value = "/saveAmount")
    private ResponseEntity<List<BonusesPaid>> showPromo(@RequestBody BonusesPaid bonuses) {
        if (bonuses.getId() != 0) {
            bonusesPaidRepository.save(new BonusesPaid(bonuses.getId(),bonuses.model,sqlDate(bonuses.getStartPromo()),sqlDate(bonuses.getEndPromo()),bonuses.getAmount(),bonuses.suppliers));
        }else {
            bonusesPaidRepository.save(new BonusesPaid(bonuses.model,sqlDate(bonuses.getStartPromo()),sqlDate(bonuses.getEndPromo()),bonuses.getAmount(),bonuses.suppliers));
        }
          return new ResponseEntity<>(bonusesPaidRepository.findAll(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "deleteAmount/{id}", method = RequestMethod.GET)
    public List<BonusesPaid> deleteAmount(@PathVariable("id") Integer id) {
        bonusesPaidRepository.deleteById(id);
        return bonusesPaidRepository.findAll(Sort.by(Sort.Direction.DESC, "startPromo"));
    }
    @ResponseBody
    @RequestMapping(value = "updateAmount/{id}", method = RequestMethod.GET)
    public Optional<BonusesPaid> updatePromo(@PathVariable("id") Integer id) {
        return bonusesPaidRepository.findById(id);
    }
    @ResponseBody
    @RequestMapping(value = "searchAmount", method = RequestMethod.POST)
    private List<BonusesPaid> searchAmount(@RequestBody BonusesPaid bonusesPaid) {
        return bonusesPaidRepository.searchAmount(bonusesPaid.getSuppliers(),bonusesPaid.getModel(),sqlDate(bonusesPaid.getStartPromo()),sqlDate(bonusesPaid.getEndPromo()));
    }
    private java.sql.Date sqlDate(Date startPromo) {
        if (startPromo != null) {
            return new java.sql.Date(startPromo.getTime());
        }
        return null;
    }
}
