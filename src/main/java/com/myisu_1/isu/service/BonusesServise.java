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
import java.text.ParseException;
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
    String[] modelPromoBonus = null;
    public List<Bonuses> bonusesShowAll(Bonuses bonuses) {
        List<Bonuses> bonuses1 = new ArrayList<>();

        List<price_promo> modelGb = null;

        try {

            modelGb = promoRepositoriy.getPrormoAll(bonuses.getModels(), dateString(bonuses.getStartDate()), null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Sales> salesPhone = null;
        try {
            salesPhone = salesRepositoriy.getSaleAll(dateString(bonuses.getStartDate()), dateString(bonuses.getEndDate()), bonuses.getShop());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<String> imeiSale = salesPhone.stream().map(Sales::getImeis).collect(Collectors.toList());
        List<String> model = salesPhone.stream().map(Sales::getNomenclature).collect(Collectors.toList());

        List<Suppliers> imeiSuppliers = suppliersRepositoriy.getListSuppliers(imeiSale, bonuses.getProvider());
        List<Phone_Smart> listPhone = phoneRepositoriy.getSaleModelList(model);



        for (Suppliers suppliers : imeiSuppliers) {

            for (Sales sales : salesPhone) {
                if (suppliers.getImei().equals(sales.getImeis())) {
                    modelPromoBonus =  promoSearch(sales.getNomenclature(), sales.getDateSales(), listPhone, modelGb);
                    if(modelPromoBonus!=null){
                    Bonuses bonusesList = new Bonuses();
                      //  System.out.println(suppliers.getImei() + "--" + sales.getNomenclature() + "--" + sales.getDateSales() + "--" + suppliers.getSuppliers());
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
                        Date startDate;
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            startDate = df.parse(modelPromoBonus[4]);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        bonusesList.setStartDate(startDate);
                     //   System.out.println(modelPromoBonus[5]);
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

    private String[] promoSearch(String nomenclature, Date dateSales, List<Phone_Smart> listPhone, List<price_promo> modelGb) {

        modelPromoBonus = null;
        String model_Gb = null;
        for (Phone_Smart phone_smart : listPhone) {

            if (phone_smart.getModel_GB().equals(nomenclature)) {

                model_Gb = phone_smart.getModel();

            }
        }
        for (price_promo pricePromo : modelGb) {

            if (pricePromo.getModels().equals(model_Gb) && dateSales.getTime()>=pricePromo.getStartPromo().getTime() && dateSales.getTime()<=pricePromo.getEndPromo().getTime()) {

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
