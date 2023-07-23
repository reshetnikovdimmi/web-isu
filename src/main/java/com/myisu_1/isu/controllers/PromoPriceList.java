package com.myisu_1.isu.controllers;

import com.google.gson.Gson;
import com.myisu_1.isu.exporte.ExselFileExporte;
import com.myisu_1.isu.exporte.ExselFileExportePromo;
import com.myisu_1.isu.models.price_promo;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.PriceRepositoriy;
import com.myisu_1.isu.repo.PromoRepositoriy;
import com.myisu_1.isu.service.PromoPriceListServise;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PromoPriceList {
    @Autowired
    private PromoRepositoriy promoRepositoriy;
    @Autowired
    private PromoPriceListServise promoPriceListServise;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private PriceRepositoriy priceRepositoriy;

    @GetMapping("/promoPriceList")
    public String promoPriceList(Model model) {
        model.addAttribute("optionsPhone", phoneRepositoriy.getPhoneList());
        model.addAttribute("promoExtension", promoPriceListServise.promoExtension(new java.sql.Date(currentDate().getTime())));
        model.addAttribute("endpromo", promoPriceListServise.endPromo(new java.sql.Date(currentDate().getTime())));
        model.addAttribute("startpromo", promoPriceListServise.startPromo(new java.sql.Date(currentDate().getTime())));
        model.addAttribute("current_promo", promoRepositoriy.currentPromo(new java.sql.Date(currentDate().getTime())));
        model.addAttribute("all_promo", promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo")));

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

    @ResponseBody
    @RequestMapping(value = "price/{brend}", method = RequestMethod.GET)
    public Integer price(@PathVariable("brend") String brend) {
        return priceRepositoriy.getPriceModelGB(brend.replaceAll("_", "/"));
    }

    @ResponseBody
    @RequestMapping(value = "deletePromo/{id}", method = RequestMethod.GET)
    public List<price_promo> deletePromo(@PathVariable("id") Integer id) {
        promoRepositoriy.deleteById(id);
        return promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo"));
    }

    @ResponseBody
    @RequestMapping(value = "updatePromo/{id}", method = RequestMethod.GET)
    public Optional<price_promo> updatePromo(@PathVariable("id") Integer id) {

        return promoRepositoriy.findById(id);
    }

    @ResponseBody
    @RequestMapping(value = "savePromo", method = RequestMethod.POST)
    private List<price_promo> savePromo(@RequestBody price_promo pricePromo, Model model) {
        if (pricePromo.getId() != 0) {
            promoRepositoriy.save(new price_promo(
                    pricePromo.getId(),
                    pricePromo.getBrend(),
                    pricePromo.getModels(),
                    pricePromo.getPrice(),
                    pricePromo.getPrice_promo(),
                    new java.sql.Date(pricePromo.getStartPromo().getTime()),
                    new java.sql.Date(pricePromo.getEndPromo().getTime()),
                    pricePromo.getMarwel(),
                    pricePromo.getTfn(),
                    pricePromo.getVvp(),
                    pricePromo.getMerlion()));
        } else {
            promoRepositoriy.save(new price_promo(pricePromo.getBrend(),
                    pricePromo.getModels(),
                    pricePromo.getPrice(),
                    pricePromo.getPrice_promo(),
                    new java.sql.Date(pricePromo.getStartPromo().getTime()),
                    new java.sql.Date(pricePromo.getEndPromo().getTime()),
                    pricePromo.getMarwel(),
                    pricePromo.getTfn(),
                    pricePromo.getVvp(),
                    pricePromo.getMerlion()));
        }
        return promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo"));


    }

    @ResponseBody
    @RequestMapping(value = "searchPromo", method = RequestMethod.POST)
    private List<price_promo> searchPromo(@RequestBody price_promo pricePromo, Model model) {

             return promoRepositoriy.searchPromo(pricePromo.getBrend(), pricePromo.getModels(), sqlDate(pricePromo.getStartPromo()), sqlDate(pricePromo.getEndPromo()));
    }

    @RequestMapping(value = "/showPromo/{dates}", method = RequestMethod.GET)

    private String showPromo(@PathVariable("dates") String dates, Model model) throws ParseException {

        model.addAttribute("endpromo", promoPriceListServise.endPromo(new java.sql.Date(stringDate(dates).getTime())));
        model.addAttribute("startpromo", promoPriceListServise.startPromo(new java.sql.Date(stringDate(dates).getTime())));
        model.addAttribute("promoExtension", promoPriceListServise.promoExtension(new java.sql.Date(stringDate(dates).getTime())));
        model.addAttribute("current_promo", promoRepositoriy.currentPromo(new java.sql.Date(currentDate().getTime())));
        return "promoPriceList::showPromo";
    }
    @GetMapping("/exselPromo")
    public void downloadExselFile(HttpServletResponse response) throws IOException, ParseException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=start.xlsx");

        ByteArrayInputStream inputStream = ExselFileExportePromo.exportPrisePromoFile(promoPriceListServise.getEndPromo(),promoPriceListServise.getStartPromo(),promoPriceListServise.getPromoExtension(),phoneRepositoriy.findAll());

        IOUtils.copy(inputStream, response.getOutputStream());


    }
    private java.sql.Date sqlDate(Date startPromo) {
        if (startPromo != null) {
            return new java.sql.Date(startPromo.getTime());
        }
        return null;
    }

    Date currentDate() {
        int den, mes, god;
        final Calendar c = Calendar.getInstance();
        den = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        god = c.get(Calendar.YEAR);
        Date endDate = new Date(god - 1900, mes, den);
        return endDate;
    }
    public   Date stringDate(String stringCellValue) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse(stringCellValue);
        return startDate;
    }
}
