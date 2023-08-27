package com.myisu_1.isu.controllers;


import com.myisu_1.isu.service.MovementsPhoneServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class MovementsPhone {
    @Autowired
    private MovementsPhoneServise movementsPhoneServise;
    @GetMapping("/MovementsPhone")
    public String home(Model model) {
        movementsPhoneServise.distributionModel();


        return "MovementsPhone";
    }





}
