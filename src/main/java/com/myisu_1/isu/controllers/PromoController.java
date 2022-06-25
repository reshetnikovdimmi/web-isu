package com.myisu_1.isu.controllers;


import com.google.gson.Gson;
import com.myisu_1.isu.models.*;
import com.myisu_1.isu.repo.MarwelPromoRepositoriy;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.PriceRepositoriy;
import com.myisu_1.isu.repo.PromoRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = { "", "mode" })
public class PromoController {

    List<Phone_Smart> phones;
    List<Phone_Smart> phone;
    List<retail_price> price;
    List<price_promo> all_promo;
    List<MarvelPromo> promoMarwel;
    List<price_promo> search;
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
    @Autowired
    private MarwelPromoRepositoriy marwelPromoRepositoriy;

   @GetMapping("/promo")
    public String Brend (Model model) {
       phone = new ArrayList<>();
       promoMarwel = new ArrayList<>();
       model.addAttribute("phone", phone);
       phones = (List<Phone_Smart>) phoneRepositoriy.findAll();
       phon = new HashSet<>();
        for(int i = 1;i< phones.size();i++){
           phon.add(phones.get(i).getBrend());
               }
                model.addAttribute("phones", Sorting(phon));
       all_promo = (List<price_promo>) promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo"));
       model.addAttribute("all_promo", all_promo);
       model.addAttribute("current_promoMarwel", current_promoMarwel());

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

    @PostMapping("/add_phone")
    public String add_phone(@RequestParam int IDupdate,
                            @RequestParam String brend,
                            @RequestParam String models,
                            @RequestParam String price,
                            @RequestParam String Promo_price,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") Date start_date,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") Date end_date,
                            @RequestParam String Marwel,
                            @RequestParam String TFN,
                            @RequestParam String ВВП,
                            @RequestParam String Merlion,
                            Model model) {

        if(IDupdate!=0) {

            price_promo pricePromo = new price_promo(IDupdate,brend, models, price, Promo_price, start_date, end_date, Marwel, TFN, ВВП, Merlion);
            promoRepositoriy.save(pricePromo);
            model.addAttribute("phones", Sorting(phon));
            all_promo = (List<price_promo>) promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo"));
            model.addAttribute("all_promo", all_promo);
            return "promo";
        }else{
            price_promo pricePromo = new price_promo(brend, models, price, Promo_price, start_date, end_date, Marwel, TFN, ВВП, Merlion);
            promoRepositoriy.save(pricePromo);

        }

        model.addAttribute("phones", Sorting(phon));
        all_promo = (List<price_promo>) promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo"));
        model.addAttribute("all_promo", all_promo);
        return "promo";
    }

    @PostMapping("/search")
    public String search(   @RequestParam String searchModels,
                            @RequestParam String startSearch,
                            @RequestParam String endSearch,
                            Model model) {


        model.addAttribute("phones", Sorting(phon));
        model.addAttribute("all_promo", search(searchModels,startSearch,endSearch));
        return "promo";
    }
    @PostMapping("/reset")
    public String reset( Model model) {
        model.addAttribute("phones", Sorting(phon));
        model.addAttribute("all_promo", all_promo);
        return "promo";
    }

    @ResponseBody
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public Optional<price_promo> update(@PathVariable("id") int id) {

        return promoRepositoriy.findById(id);
    }
    @PostMapping("/delet")
    public String delet(@RequestParam int ID,

                            Model model) {

        System.out.println(ID);
        promoRepositoriy.deleteById(ID);
        model.addAttribute("phones", Sorting(phon));
        all_promo = (List<price_promo>) promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo"));
        model.addAttribute("all_promo", all_promo);
        return "promo";
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

    public List<price_promo> search (String searchModels,String startSearch,String endSearch){
    search = new ArrayList<>();
    for ( int i =1; i<all_promo.size();i++){
    if (all_promo.get(i).getBrend().equals(searchModels)){
        search.add(new price_promo(
                all_promo.get(i).getId(),
                all_promo.get(i).getBrend(),
                all_promo.get(i).getModels(),
                all_promo.get(i).getPrice(),
                all_promo.get(i).getPrice_promo(),
                all_promo.get(i).getStartPromo(),
                all_promo.get(i).getEndPromo(),
                all_promo.get(i).getMarwel(),
                all_promo.get(i).getTfn(),
                all_promo.get(i).getVvp(),
                all_promo.get(i).getMerlion()));

    //    System.out.println(searchModels + "-"+ startSearch +"-"+ endSearch);
    }
}

        return search;
    }

    private List<MarvelPromo> current_promoMarwel() {

        List<MarvelPromo> current_promoMarwel = new ArrayList<>();

        Date endDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd", Locale.ENGLISH);
        promoMarwel = (List<MarvelPromo>) marwelPromoRepositoriy.findAll();
for(int i = 1;i<promoMarwel.size();i++){
    if(promoMarwel.get(i).getStartPromo().getTime()<=endDate.getTime() && promoMarwel.get(i).getEndPromo().getTime() >= endDate.getTime()){
        MarvelPromo marvelPromo = new MarvelPromo();
        current_promoMarwel.add(new MarvelPromo(
                promoMarwel.get(i).getId(),
                promoMarwel.get(i).getPromoCode(),
                promoMarwel.get(i).getStartPromo(),
                promoMarwel.get(i).getEndPromo(),
                promoMarwel.get(i).getArticleNumber(),
                promoMarwel.get(i).getVision(),
                promoMarwel.get(i).getNewVision(),
                promoMarwel.get(i).getDiscount(),
                promoMarwel.get(i).getCompensation(),
                promoMarwel.get(i).getCollecting(),
                promoMarwel.get(i).getStatus()
                ));

    }
}

        return current_promoMarwel;
    }

}