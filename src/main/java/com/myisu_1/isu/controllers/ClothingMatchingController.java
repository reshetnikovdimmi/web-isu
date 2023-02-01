package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.models.Phone.RemanisPhoneWarehouse;
import com.myisu_1.isu.repo.ClothingMatchingTableRepositoriy;
import com.myisu_1.isu.service.ClothesForPhonesServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClothingMatchingController {
    @Autowired
    private ClothesForPhonesServise loadingDBServise;
    @Autowired
    private ClothingMatchingTableRepositoriy clothingMatchingTableRepositoriy;
    @GetMapping("/ClothingMatching")
    public String home(Model model) {
        model.addAttribute("ClothingMatching", loadingDBServise.LoadingBrendDisting());


        return "ClothingMatching";
    }
    @PostMapping(path = "/saveSlotongMatching")

    private ResponseEntity saveSlotongMatching(@RequestBody List<ClothingMatchingTable> sim) {


        return ResponseEntity.ok(loadingDBServise.saveSparkSale(sim));
    }
    @ResponseBody
    @RequestMapping(value = "slotongMatchingTable", method = RequestMethod.GET)
    public List<ClothingMatchingTable> slotongMatchingTable() {

        return loadingDBServise.slotongMatchingTable();
    }
    @PostMapping(path = "/slotongMatchingTableDel")

    private ResponseEntity slotongMatchingTableDel(@RequestBody List<String> sim) {

for (int i=0;i<sim.size();i++){
    clothingMatchingTableRepositoriy.deleteById(Integer.valueOf(sim.get(i)));
}
        return ResponseEntity.ok(loadingDBServise.slotongMatchingTable());
    }
}
