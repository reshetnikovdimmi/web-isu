package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.Bonuses;

import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.Suppliers;
import com.myisu_1.isu.models.price_promo;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.PromoRepositoriy;
import com.myisu_1.isu.repo.SalesRepositoriy;
import com.myisu_1.isu.repo.SuppliersRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BonusesServise {
    @Autowired
    private PromoRepositoriy promoRepositoriy;
    @Autowired
    private SalesRepositoriy salesRepositoriy;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private SuppliersRepositoriy suppliersRepositoriy;

    public List<Bonuses> bonusesShowAll(Bonuses bonuses) {
List<Bonuses> bonuses1 = new ArrayList<>();
        System.out.println(bonuses.getStartDate()+"--"+bonuses.getEndDate());

        List<price_promo> modelGb = promoRepositoriy.getPrormoAll(bonuses.getModels(), bonuses.getStartDate(), bonuses.getEndDate());
        List<Sales> salesPhone = salesRepositoriy.getSaleAll(bonuses.getStartDate(), bonuses.getEndDate(), bonuses.getShop());
        List<String> imeiSale = salesPhone.stream().map(Sales::getImeis).collect(Collectors.toList());

        List<String> model = salesPhone.stream().map(Sales::getNomenclature).collect(Collectors.toList());
        List<Suppliers> imeiSuppliers = suppliersRepositoriy.getListSuppliers(imeiSale,bonuses.getProvider());
        List<Phone_Smart> listPhone = phoneRepositoriy.getSaleModelList(model);
        for (price_promo pricePromo:modelGb){
            System.out.println(pricePromo.getModels());
        }
        for (Suppliers suppliers: imeiSuppliers) {
           // System.out.println(suppliers.getImei());
            for (Sales sales:salesPhone){
                for (Phone_Smart phoneSmart:listPhone){
                    for (price_promo pricePromo:modelGb){


                        if(suppliers.getImei().equals(sales.getImeis()) && sales.getNomenclature().equals(phoneSmart.getModel_GB())/* && pricePromo.getModels().equals(phoneSmart.getModel())&& pricePromo.getStartPromo().getTime()<=sales.getDateSales().getTime()&& pricePromo.getEndPromo().getTime()>=sales.getDateSales().getTime()*/){

                          //  System.out.println(suppliers.getImei()+"--"+phoneSmart.getModel_GB()+"--"+sales.getDateSales()+"--"+suppliers.getSuppliers());
                        }
                    }


                }

            }

        }




        System.out.println("ok");
        return null;
    }
}
