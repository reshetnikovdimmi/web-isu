package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.repo.PostRepositoriy;
import com.myisu_1.isu.service.MatrixSparkServise;
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
    @Autowired
    private PostRepositoriy authorization_shop;

    @GetMapping("/matrixSpark")
    public String home(Model model) {
        model.addAttribute("shop", authorization_shop.getShopMult());
        return "matrixSpark";
    }

    @ResponseBody
    @RequestMapping(value = "matrixSparkSale", method = RequestMethod.GET)
    public Iterable<MatrixSpark> matrixSparkSale() {

        return null;
    }
    @RequestMapping(value = "creatMatrix/{shop}", method = RequestMethod.GET)
    public String creatMatrix(@PathVariable("shop") String shop,Model model ) {
          model.addAttribute("tableMatrix", matrixSparkServise.matrixSparktable(shop));
        return "matrixSpark::tableMatrix";
    }





    @RequestMapping(value = "updateSparkSale", method = RequestMethod.GET)
    public String updateSparkSale(Model model) {
        model.addAttribute("tableMatrix", matrixSparkServise.matrixSparkTableUpdate());

        return "matrixSpark::tableMatrix";
    }
    @PostMapping(path = "/saveSparkSale")

    private ResponseEntity saveSparkSale(@RequestBody List<MatrixSpark> sim) {


        return ResponseEntity.ok(matrixSparkServise.saveSparkSale(sim));
    }
}