package com.myisu_1.isu.service;

import com.myisu_1.isu.models.price_promo;
import com.myisu_1.isu.repo.PromoRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class PromoPriceListServise {
    @Autowired
    private PromoRepositoriy promoRepositoriy;

    public Object startPromo(Date date) {
        System.out.println(addDays(date, 1));
        List<price_promo> tomorrow = promoRepositoriy.startPromo(new java.sql.Date(addDays(date, 1).getTime()));
        List<price_promo> today = promoRepositoriy.startPromo(new java.sql.Date(date.getTime()));
        List<price_promo> yesterday = promoRepositoriy.endPromo(new java.sql.Date(addDays(date, -1).getTime()));



        return promoRepositoriy.startPromo(new java.sql.Date(date.getTime()));
    }

    public Object endPromo(Date date) {

        return promoRepositoriy.endPromo(new java.sql.Date(date.getTime()));
    }

    public Object promoExtension(Date date) {


        return null;
    }

    public java.util.Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}
