package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.Suppliers;
import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.repo.*;
import com.myisu_1.isu.service.MainServise;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@Controller
public class MainController {
    @Autowired
    private MainServise mainServise;
    String logins;

    List<Authorization_tt> tests;
    List<Suppliers> suppliersList;
    List<Phone_Smart> phoneSmartList;
    Iterable<Authorization_tt> post;
    @Autowired
    private PostRepositoriy postRepositoriy;
    @Autowired
    private SuppliersRepositoriy suppliersRepositoriy;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private CollectionScheduleRepository collectionScheduleRepository;
    @Autowired
    private RemanisSimRepository remanisSimrepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("test", postRepositoriy.findAll());
        model.addAttribute("tests", postRepositoriy.findAll());
        return "home";
    }


    @PostMapping("/entrance")
    public String entrance(@RequestParam String login, @RequestParam String pasword, Model model) {
        logins = login;
        tests = (List<Authorization_tt>) postRepositoriy.findAll();
        model.addAttribute("login", login);
        for (int i = 1; i < tests.size(); i++) {
            if (login.equals(tests.get(i).getLogin()) & pasword.equals(tests.get(i).getPasword())) {
                model.addAttribute("takeAlook", mainServise.incassationTomorrow());
                return "menu";
            }
        }
        model.addAttribute("test", post);
        model.addAttribute("tests", tests);
        return "home";
    }


    @RequestMapping(value = "/AllShoppingNeeds/{shop}", method = RequestMethod.GET)
    private String allShoppingNeeds(@PathVariable("shop") String shop, Model model) {
        model.addAttribute("AllShoppingNeeds", remanisSimrepository.findByShop(shop));
        return "menu::AllShoppingNeeds";

    }

    @GetMapping("/entrance")
    public String entranc(Model model) {
        model.addAttribute("login", logins);
        model.addAttribute("takeAlook", mainServise.incassationTomorrow());
        return "menu";
    }

}
