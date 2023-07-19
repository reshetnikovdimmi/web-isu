package com.myisu_1.isu.controllers;

import com.google.gson.Gson;
import com.myisu_1.isu.models.price_promo;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.PromoRepositoriy;
import com.myisu_1.isu.service.PromoPriceListServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class PromoPriceList {
    @Autowired
    private PromoRepositoriy promoRepositoriy;
    @Autowired
    private PromoPriceListServise promoPriceListServise;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @GetMapping("/promoPriceList")
    public String promoPriceList(Model model) {
        model.addAttribute("optionsPhone",phoneRepositoriy.getPhoneList());
        model.addAttribute("promoExtension", promoPriceListServise.promoExtension(new java.sql.Date(currentDate().getTime())));
        model.addAttribute("endpromo", promoPriceListServise.endPromo(new java.sql.Date(currentDate().getTime())));
        model.addAttribute("startpromo", promoPriceListServise.startPromo(new java.sql.Date(currentDate().getTime())));
        model.addAttribute("current_promo", promoRepositoriy.currentPromo(new java.sql.Date(currentDate().getTime())));
        model.addAttribute("all_promo", (List<price_promo>) promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo")));
        return "promoPriceList";
    }
    @ResponseBody
    @RequestMapping(value = "/dropDownListModels/{phone}", method = RequestMethod.GET)

    private String dropDownListModels(@PathVariable("phone") String phone) {
        Gson gson = new Gson();
        return gson.toJson(phoneRepositoriy.getModeModel_GBList(phone));

    }

    @ResponseBody
    @RequestMapping(value = "dropDownListBrendPromo/{brend}", method = RequestMethod.GET)
    public String loadBrend(@PathVariable("brend") String brend) {
        Gson gson = new Gson();
        return gson.toJson(phoneRepositoriy.getModelBrendList(brend));
    }

    Date currentDate(){
        int den, mes, god;
        final Calendar c = Calendar.getInstance();
        den = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        god = c.get(Calendar.YEAR);
        Date endDate = new Date(god-1900,mes,den);
        return endDate;
    }
}
