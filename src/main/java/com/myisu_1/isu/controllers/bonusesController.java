package com.myisu_1.isu.controllers;

import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.models.*;
import com.myisu_1.isu.repo.*;
import com.myisu_1.isu.service.BonusesServise;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



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
    @Autowired
    private ValueVUERepositoriy valueVUERepositoriy;
    @Autowired
    private PostRepositoriy authorizationTt;
    @Autowired
    private BonusesServise bonusesServise;


    List<Sales> all_listSales;
    List<Suppliers> all_listSuppliers;
    List<price_promo> all_promo;
    List<Phone_Smart> phones;
    List<Combo> combos;
    List<ValueVUE> vues;
    List<authorization_tt> authorizationTtList;
    List<String> shop;
    List<String> neT2;
    public double count;


    @GetMapping("/bonuses")
    public String bonuses(Model model) {
        model.addAttribute("optionsShop",authorizationTt.getShopList());
        model.addAttribute("optionsPhone",phoneRepositoriy.getPhoneList());
        model.addAttribute("optionsProvider",suppliersRepositoriy.getProviderList());
        all_listSales = (List<Sales>) salesRepositoriy.findAll();
        all_listSuppliers = (List<Suppliers>) suppliersRepositoriy.findAll();
        all_promo = (List<price_promo>) promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo"));
        phones = (List<Phone_Smart>) phoneRepositoriy.findAll();
        combos = comboRepositoriy.findAll();
        vues = valueVUERepositoriy.findAll();
        authorizationTtList = (List<authorization_tt>) authorizationTt.findAll();
        storeInitialization();
        return "bonuses";
    }
    @RequestMapping(value = "/dropDownListModelGB/{phone}", method = RequestMethod.GET)
    private String dropDownListModelGB(@PathVariable("phone") String phone, Model model) {
        model.addAttribute("optionsBrend",phoneRepositoriy.getModel_GBList(phone));
        return "bonuses::dropDownListModelGB";

    }

    @PostMapping("/buttonShowAll")
    private ResponseEntity<List<Bonuses>> buttonShowAll(@RequestBody Bonuses bonuses, Model model) {

        return new ResponseEntity<>(bonusesServise.bonusesShowAll(bonuses), HttpStatus.OK);


    }

    private void storeInitialization() {
//
        shop = new ArrayList<>();
        neT2 = new ArrayList<>();
        for (int i = 0;i<authorizationTtList.size();i++){
            if (authorizationTtList.get(i).getClusterT2().length() != 0) {
            shop.add(authorizationTtList.get(i).getName());
           }else {
                neT2.add(authorizationTtList.get(i).getName());
            }
        }
    }

    @PostMapping("/bonuses")
    public String entrance(@DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd") Date stop, Model model) throws ParseException {

        model.addAttribute("MARWEL", MARWEL(start,stop,"МАРВЕЛ КТ ООО",shop));
        model.addAttribute("MARWELcount", count);
        model.addAttribute("TFN", MARWEL(start,stop,"ТФН ООО", shop));
        model.addAttribute("TFNcount", count);
        model.addAttribute("MERLION", MARWEL(start,stop,"БЕРКС ООО", shop));
        model.addAttribute("MERLIONcount", count);
        model.addAttribute("VVP", MARWEL(start,stop,"ЦЕНТР ДИСТРИБЬЮЦИИ ООО Теле2 ", shop));
        model.addAttribute("VVPcount", count);
        model.addAttribute("neVVP", MARWEL(start,stop,"ЦЕНТР ДИСТРИБЬЮЦИИ ООО Теле2 ", neT2));
        model.addAttribute("neVVPcount", count);
        model.addAttribute("Combo", COMBO(start,stop));
        model.addAttribute("ComboCount", Math.ceil(count));
        model.addAttribute("VUE", VUE(start,stop));
        model.addAttribute("VUECount", Math.ceil(count));
        return "bonuses";
    }

    private Object VUE(Date start, Date stop) {
        count = 0.0;
        List<ValueVUE> vueList = new ArrayList<ValueVUE>();

        for (int i = 0;i<vues.size();i++){
            if(vues.get(i).getDateOFsale().getTime()>=start.getTime() && vues.get(i).getDateOFsale().getTime()<=stop.getTime()){
                vueList.add(new ValueVUE(
                        vues.get(i).getId(),
                        vues.get(i).getNomenclature(),
                        vues.get(i).getImei(),
                        vues.get(i).getQuality(),
                        vues.get(i).getDateOFsale(),
                        vues.get(i).getShop(),
                        vues.get(i).getValueVUE()));
                count = count + vues.get(i).getValueVUE()*5;
            }
        }
        return vueList;
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

    private Object MARWEL(Date start, Date stop, String vendor, List<String> shopList) throws ParseException {

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
       if(vendor.equals("ЦЕНТР ДИСТРИБЬЮЦИИ ООО Теле2 ")){
           for (int f = 0; f< shopList.size(); f++){
               if (shopList.get(f).equals(all_listSales.get(i).getShop())) {
                   bonuses.add(bonus(i,v,vendor));
               }

           }
               }else {

                   bonuses.add(bonus(i,v,vendor));
               }
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
