package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.authorization_tt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    @GetMapping("/promo")
    public String promo (Model model) {

        return "promo";
    }
}