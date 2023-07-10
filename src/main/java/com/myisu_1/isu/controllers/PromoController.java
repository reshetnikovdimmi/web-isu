package com.myisu_1.isu.controllers;


import com.google.gson.Gson;
import com.myisu_1.isu.exporte.ExselFileExporte;
import com.myisu_1.isu.models.*;
import com.myisu_1.isu.models.Marwel.MarvelPromo;
import com.myisu_1.isu.repo.*;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

import java.util.*;

@Controller
@RequestMapping(value = { "", "mode" })
public class PromoController {

    List<Phone_Smart> phones;
    List<Phone_Smart> phone;
    List<retail_price> price;
    List<price_promo> all_promo;
    List<MarvelPromo> promoMarwel;
    List<ListOFgoods> promoVVP;
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
    @Autowired
    private ListOFgoodsRepositoriy listOFgoodsRepositoriy;

    @GetMapping("/promo")
    public String Brend(Model model) {
        phone = new ArrayList<>();
        promoMarwel = new ArrayList<>();
        model.addAttribute("phone", phone);
        phones = (List<Phone_Smart>) phoneRepositoriy.findAll();
        phon = new HashSet<>();
        for (int i = 1; i < phones.size(); i++) {
            phon.add(phones.get(i).getBrend());
        }
        model.addAttribute("phones", Sorting(phon));
        all_promo = (List<price_promo>) promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo"));
        model.addAttribute("all_promo", all_promo);
        model.addAttribute("current_promoMarwel", current_promoMarwel());
        model.addAttribute("current_promo", current_promo());
        model.addAttribute("startpromo", startpromo());
        model.addAttribute("endpromo", endpromo());
        model.addAttribute("promoCode", promoCode());
        model.addAttribute("current_promoVVP", current_promoVVP());

        return "promo";
    }

    @GetMapping("/exsel")
    public void downloadExselFile(HttpServletResponse response) throws IOException {

response.setContentType("application/octet-stream");
response.setHeader("Content-Disposition","attachment; filename=start.xlsx");

        ByteArrayInputStream inputStream = ExselFileExporte.exportPrisePromoFile(startpromo(),phones,endpromo());

        IOUtils.copy(inputStream, response.getOutputStream());


    }

