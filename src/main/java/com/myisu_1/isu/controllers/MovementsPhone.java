package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Phone.From_whereTo_where;
import com.myisu_1.isu.models.Phone.RequirementPhone;
import com.myisu_1.isu.models.RTK.AndroidMatrixRTK;
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
        movementsPhoneServise.LoadAuthorization_ttList();


        return "MovementsPhone";
    }


    @ResponseBody
    @RequestMapping(value = "movementsPhone", method = RequestMethod.GET)
    public Iterable<RequirementPhone> movementsPhone() {

        return movementsPhoneServise.requirementPhone();
    }

    @ResponseBody
    @RequestMapping(value = "allMovements", method = RequestMethod.GET)
    public List<From_whereTo_where> allMovements() {

        return movementsPhoneServise.allMovements();
    }
    @ResponseBody
    @RequestMapping(value = "shopMovements/{Shop}", method = RequestMethod.GET)
    public List<From_whereTo_where> shopMovements(@PathVariable("Shop") String shop) {


        return movementsPhoneServise.shopMovements(shop);
    }
}
