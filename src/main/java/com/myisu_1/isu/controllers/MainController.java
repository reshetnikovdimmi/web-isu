package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.Suppliers;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@Controller
public class MainController {
    String logins;

    List<authorization_tt> tests;
    List<Suppliers> suppliersList;
    List<Phone_Smart> phoneSmartList;
    Iterable<authorization_tt> post;
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
        post = postRepositoriy.findAll();
        model.addAttribute("test", post);
        tests = (List<authorization_tt>) postRepositoriy.findAll();
        model.addAttribute("tests", tests);
        return "home";
    }


    @PostMapping("/entrance")
    public String entrance(@RequestParam String login, @RequestParam String pasword, Model model) {
        logins = login;
        model.addAttribute("login", login);
        for (int i = 1; i < tests.size(); i++) {
            if (login.equals(tests.get(i).getLogin()) & pasword.equals(tests.get(i).getPasword())) {
                model.addAttribute("takeAlook", incassationTomorrow());
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


    public List<String> incassationTomorrow() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 0);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        List<String> result = null;
       switch (dayOfWeek) {
            case 1:result = collectionScheduleRepository.incassationMonday();
                break;

            case 2: result = collectionScheduleRepository.incassationTuesday();
                break;
            case 3: result = collectionScheduleRepository.incassationWednesday();

                break;
           case 4: result = collectionScheduleRepository.incassationThursday();

               break;

            case 5: result = collectionScheduleRepository.incassationFriday();

                break;
            default:
                break;
        }
        return result;
    }
  }
