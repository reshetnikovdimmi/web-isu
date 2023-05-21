package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.RTK.MatrixRTK;
import com.myisu_1.isu.repo.MatrixRTKRepository;
import com.myisu_1.isu.service.MatrixRTKServise;
import com.myisu_1.isu.service.SimDistributionServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class MatrixRTKController {
    @Autowired
    private MatrixRTKServise matrixRTKServise;
    @Autowired
    private SimDistributionServise simDistributionServise;
    @GetMapping("/MatrixRTK")
    public String MatrixPhone(Model model) {
        model.addAttribute("MatrixRTK", matrixRTKServise.getSaleRemanisAll());
        model.addAttribute("distributionModel", simDistributionServise.distributionModel());
        return "MatrixRTK";
    }
    @RequestMapping(value = "/RemanisCashRTK/{grop}", method = RequestMethod.GET)

    private String remanisSaleShopRTK(@PathVariable("grop") String grop, Model model) {

        model.addAttribute("RemanRTKCash", matrixRTKServise.remanisCashRTK(grop));

        return "MatrixRTK::RemanRTKCash";

    }
    @RequestMapping(value = "/RemanisSaleRTKShop/{grop}", method = RequestMethod.GET)

    private String remanisSaleRTKShop(@PathVariable("grop") String grop, Model model) {

        model.addAttribute("MatrixRTKShop", matrixRTKServise.getSaleRemanisShop(grop));

        return "MatrixRTK::MatrixRTKShop";

    }
    @GetMapping("/TableMatrixRTK")

    private String tableMatrixRTK( Model model) {

        model.addAttribute("TableMatrixRTK", matrixRTKServise.getTableMatrixRTK());
        model.addAttribute("distributionModel", simDistributionServise.distributionModel());
        return "MatrixRTK::TableMatrixRTK";

    }
    @ResponseBody
    @RequestMapping(value = "TableDistributionRTK/{shop}", method = RequestMethod.GET)
    public Map<String,Map<String, Map<String, Integer>>> createTableDistributionRTK(@PathVariable("shop") String shop) {

        return matrixRTKServise.createTableDistributionRTK(shop);

    }
}