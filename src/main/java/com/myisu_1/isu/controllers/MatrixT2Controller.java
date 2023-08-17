package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Phone.MatrixT2;
import com.myisu_1.isu.service.MatrixT2Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
public class MatrixT2Controller {
    @Autowired
    private MatrixT2Servise matrixT2Servise;

    @GetMapping("/matrixT2")
    public String home(Model model) {
        return "matrixT2";
    }

    @ResponseBody
    @RequestMapping(value = "matrixT2table", method = RequestMethod.GET)
    public Iterable<MatrixT2> updateRTK() {
        return matrixT2Servise.matrixT2table();
    }

    @ResponseBody
    @RequestMapping(value = "matrixT2Del/{distributionModel}", method = RequestMethod.GET)
    public Iterable<MatrixT2> matrixT2Del(@PathVariable("distributionModel") String distributionModel) {
        return matrixT2Servise.matrixT2Del(distributionModel);
    }

    @PostMapping(path = "/matrixT2Update")
    private ResponseEntity matrixT2tables(@RequestBody List<MatrixT2> sim) {
        return ResponseEntity.ok(matrixT2Servise.matrixT2Update(sim));
    }

    @PostMapping("/matrixT2Import")
    public String matrixT2Import(@RequestParam("matrixT2Import") MultipartFile matrixT2Import, Model model) throws IOException, ParseException {
        model.addAttribute("time", matrixT2Servise.matrixT2Import(matrixT2Import));
        return "matrixT2";
    }
}
