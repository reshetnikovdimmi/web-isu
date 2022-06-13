package com.myisu_1.isu.controllers;

import com.google.gson.Gson;
import com.myisu_1.isu.models.Distinct;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.price_promo;
import com.myisu_1.isu.models.retail_price;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.PriceRepositoriy;
import com.myisu_1.isu.repo.PromoRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping(value = { "", "mode" })
public class MenuController {

    List<Phone_Smart> phones;
    List<Phone_Smart> phone;
    List<retail_price> price;
    List<price_promo> all_promo;
    Distinct distinct;
    HashSet<String> phon;
    HashSet<String> phonGb;
    List<Distinct> one;
    
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private PriceRepositoriy priceRepositoriy;
    @Autowired
    private PromoRepositoriy promoRepositoriy;

   @GetMapping("/promo")
    public String Brend (Model model) {
       phone = new ArrayList<>();
       model.addAttribute("phone", phone);
       phones = (List<Phone_Smart>) phoneRepositoriy.findAll();
       phon = new HashSet<>();
        for(int i = 1;i< phones.size();i++){
           phon.add(phones.get(i).getBrend());
               }
                model.addAttribute("phones", Sorting(phon));
       all_promo = (List<price_promo>) promoRepositoriy.findAll();
       model.addAttribute("all_promo", all_promo);
        return "promo";
    }

    @ResponseBody
    @RequestMapping(value = "brend/{brend}", method = RequestMethod.GET)
    public String loadBrend(@PathVariable("brend") String brend) {
        phonGb = new HashSet<>();
        phonGb.add("----");
        for(int i = 1;i< phones.size();i++){
            if (brend.equals((phones.get(i).getBrend()))){
                phonGb.add(phones.get(i).getModel_GB());
            }
        }
            Gson gson = new Gson();
        return gson.toJson(Sorting(phonGb));
    }

    @ResponseBody
    @RequestMapping(value = "mode/{mode}", method = RequestMethod.GET)
    public String loadModel(@PathVariable("mode") String mode) {
       String name = null;
        String price_phone = null;
        for(int i = 1;i< phones.size();i++){
            if (mode.replaceAll("_", "/").equals((phones.get(i).getModel_GB()))){
                     name = phones.get(i).getModel();
            }
        }
        price = (List<retail_price>) priceRepositoriy.findAll();

        for(int i = 1;i< price.size();i++){
                if (name.equals((price.get(i).getName()))){
                price_phone = price.get(i).getPrice();
                }
        }
        return price_phone;
    }

    public List<Distinct> Sorting (HashSet<String> phon){
        distinct = new Distinct();
        one = new ArrayList<>();
        TreeSet myTreeSet = new TreeSet();
        myTreeSet.addAll(phon);
        Iterator<String> i = myTreeSet.iterator();
        while (i.hasNext())
            one.add(new Distinct(i.next()));
        return one;
    }

    @PostMapping("/add_phone")
    public String add_phone(@RequestParam String brend,
                            @RequestParam String models,
                            @RequestParam String price,
                            @RequestParam String Promo_price,
                            @RequestParam String start_date,
                            @RequestParam String end_date,
                            @RequestParam String Marwel,
                            @RequestParam String TFN,
                            @RequestParam String ВВП,
                            @RequestParam String Merlion,
                            Model model) {
System.out.println(price + "-"+ models + "-"+ Marwel +"-"+ Merlion);
        price_promo pricePromo = new price_promo(brend,models,price,Promo_price,start_date,end_date,Marwel,TFN,ВВП,Merlion);
        promoRepositoriy.save(pricePromo);
        model.addAttribute("phones", Sorting(phon));
        all_promo = (List<price_promo>) promoRepositoriy.findAll();
        model.addAttribute("all_promo", all_promo);
        return "promo";
    }

}