package com.myisu_1.isu.models.bonuses;

import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.Suppliers;
import com.myisu_1.isu.models.price_promo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BonusesAll {
    public String[] modelPromoBonus = null;
    public List<Bonuses> bonuses1= null;
    public List<price_promo> modelGb = null;
    public List<Sales> salesPhone = null;
    public List<String> imeiSale = null;
    public List<String> model = null;
    public List<Suppliers> imeiSuppliers=null;
    public List<Phone_Smart> listPhone = null;


    public List<Bonuses> bonusesCalculation() {


        return bonuses1;
    }

    final String[] promoSearch(String nomenclature, Date dateSales, List<Phone_Smart> listPhone, List<price_promo> modelGb) {

        modelPromoBonus = null;
        String model_Gb = null;
        for (Phone_Smart phone_smart : listPhone) {
            if (phone_smart.getModel_GB().equals(nomenclature)) {
                model_Gb = phone_smart.getModel();
            }
        }
        for (price_promo pricePromo : modelGb) {
            if (pricePromo.getModels().equals(model_Gb) && dateSales.getTime() >= pricePromo.getStartPromo().getTime() && dateSales.getTime() <= pricePromo.getEndPromo().getTime()) {
                modelPromoBonus = new String[6];
                modelPromoBonus[0] = pricePromo.getMarwel();
                modelPromoBonus[1] = pricePromo.getMerlion();
                modelPromoBonus[2] = pricePromo.getTfn();
                modelPromoBonus[3] = pricePromo.getVvp();
                modelPromoBonus[4] = String.valueOf(pricePromo.getStartPromo());
                modelPromoBonus[5] = String.valueOf(pricePromo.getEndPromo());
            }
        }
        return modelPromoBonus;
    }

    public   Date dateString(Date stringCellValue) throws ParseException {
        if (stringCellValue == null) {
            return null;
        }
        return new java.sql.Date(stringCellValue.getTime());
    }

    public   Date stringDate(String stringCellValue) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse(stringCellValue);
        return startDate;
    }
}
