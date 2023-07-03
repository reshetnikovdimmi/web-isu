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

import java.text.ParseException;
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

        List<price_promo> modelGb = null;
        try {
            modelGb = promoRepositoriy.getPrormoAll(bonuses.getModels(), dateString(bonuses.getStartDate()), dateString(bonuses.getEndDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Sales> salesPhone = salesRepositoriy.getSaleAll(bonuses.getStartDate(), bonuses.getEndDate(), bonuses.getShop());
        List<String> imeiSale = salesPhone.stream().map(Sales::getImeis).collect(Collectors.toList());
        List<String> model = salesPhone.stream().map(Sales::getNomenclature).collect(Collectors.toList());
        List<Suppliers> imeiSuppliers = suppliersRepositoriy.getListSuppliers(imeiSale, bonuses.getProvider());
        List<Phone_Smart> listPhone = phoneRepositoriy.getSaleModelList(model);

        for (Suppliers suppliers : imeiSuppliers) {
            for (Sales sales : salesPhone) {
                if (suppliers.getImei().equals(sales.getImeis())) {
                    promoSearch(sales.getNomenclature(), sales.getDateSales(), listPhone, modelGb);
                    System.out.println(suppliers.getImei() + "--" + sales.getNomenclature() + "--" + sales.getDateSales() + "--" + suppliers.getSuppliers());
                }
            }

        }

        System.out.println("ok");
        return null;
    }

    private String[] promoSearch(String nomenclature, Date dateSales, List<Phone_Smart> listPhone, List<price_promo> modelGb) {
        String[] modelPromoBonus = null;
        String model_Gb = null;
        for (Phone_Smart phone_smart : listPhone) {
            if (phone_smart.getModel_GB().equals(nomenclature)) {
                model_Gb = phone_smart.getModel();

            }
        }
        for (price_promo pricePromo : modelGb) {
            if (pricePromo.getModels().equals(model_Gb)) {
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

    private Date dateString(Date stringCellValue) throws ParseException {
        if (stringCellValue == null) {
            return null;
        }
        return new java.sql.Date(stringCellValue.getTime());
    }

}
