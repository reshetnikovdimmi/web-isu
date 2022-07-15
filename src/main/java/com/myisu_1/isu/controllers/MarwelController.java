package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Distinct;
import com.myisu_1.isu.models.MarvelPromo;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.price_promo;
import com.myisu_1.isu.repo.MarwelPromoRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


@Controller
public class MarwelController {
    @Autowired
    private MarwelPromoRepositoriy marwelPromoRepositoriy;
    List<MarvelPromo> promoMarwel;
    @GetMapping("/Marwel")
    public String Marwel(Model model) {
        model.addAttribute("promoCode", promoCode());
        model.addAttribute("promoCodeDistinct", promoCodeDistinct());
        return "Marwel";
    }

    private List<Distinct> promoCodeDistinct() {

        List<Distinct> listDistinct = new ArrayList<>();
        HashSet<String> hashDistinct = new HashSet<>();
        for(int i=0;i<promoMarwel.size();i++){
            hashDistinct.add(promoMarwel.get(i).getPromoCode());
        }
        Iterator<String> i = hashDistinct.iterator();
        while (i.hasNext())
           // System.out.println(i.next());
        listDistinct.add(new Distinct(i.next()));
        return  listDistinct;
    }

    private List<MarvelPromo> promoCode() {
        promoMarwel = (List<MarvelPromo>) marwelPromoRepositoriy.findAll();
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
}
