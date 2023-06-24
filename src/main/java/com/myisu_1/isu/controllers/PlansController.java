package com.myisu_1.isu.controllers;

import com.myisu_1.isu.repo.SalesRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class PlansController {
    @Autowired
    private SalesRepositoriy salesRepositoriy;

    @GetMapping("/plans")
    public String plans(Model model) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_YEAR, 1);
        Date yearStartDate = c.getTime();
        model.addAttribute("surveyMap", salesRepositoriy.getMap(yearStartDate));
        Map<String, Map<String, String>> shop = new TreeMap<>();

        List<Object> kk = salesRepositoriy.getShopMap(yearStartDate);
        Set<String> disting = new HashSet<>();
        for (Object jj : kk) {
            for (int i = 0; i < convertObjectToList(jj).size(); i++) {
                disting.add((String) convertObjectToList(jj).get(0));
            }

        }
        for (String str : disting) {
            Map<String, String> indicator = new TreeMap<>();
            for (Object jj : kk) {
                for (int i = 0; i < convertObjectToList(jj).size(); i++) {
                    if (str.equals(convertObjectToList(jj).get(0))) {
                        indicator.put(convertObjectToList(jj).get(1).toString(), convertObjectToList(jj).get(2).toString());
                    }
                }
            }
            shop.put(str, indicator);
        }
        model.addAttribute("surveyDisting", salesRepositoriy.getDistinct(yearStartDate));
        model.addAttribute("surveyShopMap", shop);
        return "plans";
    }

    public static List<Object> convertObjectToList(Object obj) {
        List<Object> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }
}
