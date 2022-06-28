package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.price_promo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Controller
public class MarwelController {

    @GetMapping("/Marwel")
    public String Marwel(Model model) {

        return "Marwel";
    }
}
