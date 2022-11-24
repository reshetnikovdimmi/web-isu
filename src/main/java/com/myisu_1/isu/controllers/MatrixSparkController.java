package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.models.Phone.MatrixT2;
import com.myisu_1.isu.service.MatrixSparkServise;
import com.myisu_1.isu.service.MatrixT2Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseBody
    @RequestMapping(value = "updateSparkSale", method = RequestMethod.GET)
    public Iterable<MatrixSpark> updateSparkSale() {

        return matrixSparkServise.matrixSparkTableUpdate();
    }
    @PostMapping(path = "/saveSparkSale")

    private ResponseEntity saveSparkSale(@RequestBody List<MatrixSpark> sim) {


        return ResponseEntity.ok(matrixSparkServise.saveSparkSale(sim));
    }
}