    @ResponseBody
    @RequestMapping(value = "brend/{brend}", method = RequestMethod.GET)
    public String loadBrend(@PathVariable("brend") String brend) {
        phonGb = new HashSet<>();
        phonGb.add("----");
        for (int i = 0; i < phones.size(); i++) {
            if (brend.equals((phones.get(i).getBrend()))) {
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
        for (int i = 0; i < phones.size(); i++) {
            if (mode.replaceAll("_", "/").equals((phones.get(i).getModel_GB()))) {
                name = phones.get(i).getModel();
            }
        }
        price = (List<retail_price>) priceRepositoriy.findAll();

        for (int i = 0; i < price.size(); i++) {
            if (name.equals((price.get(i).getName()))) {
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

        if (IDupdate != 0) {

            price_promo pricePromo = new price_promo(IDupdate, brend, models, price, Promo_price, start_date, end_date, Marwel, TFN, ВВП, Merlion);
            promoRepositoriy.save(pricePromo);
            model.addAttribute("phones", Sorting(phon));
            all_promo = (List<price_promo>) promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo"));
            model.addAttribute("all_promo", all_promo);
            model.addAttribute("current_promoMarwel", current_promoMarwel());
            model.addAttribute("current_promo", current_promo());
            model.addAttribute("startpromo", startpromo());
            model.addAttribute("endpromo", endpromo());
            model.addAttribute("promoCode", promoCode());
            model.addAttribute("current_promoVVP", current_promoVVP());
            return "promo";
        } else {
            price_promo pricePromo = new price_promo(brend, models, price, Promo_price, start_date, end_date, Marwel, TFN, ВВП, Merlion);
            promoRepositoriy.save(pricePromo);

        }

        model.addAttribute("phones", Sorting(phon));
        all_promo = (List<price_promo>) promoRepositoriy.findAll(Sort.by(Sort.Direction.DESC, "startPromo"));
        model.addAttribute("all_promo", all_promo);
        model.addAttribute("current_promoMarwel", current_promoMarwel());
        model.addAttribute("current_promo", current_promo());
        model.addAttribute("startpromo", startpromo());
        model.addAttribute("endpromo", endpromo());
        model.addAttribute("promoCode", promoCode());
        model.addAttribute("current_promoVVP", current_promoVVP());
        return "promo";
    }

    @PostMapping("/search")
    public String search(@RequestParam String searchModels,
                         @RequestParam String startSearch,
                         @RequestParam String endSearch,
                         Model model) {


        model.addAttribute("phones", Sorting(phon));
        model.addAttribute("all_promo", search(searchModels, startSearch, endSearch));
        model.addAttribute("current_promoMarwel", current_promoMarwel());
        model.addAttribute("current_promo", current_promo());
        model.addAttribute("startpromo", startpromo());
        model.addAttribute("endpromo", endpromo());
        model.addAttribute("promoCode", promoCode());
        model.addAttribute("current_promoVVP", current_promoVVP());
        return "promo";
    }

    @PostMapping("/reset")
    public String reset(Model model) {
        model.addAttribute("phones", Sorting(phon));
        model.addAttribute("all_promo", all_promo);
        model.addAttribute("current_promoMarwel", current_promoMarwel());
        model.addAttribute("current_promo", current_promo());
        model.addAttribute("startpromo", startpromo());
        model.addAttribute("endpromo", endpromo());
        model.addAttribute("promoCode", promoCode());
        model.addAttribute("current_promoVVP", current_promoVVP());
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
        model.addAttribute("current_promoMarwel", current_promoMarwel());
        model.addAttribute("current_promo", current_promo());
        model.addAttribute("startpromo", startpromo());
        model.addAttribute("endpromo", endpromo());
        model.addAttribute("promoCode", promoCode());
        model.addAttribute("current_promoVVP", current_promoVVP());
        return "promo";
    }

    public List<Distinct> Sorting(HashSet<String> phon) {
        distinct = new Distinct();
        one = new ArrayList<>();
        TreeSet myTreeSet = new TreeSet();
        myTreeSet.addAll(phon);
        Iterator<String> i = myTreeSet.iterator();
        while (i.hasNext())
            one.add(new Distinct(i.next()));
        return one;
    }

    public List<price_promo> search(String searchModels, String startSearch, String endSearch) {
        search = new ArrayList<>();
        for (int i = 0; i < all_promo.size(); i++) {
            if (all_promo.get(i).getBrend().equals(searchModels)) {
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



        promoMarwel = (List<MarvelPromo>) marwelPromoRepositoriy.findAll();
        for (int i = 0; i < promoMarwel.size(); i++) {
            if (promoMarwel.get(i).getStartPromo().getTime() <= current_date().getTime() && promoMarwel.get(i).getEndPromo().getTime() >= current_date().getTime() ) {
              //  System.out.println(promoMarwel.get(i).getStartPromo());
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

    private List<price_promo> current_promo() {
        List<price_promo> current_promo = new ArrayList<>();


        for (int i = 0; i < all_promo.size(); i++) {
           // System.out.println(all_promo.get(i).getStartPromo() +"+1");
            if (all_promo.get(i).getStartPromo().getTime() <= current_date().getTime() && all_promo.get(i).getEndPromo().getTime() >= current_date().getTime()) {

                current_promo.add(new price_promo(
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
                        all_promo.get(i).getMerlion()
                ));

        }
        }
        return current_promo;
    }
    private List<ListOFgoods> current_promoVVP() {
        List<ListOFgoods> current_promoVVP = new ArrayList<>();
        promoVVP = (List<ListOFgoods>) listOFgoodsRepositoriy.findAll();
        //System.out.println(promoVVP.size() +"------->");
        for (int i = 0; i < promoVVP.size(); i++) {
           // if (promoVVP.get(i).getStartPromo().getTime() <= current_date().getTime() && promoVVP.get(i).getEndPromo().getTime() >= current_date().getTime()) {

            if (promoVVP.get(i).getPricePromo() !=0) {
           //     System.out.println(promoVVP.get(i).getStartPromo().getTime()+"--->"+current_date().getTime());
               current_promoVVP.add(new ListOFgoods(
                       promoVVP.get(i).getId(),
                       promoVVP.get(i).getModel(),
                       promoVVP.get(i).getPrice(),
                       promoVVP.get(i).getPricePromo(),
                       promoVVP.get(i).getStartPromo(),
                       promoVVP.get(i).getEndPromo(),
                       promoVVP.get(i).getDiscountUE()
                ));

            }
        }
        return current_promoVVP;
    }

    private List<price_promo> startpromo() {

        List<price_promo> startpromo = new ArrayList<>();

        for (int i = 0; i < all_promo.size(); i++) {

            if (all_promo.get(i).getStartPromo().getTime() == current_date().getTime()) {

                startpromo.add(new price_promo(
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
                        all_promo.get(i).getMerlion()
                ));
        }

        }
        return startpromo;
    }
    private List<price_promo> endpromo() {
        List<price_promo> endpromo = new ArrayList<>();

        for (int i = 0; i < all_promo.size(); i++) {
            if (all_promo.get(i).getEndPromo().getTime() == current_date().getTime()) {
                endpromo.add(new price_promo(
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
                        all_promo.get(i).getMerlion()
                ));

            }
        }
        return endpromo;
    }
    private List<MarvelPromo> promoCode() {
        List<MarvelPromo> promoCode = new ArrayList<>();

        for (int i = 0; i < promoMarwel.size(); i++) {
          //  if (promoMarwel.get(i).getEndPromo().getTime() == current_date().getTime()) {
                promoCode.add(new MarvelPromo(
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

         //   }
        }
        return promoCode;
    }
    Date current_date(){
        int den, mes, god;
        final Calendar c = Calendar.getInstance();
        den = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        god = c.get(Calendar.YEAR);
        Date endDate = new Date(god-1900,mes,den);
        return endDate;
    }
    Date current_date1(){
        int den, mes, god;
        final Calendar c = Calendar.getInstance();
        den = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        god = c.get(Calendar.YEAR);
        Date endDate = new Date(god-1900,mes,den);
        return endDate;
    }
}