package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.models.Phone.MatrixT2;
import com.myisu_1.isu.service.MatrixSparkServise;
import com.myisu_1.isu.service.MatrixT2Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class MatrixSparkController {
    @Autowired
    private MatrixSparkServise matrixSparkServise;

    @GetMapping("/matrixSpark")
    public String home(Model model) {
        return "matrixSpark";
    }

    @ResponseBody
    @RequestMapping(value = "matrixSparkSale", method = RequestMethod.GET)
    public Iterable<MatrixSpark> matrixSparkSale() {

        return matrixSparkServise.matrixSparktable();
    }

}