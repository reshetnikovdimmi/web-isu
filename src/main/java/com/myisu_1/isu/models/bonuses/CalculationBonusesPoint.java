package com.myisu_1.isu.models.bonuses;

import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.Suppliers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CalculationBonusesPoint extends BonusesAll{

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


}
