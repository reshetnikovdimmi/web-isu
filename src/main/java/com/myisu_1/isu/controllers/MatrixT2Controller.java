package com.myisu_1.isu.controllers;

import com.myisu_1.isu.service.PhoneServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatrixT2Controller {
    @Autowired
    private PhoneServise phoneServise;

    @GetMapping("/matrixT2")
    public String home(Model model) {


        return "matrixT2";
    }

}
