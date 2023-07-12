package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.models.Marwel.MarvelPromo;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.bonuses.CalculationBonusesPoint;
import com.myisu_1.isu.models.bonuses.LoadBonusesAll;
import com.myisu_1.isu.models.bonuses.MarvelBonus;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
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
    @Autowired
    public PostRepositoriy postRepositoriy;
    @Autowired
    private MarvelClassifierRepositoriy marvelClassifierRepositoriy;

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

    public List<Bonuses> bonusesSumAll(Bonuses bonuses) {

        LoadBonusesAll lba = new LoadBonusesAll();
        lba.modelGb = promoRepositoriy.getPrormoAll(null);


        try {
            lba.salesPhone = salesRepositoriy.getSaleAll(lba.dateString(bonuses.getStartDate()), lba.dateString(bonuses.getEndDate()), null);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        lba.imeiSale = lba.salesPhone.stream().map(Sales::getImeis).collect(Collectors.toList());

        lba.model = lba.salesPhone.stream().map(Sales::getNomenclature).collect(Collectors.toList());

        lba.imeiSuppliers = suppliersRepositoriy.getListSuppliers(lba.imeiSale, null);

        lba.listPhone = phoneRepositoriy.getSaleModelList(lba.model);
        List<Bonuses> bonuses1 = lba.bonusesCalculation();

        return bonuses1;
    }


    public List<Bonuses> bonusesNoT2(Bonuses bonuses) {
        CalculationBonusesPoint cbp = new CalculationBonusesPoint();

        cbp.modelGb = promoRepositoriy.getPrormoAll(bonuses.getModels());
        try {
            cbp.salesPhone = salesRepositoriy.getSaleNoT2(cbp.dateString(bonuses.getStartDate()), cbp.dateString(bonuses.getEndDate()), postRepositoriy.getShopMult());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        cbp.imeiSale = cbp.salesPhone.stream().map(Sales::getImeis).collect(Collectors.toList());

        cbp.model = cbp.salesPhone.stream().map(Sales::getNomenclature).collect(Collectors.toList());
        cbp.imeiSuppliers = suppliersRepositoriy.getListSuppliers(cbp.imeiSale, "ЦЕНТР ДИСТРИБЬЮЦИИ ООО Теле2 ");
        cbp.listPhone = phoneRepositoriy.getSaleModelList(cbp.model);


        return cbp.bonusesCalculation();
    }

    public List<Bonuses> marvelReportings(MarvelPromo marvelPromo) {

        MarvelBonus mB = new MarvelBonus();
        mB.modelMarvelPromo = marvelClassifierRepositoriy.getRainbowNomenclature();

        mB.modelGb = promoRepositoriy.getPrormoAll(null);
        try {
            mB.salesPhone = salesRepositoriy.getSaleXiaomi(mB.dateString(marvelPromo.getStartPromo()), mB.dateString(marvelPromo.getEndPromo()), mB.modelMarvelPromo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        mB.imeiSale = mB.salesPhone.stream().map(Sales::getImeis).collect(Collectors.toList());

        mB.model = mB.salesPhone.stream().map(Sales::getNomenclature).collect(Collectors.toList());
        mB.imeiSuppliers = suppliersRepositoriy.getListSuppliers(mB.imeiSale, "МАРВЕЛ КТ ООО");
        mB.listPhone = phoneRepositoriy.getSaleModelList(mB.model);



        return mB.bonusesCalculation();
    }
}