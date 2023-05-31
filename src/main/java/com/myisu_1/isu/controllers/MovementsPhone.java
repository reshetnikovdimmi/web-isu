package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Phone.From_whereTo_where;
import com.myisu_1.isu.models.Phone.RequirementPhone;
import com.myisu_1.isu.models.Phone.TableMatrixT2;
import com.myisu_1.isu.service.MovementsPhoneServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
