package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.Suppliers;
import com.myisu_1.isu.models.bonuses.BonusesAll;
import com.myisu_1.isu.models.bonuses.CalculationBonusesPoint;
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
    public SuppliersRepositoriy suppliersRepositoriy;

    public List<Bonuses> bonusesShowAll(Bonuses bonuses) {
CalculationBonusesPoint cbp = new CalculationBonusesPoint();

        cbp.modelGb = promoRepositoriy.getPrormoAll(bonuses.getModels());
        try {
            cbp.salesPhone = salesRepositoriy.getSaleAll(cbp.dateString(bonuses.getStartDate()), cbp.dateString(bonuses.getEndDate()), bonuses.getShop());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        cbp.imeiSale = cbp.salesPhone.stream().map(Sales::getImeis).collect(Collectors.toList());
        cbp.model = cbp.salesPhone.stream().map(Sales::getNomenclature).collect(Collectors.toList());
        cbp.imeiSuppliers = suppliersRepositoriy.getListSuppliers(cbp.imeiSale, bonuses.getProvider());
        cbp.listPhone = phoneRepositoriy.getSaleModelList(cbp.model);


        return cbp.bonusesCalculation();
    }

}