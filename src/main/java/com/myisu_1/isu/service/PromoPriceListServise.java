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
    List<price_promo> startPromo;
    List<price_promo> endPromo;
    List<price_promo> promoExtension;

    public Object startPromo(Date date) {
        loadTodayYesterday(new java.sql.Date(addDays(date, 0).getTime()),new java.sql.Date(addDays(date, -1).getTime()));
        startPromo = (List<price_promo>) todayStartEndPromo(today,yesterday);
        return startPromo;
    }

    public Object endPromo(Date date) {
        loadTodayYesterday(new java.sql.Date(addDays(date, 0).getTime()),new java.sql.Date(addDays(date, -1).getTime()));
        endPromo = (List<price_promo>) todayStartEndPromo(yesterday,today);
        return endPromo;
    }

    public Object promoExtension(Date date) {
        loadTodayYesterday(new java.sql.Date(addDays(date, 0).getTime()),new java.sql.Date(addDays(date, -1).getTime()));
        promoExtension = (List<price_promo>) extensionTodayPromo(today,yesterday);
        return promoExtension;
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

    private void loadTodayYesterday(Date date0, Date date1){

            today = promoRepositoriy.startPromo(date0);
            yesterday = promoRepositoriy.endPromo(date1);
    }


    public List<price_promo> getPromoExtension() {
        return promoExtension;
    }

    public List<price_promo> getEndPromo() {
        return endPromo;
    }

    public List<price_promo> getStartPromo() {
        return startPromo;
    }
}
