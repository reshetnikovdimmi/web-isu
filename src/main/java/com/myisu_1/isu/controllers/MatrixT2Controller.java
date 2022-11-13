package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Phone.MatrixT2;
import com.myisu_1.isu.models.RTK.AndroidMatrixRTK;
import com.myisu_1.isu.models.RTK.MatrixRTK;
import com.myisu_1.isu.service.MatrixT2Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}
