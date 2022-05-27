package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.PostRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    List<authorization_tt> tests;
    List<authorization_tt> test;

    @Autowired

    private PostRepositoriy postRepositoriy;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<authorization_tt> post = postRepositoriy.findAll();
        test = new ArrayList<>();
        model.addAttribute("test", post);
        tests = (List<authorization_tt>) postRepositoriy.findAll();
        model.addAttribute("tests", tests);
        return "home";
    }

    @PostMapping("/entrance")
    public String entrance(@RequestParam String login, @RequestParam String pasword, Model model) {
        model.addAttribute("login", login);
        for (int i = 1; i < tests.size(); i++) {
            if (login.equals(tests.get(i).getLogin()) & pasword.equals(tests.get(i).getPasword())) {
                return "menu";
            }
        }
        return "";
    }
}
