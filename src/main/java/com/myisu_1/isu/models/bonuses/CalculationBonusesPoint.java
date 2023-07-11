package com.myisu_1.isu.models.bonuses;

import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.Suppliers;
import com.myisu_1.isu.models.price_promo;
import lombok.*;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data

public class CalculationBonusesPoint extends BonusesAll{
@Override
    public List<Bonuses> bonusesCalculation() {

        bonuses1 = new ArrayList<>();
        for (Suppliers suppliers : imeiSuppliers) {
            for (Sales sales : salesPhone) {
                if (suppliers.getImei().equals(sales.getImeis())) {
                    modelPromoBonus = promoSearch(sales.getNomenclature(), sales.getDateSales(), listPhone, modelGb);
                    if (modelPromoBonus != null) {
                        Bonuses bonusesList = new Bonuses();
                        switch (suppliers.getSuppliers()) {
                            case "МАРВЕЛ КТ ООО":
                                bonusesList.setCompensation(Double.valueOf(modelPromoBonus[0]));
                                break;
                            case "ТФН ООО":
                                bonusesList.setCompensation(Double.valueOf(modelPromoBonus[2]));
                                break;
                            case "БЕРКС ООО":
                                bonusesList.setCompensation(Double.valueOf(modelPromoBonus[1]));
                                break;
                            case "ЦЕНТР ДИСТРИБЬЮЦИИ ООО Теле2 ":
                                bonusesList.setCompensation(Double.valueOf(modelPromoBonus[3]));
                                break;
                            case "ЦЕНТР ДИСТРИБЬЮЦИИ ООО":
                                bonusesList.setCompensation(Double.valueOf(modelPromoBonus[6]));
                                break;
                            default:
                                break;
                        }
                        try {
                            bonusesList.setStartDate(stringDate(modelPromoBonus[4]));
                            bonusesList.setEndDate(stringDate(modelPromoBonus[5]));
                            bonusesList.setDataSale(String.valueOf(stringDate(String.valueOf(sales.getDateSales()))));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        bonusesList.setShop(sales.getShop());
                        bonusesList.setProvider(suppliers.getSuppliers());
                        bonusesList.setImei(suppliers.getImei());
                        bonusesList.setModels(sales.getNomenclature());
                        bonuses1.add(bonusesList);
                    }
                }
            }
        }

        return bonuses1;
    }



    public String[] promoSearch(String nomenclature, Date dateSales, List<Phone_Smart> listPhone, List<price_promo> modelGb) {

        modelPromoBonus = null;
        String model_Gb = null;
        for (Phone_Smart phone_smart : listPhone) {
            if (phone_smart.getModel_GB().equals(nomenclature)) {
                model_Gb = phone_smart.getModel();
            }
        }
        for (price_promo pricePromo : modelGb) {
            if (pricePromo.getModels().equals(model_Gb) && dateSales.getTime() >= pricePromo.getStartPromo().getTime() && dateSales.getTime() <= pricePromo.getEndPromo().getTime()) {
                modelPromoBonus = new String[7];
                modelPromoBonus[0] = pricePromo.getMarwel();
                modelPromoBonus[1] = pricePromo.getMerlion();
                modelPromoBonus[2] = pricePromo.getTfn();
                modelPromoBonus[3] = pricePromo.getVvp();
                modelPromoBonus[4] = String.valueOf(pricePromo.getStartPromo());
                modelPromoBonus[5] = String.valueOf(pricePromo.getEndPromo());
                modelPromoBonus[6] = pricePromo.getVvp();
            }
        }
        return modelPromoBonus;
    }


}
