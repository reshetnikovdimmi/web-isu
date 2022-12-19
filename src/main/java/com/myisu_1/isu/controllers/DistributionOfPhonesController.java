package com.myisu_1.isu.controllers;

import com.myisu_1.isu.exporte.ExselFileExporte;
import com.myisu_1.isu.models.Phone.*;
import com.myisu_1.isu.models.RTK.AndroidMatrixRTK;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.service.PhoneServise;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class DistributionOfPhonesController {
    @Autowired
    private PhoneServise phoneServise;

    @GetMapping("/distributionOFphones")
    public String home(Model model) {
        phoneServise.LoadAuthorization_ttList();


        return "distributionOFphones";
    }

    @ResponseBody
    @RequestMapping(value = "requirementPhone", method = RequestMethod.GET)
    public Iterable<RequirementPhone> requirementPhone() {

        return phoneServise.requirementPhone();
    }


    @ResponseBody
    @RequestMapping(value = "remanisWarehousePhone", method = RequestMethod.GET)
    public Iterable<RemanisPhoneWarehouse> remanisWarehousePhone() {

        return phoneServise.remanisWarehousePhone();
    }
    @ResponseBody
    @RequestMapping(value = "requirementPhone/{Shop}", method = RequestMethod.GET)
    public Iterable<DistributionPhone> distributionPhone(@PathVariable("Shop") String shop) {


        return phoneServise.distributionPhone(shop);
    }
    @ResponseBody
    @RequestMapping(value = "matrixT2Phone", method = RequestMethod.GET)
    public Iterable<TableMatrixT2> matrixT2Phone() {

        return phoneServise.matrixT2Phone();
    }
    @PostMapping(path = "/skyPhone")
    private ResponseEntity skyPhone(@RequestBody DistributionPhone skyPhone) {



        return ResponseEntity.ok(phoneServise.distributionSkyPhone(skyPhone));
    }
}

