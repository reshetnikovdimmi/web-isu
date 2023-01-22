package com.myisu_1.isu.controllers;

import com.myisu_1.isu.service.OrderPhoneServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderPhone {
    @Autowired
    private OrderPhoneServise orderPhoneServise;
    @GetMapping("/OrderPhone")
    public String home(Model model) {
        orderPhoneServise.LoadAuthorization_ttList();


        return "OrderPhone";
    }

    @ResponseBody
    @RequestMapping(value = "orderFromT2Warehouse", method = RequestMethod.GET)
    public List<com.myisu_1.isu.models.Phone.OrderPhone> orderFromT2Warehouse() {
        orderPhoneServise.requirementPhone();
        return orderPhoneServise.orderFromT2Warehouse();
    }
    @ResponseBody
    @RequestMapping(value = "orderFromWarehouse", method = RequestMethod.GET)
    public List<com.myisu_1.isu.models.Phone.OrderPhone> orderFromWarehouse() {

        return orderPhoneServise.orderFromWarehouse();
    }
    @ResponseBody
    @RequestMapping(value = "orderFromAllWarehouse", method = RequestMethod.GET)
    public List<com.myisu_1.isu.models.Phone.OrderPhone> orderFromAllWarehouse() {

        return orderPhoneServise.orderFromAllWarehouse();
    }
    @ResponseBody
    @RequestMapping(value = "orderFromMatrixWarehouse", method = RequestMethod.GET)
    public List<com.myisu_1.isu.models.Phone.OrderPhone> orderFromMatrixWarehouse() {

        return orderPhoneServise.orderFromMatrixT2Warehouse();
    }
    @ResponseBody
    @RequestMapping(value = "orderFromMinT2Warehouse", method = RequestMethod.GET)
    public List<com.myisu_1.isu.models.Phone.OrderPhone> orderFromMinT2Warehouse() {

        return orderPhoneServise.orderFromMinT2Warehouse();
    }
}
