package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.Suppliers;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.PostRepositoriy;
import com.myisu_1.isu.repo.SuppliersRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/")
    public String home(Model model) {
        post = postRepositoriy.findAll();
        model.addAttribute("test", post);
        tests = (List<authorization_tt>) postRepositoriy.findAll();
        model.addAttribute("tests", tests);
        return "home";
    }
    @GetMapping("/api")
    public ResponseEntity simos() {

        return ResponseEntity.ok(postRepositoriy.findAll());
    }

    @PostMapping("/entrance")
    public String entrance(@RequestParam String login, @RequestParam String pasword, Model model) {
        logins = login;
        model.addAttribute("login", login);
        for (int i = 1; i < tests.size(); i++) {
            if (login.equals(tests.get(i).getLogin()) & pasword.equals(tests.get(i).getPasword())) {
                return "menu";
            }
        }
        model.addAttribute("test", post);
        model.addAttribute("tests", tests);
        return "home";
    }
    @GetMapping("/entrance")
    public String entranc(Model model) {

        model.addAttribute("login", logins);
        model.addAttribute("noPhone", noPhone());
        return "menu";
    }

    private Object noPhone() {
        suppliersList = suppliersRepositoriy.findAll();
        phoneSmartList = phoneRepositoriy.findAll();
        List<String> suppliersList1 = new ArrayList<>();
        for(int i = 0;i<suppliersList.size();i++){
            suppliersList1.add(suppliersList.get(i).getImei());
        }
        System.out.println(suppliersList.contains("Infinix Hot 10 Lite 2/32Gb Quetzal cyan"));

        return  null;
    }
}
