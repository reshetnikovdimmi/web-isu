package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.PostRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    String logins;

    List<authorization_tt> tests;
    List<String> test;
    Iterable<authorization_tt> post;
    @Autowired

    private PostRepositoriy postRepositoriy;

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

        return "menu";
    }
}
