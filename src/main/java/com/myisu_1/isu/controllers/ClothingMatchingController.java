package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.service.ClothesForPhonesServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ClothingMatchingController {
    @Autowired
    private ClothesForPhonesServise loadingDBServise;
    @GetMapping("/ClothingMatching")
    public String home(Model model) {
        model.addAttribute("ClothingMatching", loadingDBServise.LoadingBrendDisting());


        return "ClothingMatching";
    }
    @PostMapping(path = "/saveSlotongMatching")

    private ResponseEntity saveSlotongMatching(@RequestBody List<ClothingMatchingTable> sim) {


        return ResponseEntity.ok(loadingDBServise.saveSparkSale(sim));
    }
}
