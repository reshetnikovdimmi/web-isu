package com.myisu_1.isu.models.bonuses;

import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.Suppliers;
import com.myisu_1.isu.models.price_promo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BonusesAll {
    public String[] modelPromoBonus = null;
    public List<Bonuses> bonuses1= null;
    public List<price_promo> modelGb = null;
    public List<Sales> salesPhone = null;
    public List<String> imeiSale = null;
    public List<String> model = null;
    public List<Suppliers> imeiSuppliers=null;
    public List<Phone_Smart> listPhone = null;


    public abstract List<Bonuses> bonusesCalculation();

    public abstract String[] promoSearch(String nomenclature, Date dateSales, List<Phone_Smart> listPhone, List<price_promo> modelGb);
}
