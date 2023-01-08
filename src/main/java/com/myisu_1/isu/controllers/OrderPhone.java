package com.myisu_1.isu.controllers;

import com.myisu_1.isu.service.MovementsPhoneServise;
import com.myisu_1.isu.service.OrderPhoneServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderPhone {
    @Autowired
    private OrderPhoneServise orderPhoneServise;
    @GetMapping("/OrderPhone")
    public String home(Model model) {
        orderPhoneServise.LoadAuthorization_ttList();


        return "OrderPhone";
    }
}
