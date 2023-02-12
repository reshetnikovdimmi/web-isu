package com.myisu_1.isu.controllers;

import com.myisu_1.isu.dto.BrendRemanis;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.repo.ClothingMatchingTableRepositoriy;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.service.ClothesForPhonesServise;
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
public class ClothingMatchingController {
    @Autowired
    private ClothesForPhonesServise loadingDBServise;
    @Autowired
    private ClothingMatchingTableRepositoriy clothingMatchingTableRepositoriy;
    @Autowired
    private PhoneRepositoriy phone_smart;
    @Autowired
    private ClothesForPhonesServise clothesForPhonesServise;

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
    @ResponseBody
    @RequestMapping(value = "slotongMatchingTabless", method = RequestMethod.GET)
    public List<Phone_Smart> lotongMatchingTable() {

        return phone_smart.findAll();
    }
    @ResponseBody
    @RequestMapping(value = "slotongMatchingTablesss", method = RequestMethod.GET)
    public List<BrendRemanis> lotongMatchingTables() {

        return clothingMatchingTableRepositoriy.getSal6Clothes("Case");
    }

    @PostMapping(path = "/slotongMatchingTableDel")

    private ResponseEntity slotongMatchingTableDel(@RequestBody List<String> sim) {

        for (int i = 0; i < sim.size(); i++) {
            clothingMatchingTableRepositoriy.deleteById(Integer.valueOf(sim.get(i)));
        }
        return ResponseEntity.ok(loadingDBServise.slotongMatchingTable());
    }

    @PostMapping("/uploadClothingMatching")
    public String mapReapExcelDatatoDB(@RequestParam("ClothingMatching") MultipartFile ClothingMatching, Model model) throws IOException, ParseException {
        model.addAttribute("ClothingMatching", loadingDBServise.LoadingBrendDisting());
        loadingDBServise.LoadingClothingMatching(ClothingMatching);
        return "ClothingMatching";
    }
    @PostMapping("/uploadClothingMatchingSale1")
    public String mapReapExcelDatatoDB1(@RequestParam("ClothingMatchingSale1") MultipartFile ClothingMatchingSale1, Model model) throws IOException, ParseException {
        model.addAttribute("ClothingMatching", loadingDBServise.LoadingBrendDisting());
        System.out.println(ClothingMatchingSale1.getName());
        loadingDBServise.LoadingClothingMatchingSale1(ClothingMatchingSale1);
        return "ClothingMatching";
    }
    @PostMapping("/uploadClothingMatchingSale6")
    public String mapReapExcelDatatoDB6(@RequestParam("ClothingMatchingSale6") MultipartFile ClothingMatchingSale6, Model model) throws IOException, ParseException {
        model.addAttribute("ClothingMatching", loadingDBServise.LoadingBrendDisting());
        loadingDBServise.LoadingClothingMatchingSale6(ClothingMatchingSale6);
        return "ClothingMatching";
    }
}
