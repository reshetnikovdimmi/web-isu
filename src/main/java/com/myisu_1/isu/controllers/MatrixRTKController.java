package com.myisu_1.isu.controllers;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.RTK.MatrixRTK;
import com.myisu_1.isu.repo.MatrixRTKRepository;
import com.myisu_1.isu.service.MatrixRTKServise;
import com.myisu_1.isu.service.SimDistributionServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MatrixRTKController {
    @Autowired
    private MatrixRTKServise matrixRTKServise;
    @Autowired
    private SimDistributionServise simDistributionServise;
    @GetMapping("/MatrixRTK")
    public String MatrixPhone(Model model) {
        model.addAttribute("MatrixRTK", matrixRTKServise.getSaleRemanisAll());
        model.addAttribute("TableMatrixRTK", matrixRTKServise.getTableMatrixRTK());
        return "MatrixRTK";
    }
    @RequestMapping(value = "/RemanisCashRTK/{grop}", method = RequestMethod.GET)
    private String remanisSaleShopRTK(@PathVariable("grop") String grop, Model model) {
        OrderRecommendations or = matrixRTKServise.remanisPhoneSachRTK(grop);
        model.addAttribute("RemanRTKCash", or.getIndicatorPhoneSach().stream().filter(r -> r.getGroup().equals(grop)).collect(Collectors.toList()));
        model.addAttribute("RemanisPhoneGroup", or.getRemainsGroupShop().stream().filter(r -> r.getGroup().equals(grop)).collect(Collectors.toList()));
        return "MatrixRTK::RemanRTKCash";

    }

    @PostMapping("/DistributionRTK")
    private ResponseEntity<OrderRecommendations> distribution(@RequestBody OrderRecommendations OR) {


        return new ResponseEntity<>(matrixRTKServise.distribution(OR), HttpStatus.OK);
    }
    @RequestMapping(value = "TableDistributionRTK/{shop}", method = RequestMethod.GET)
    public String createTableDistributionPhone(@PathVariable("shop") String shop, Model model) {
        model.addAttribute("TableDistributionPhone", matrixRTKServise.createTableDistributionRTK(shop));
        return "MatrixRTK::TableDistributionPhone";
    }
}