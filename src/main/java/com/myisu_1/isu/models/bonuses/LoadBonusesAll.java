package com.myisu_1.isu.models.bonuses;

import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.Suppliers;
import com.myisu_1.isu.models.price_promo;
import lombok.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data

@Getter
@Setter
public class LoadBonusesAll extends CalculationBonusesPoint{


    @Override
    public List<Bonuses> bonusesCalculation() {
        bonuses1 = new ArrayList<>();
        double marwel = 0,tfn = 0,merlion = 0,vvp=0, nevvp=0;
        for (Suppliers suppliers : imeiSuppliers) {
            for (Sales sales : salesPhone) {
                if (suppliers.getImei().equals(sales.getImeis())) {
                    modelPromoBonus = promoSearch(sales.getNomenclature(), sales.getDateSales(), listPhone, modelGb);
                    if (modelPromoBonus != null) {
                        switch (suppliers.getSuppliers()) {

                            case "МАРВЕЛ КТ ООО":
                                marwel += Integer.parseInt(modelPromoBonus[0]);

                                break;
                            case "ТФН ООО":
                                tfn += Integer.parseInt(modelPromoBonus[2]);

                                break;
                            case "БЕРКС ООО":
                                merlion += Integer.parseInt(modelPromoBonus[1]);

                                break;
                            case "ЦЕНТР ДИСТРИБЬЮЦИИ ООО Теле2 ":
                                vvp += Integer.parseInt(modelPromoBonus[3]);

                                break;
                            case "ЦЕНТР ДИСТРИБЬЮЦИИ ООО":
                                nevvp += Integer.parseInt(modelPromoBonus[6]);

                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
        bonuses1.add(new Bonuses("МАРВЕЛ КТ ООО",marwel));
        bonuses1.add(new Bonuses("ТФН ООО",tfn));
        bonuses1.add(new Bonuses("БЕРКС ООО",merlion));
        bonuses1.add(new Bonuses("ЦЕНТР ДИСТРИБЬЮЦИИ ООО Теле2 ",vvp));
        bonuses1.add(new Bonuses("ЦЕНТР ДИСТРИБЬЮЦИИ ООО",nevvp));

        return bonuses1;
    }




}
