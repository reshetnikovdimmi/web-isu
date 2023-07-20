package com.myisu_1.isu.service;

import com.myisu_1.isu.models.price_promo;
import com.myisu_1.isu.repo.PromoRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PromoPriceListServise {
    @Autowired
    private PromoRepositoriy promoRepositoriy;
    private List<price_promo> today;
    private List<price_promo> yesterday;


    public Object startPromo(Date date) {
        loadTodayYesterday(new java.sql.Date(addDays(date, -1).getTime()),new java.sql.Date(addDays(date, 0).getTime()));
        return todayStartEndPromo(today,yesterday);
    }

    public Object endPromo(Date date) {
        loadTodayYesterday(new java.sql.Date(addDays(date, -1).getTime()),new java.sql.Date(addDays(date, 0).getTime()));
        return todayStartEndPromo(yesterday,today);
    }

    public Object promoExtension(Date date) {
        loadTodayYesterday(new java.sql.Date(addDays(date, -1).getTime()),new java.sql.Date(addDays(date, 0).getTime()));
        return extensionTodayPromo(yesterday,today);
    }

    public java.util.Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    private Object todayStartEndPromo(List<price_promo> today, List<price_promo> yesterday) {
        List<price_promo> todays = new ArrayList<>();
        for (price_promo t:today){
            if (yesterday.stream()
                    .filter(y -> t.getModels().equals(y.getModels()))
                    .findAny()
                    .orElse(null)==null){
                todays.add(t);
            }
        }
        return todays;
    }

    private Object extensionTodayPromo(List<price_promo> today, List<price_promo> yesterday) {
        List<price_promo> todays = new ArrayList<>();
        for (price_promo t:today){
            if (yesterday.stream()
                    .filter(y -> t.getModels().equals(y.getModels()))
                    .findAny()
                    .orElse(null)!=null){
                todays.add(t);
            }
        }
        return todays;
    }

    private void loadTodayYesterday(Date date1, Date date0){
        if(today==null){
            today = promoRepositoriy.startPromo(date0);
        }
        if (yesterday==null){
            yesterday = promoRepositoriy.endPromo(date1);
        }

    }

}
