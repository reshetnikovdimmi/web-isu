package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.*;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Controller
public class bonusesController {

    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private PromoRepositoriy promoRepositoriy;
    @Autowired
    private SalesRepositoriy salesRepositoriy;
    @Autowired
    private SuppliersRepositoriy suppliersRepositoriy;
    @Autowired
    private ComboRepositoriy comboRepositoriy;

    List<Sales> all_listSales;
    List<Suppliers> all_listSuppliers;
    List<price_promo> all_promo;
    List<Phone_Smart> phones;
    List<Combo> combos;
   public double count;



    @GetMapping("/bonuses")
    public String bonuses(Model model) {
        all_listSales = (List<Sales>) salesRepositoriy.findAll();
        all_listSuppliers = (List<Suppliers>) suppliersRepositoriy.findAll();
        all_promo = (List<price_promo>) promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo"));
        phones = (List<Phone_Smart>) phoneRepositoriy.findAll();
        combos = comboRepositoriy.findAll();

        return "bonuses";
    }
    @PostMapping("/bonuses")
    public String entrance(@DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd") Date stop, Model model) throws ParseException {

        model.addAttribute("MARWEL", MARWEL(start,stop,"МАРВЕЛ КТ ООО"));
        model.addAttribute("MARWELcount", count);
        model.addAttribute("TFN", MARWEL(start,stop,"ТФН ООО"));
        model.addAttribute("TFNcount", count);
        model.addAttribute("MERLION", MARWEL(start,stop,"БЕРКС ООО"));
        model.addAttribute("MERLIONcount", count);
        model.addAttribute("VVP", MARWEL(start,stop,"ЦЕНТР ДИСТРИБЬЮЦИИ ООО Теле2 "));
        model.addAttribute("VVPcount", count);
        model.addAttribute("Combo", COMBO(start,stop));
        model.addAttribute("ComboCount", count);
        return "bonuses";
    }

    private Object COMBO(Date start, Date stop) {
        count = 0.0;
        List<Combo> combo = new ArrayList<Combo>();

        for (int i = 0;i<combos.size();i++){
         if(combos.get(i).getDate().getTime()>=start.getTime() && combos.get(i).getDate().getTime()<=stop.getTime()){
             combo.add(new Combo(
                     combos.get(i).getId(),
                     combos.get(i).getDate(),
                     combos.get(i).getImei(),
                     combos.get(i).getCombo(),
                     combos.get(i).getResume(),
                     combos.get(i).getReason(),
                     combos.get(i).getSize(),
                     combos.get(i).getPayment()));
             count = count + combos.get(i).getPayment();
             }
        }
        return combo;
    }

    private Object MARWEL(Date start, Date stop, String vendor) throws ParseException {

List<Bonuses> bonuses = new ArrayList<>();
        count = 0.0;
        for (int i = 0;i<all_listSales.size();i++) {
            if (dateString(all_listSales.get(i).getDateSales()).getTime() >= start.getTime() && all_listSales.get(i).getDateSales().getTime() <= stop.getTime()) {
                for (int j = 0; j < all_listSuppliers.size(); j++) {

                    if (all_listSales.get(i).getImeis().equals(all_listSuppliers.get(j).getImei()) && all_listSuppliers.get(j).getSuppliers().equals(vendor)) {

                        for (int l = 0; l < phones.size(); l++) {
                            if (all_listSales.get(i).getNomenclature().equals(phones.get(l).getModel())) {
                                for (int v = 0; v < all_promo.size(); v++) {
                                    if (phones.get(l).getModel_GB().equals(all_promo.get(v).getModels()) && all_listSales.get(i).getDateSales().getTime() >= all_promo.get(v).getStartPromo().getTime() && all_listSales.get(i).getDateSales().getTime() <= all_promo.get(v).getEndPromo().getTime()) {
        bonuses.add(bonus(i,v,vendor));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return bonuses;
    }

    private Bonuses bonus(int i, int v, String vendor) {

      Bonuses bonuses = new Bonuses();
      if (vendor.equals("МАРВЕЛ КТ ООО")){
        bonuses =  new Bonuses(all_listSales.get(i).getNomenclature(),
                all_listSales.get(i).getImeis(),
                String.valueOf(all_listSales.get(i).getDateSales()),
                all_promo.get(v).getStartPromo() + "<-->"+ all_promo.get(v).getEndPromo(),
                Double.parseDouble (all_promo.get(v).getMarwel().replace(",",".")));
          count = count + Double.parseDouble (all_promo.get(v).getMarwel().replace(",","."));
      }else if (vendor.equals("ТФН ООО")){
          bonuses =  new Bonuses(all_listSales.get(i).getNomenclature(),
                  all_listSales.get(i).getImeis(),
                  String.valueOf(all_listSales.get(i).getDateSales()),
                  all_promo.get(v).getStartPromo() + "<-->"+ all_promo.get(v).getEndPromo(),
                  Double.parseDouble (all_promo.get(v).getTfn().replace(",",".")));
         count = count + Double.parseDouble (all_promo.get(v).getTfn().replace(",","."));
      }else if (vendor.equals("БЕРКС ООО")){

          bonuses =  new Bonuses(all_listSales.get(i).getNomenclature(),
                  all_listSales.get(i).getImeis(),
                  String.valueOf(all_listSales.get(i).getDateSales()),
                  all_promo.get(v).getStartPromo() + "<-->"+ all_promo.get(v).getEndPromo(),
                  Double.parseDouble(all_promo.get(v).getMerlion().replace(",",".")));
          count = count + Double.parseDouble (all_promo.get(v).getMerlion().replace(",","."));
      }else if (vendor.equals("ЦЕНТР ДИСТРИБЬЮЦИИ ООО Теле2 ")){
          //System.out.println("all_listSuppliers.get(i).getSuppliers()");
          bonuses =  new Bonuses(all_listSales.get(i).getNomenclature(),
                  all_listSales.get(i).getImeis(),
                  String.valueOf(all_listSales.get(i).getDateSales()),
                  all_promo.get(v).getStartPromo() + "<-->"+ all_promo.get(v).getEndPromo(),
                  Double.parseDouble (all_promo.get(v).getVvp().replace(",",".")));
          count = count + Double.parseDouble (all_promo.get(v).getVvp().replace(",","."));
      }
      return bonuses;
    }

    private Date dateString(Date stringCellValue) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(formatter.format(stringCellValue));
    }
}
