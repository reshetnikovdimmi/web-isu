package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Phone.Buttons;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.SaleSim_1m;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.ButtonsPhoneRepositoriy;
import com.myisu_1.isu.repo.PostRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ButtonsPhoneServise {
    Buttons button = new Buttons();
    @Autowired
    private ButtonsPhoneRepositoriy buttonsPhoneRepositoriy;
    @Autowired
    private PostRepositoriy authorizationRep;
    List<String> model;
    List<String> buttonModel;

    public List<Buttons> findAllButtonsPhone() {


        return buttonsPhoneRepositoriy.getButtonPhonePrice();
    }

    public Map<String, Map<String, String>> graduationPhone() {

        int[] arrayGraduation = {1000, 2000, 3000, 4000, 5000, 6000};
        List<Buttons> buttonsList = buttonsPhoneRepositoriy.getButtonPhonePrice();
        button.modelsGraduation = buttonsPhoneRepositoriy.getModelsGraduation();
        button.graduationButton = new TreeMap<>();

        for (Integer graduation : arrayGraduation) {
            for (String modelGraduation : button.modelsGraduation) {

                model = new ArrayList<>();

                for (Buttons buttonList : buttonsList) {

                    if (buttonList.getPrice() != null && modelGraduation.equals(buttonList.getBrend()) && graduation >= Double.parseDouble(buttonList.getPrice()) && graduation - 1000 < Double.parseDouble(buttonList.getPrice())) {

                        model.add(buttonList.getModel());
                    }
                }
                button.graduationButton.put(modelGraduation + "--" + graduation, model);
            }
        }

        Map<String, Map<String, String>> networkBalance = new TreeMap<>();
        for (Map.Entry entry : button.graduationButton.entrySet()) {

            networkBalance.put((String) entry.getKey(), networkBalance((List<String>) entry.getValue()));
        }

        return networkBalance;
    }

    private Map<String, String> networkBalance(List<String> value) {
        Map<String, String> networkBalance = new TreeMap<>();
        networkBalance.put("Remanis", buttonsPhoneRepositoriy.getRemanisButton(value));
        networkBalance.put("Sale 6_3m", buttonsPhoneRepositoriy.getSale6mButton(value));
        networkBalance.put("Sale 1m", buttonsPhoneRepositoriy.getSale1mButton(value));
        return networkBalance;
    }

    public Map<String, Map<String, Map<String, String>>> tableShopRemanis(String shop) {

        Map<String, Map<String, Map<String, Map<String, String>>>> modelShopSaleRem = new TreeMap<>();
        Map<String, Map<String, Map<String, String>>> modelShopSale = new TreeMap<>();
        Map<String, Map<String, String>> modelShop;


        for (String List : button.graduationButton.keySet()) {
            modelShop = new TreeMap<>();
            modelShop.put("ИТОГО", shopRemanisSele(button.graduationButton.get(List), shop));
            for (String Lis : button.graduationButton.get(List)) {
                modelShop.put(Lis, model(shop, Lis));
            }

            modelShopSale.put(List, modelShop);

        }
        modelShopSaleRem.put(shop, modelShopSale);


        System.out.println(shop);

        return modelShopSaleRem.get(shop);
    }

    private Map<String, String> model(String authorizationList, String lis) {
        Map<String, String> model = new TreeMap<>();


        model.put("ОСТ", buttonsPhoneRepositoriy.getShopRemanisModel(authorizationList, lis));
        model.put("ПРОД6", buttonsPhoneRepositoriy.getShopRemanisSele6mModel(authorizationList, lis));
        model.put("ПРОД1", buttonsPhoneRepositoriy.getShopRemanisSele1mModel(authorizationList, lis));
        model.put("ОСТСК", buttonsPhoneRepositoriy.getShopRemanisModel(button.authorization_ttList.get(0).getName(), lis));
        model.put("ЗАКАЗ", "0");

        return model;

    }


    public Map<String, Map<String, String>> tableShopRemanisSele(String brendPhone) {


        Map<String, Map<String, String>> shopRemanisSele = new TreeMap<>();

        for (authorization_tt authorizationList : button.authorization_ttList) {
            shopRemanisSele.put(authorizationList.getName(), shopRemanisSele(button.graduationButton.get(brendPhone), authorizationList.getName()));

        }
        return shopRemanisSele;
    }

    private Map<String, String> shopRemanisSele(List<String> strings, String name) {
        int sumsale = 0;
        Map<String, String> shopRemanisSele = new TreeMap<>();
        List<RemanisSim> remanis = buttonsPhoneRepositoriy.getShopRemanis(strings);
        for (RemanisSim sal : remanis) {
            if (name.equals(sal.getShop())) {
                sumsale += sal.getRemainsSimModem();
            }

        }

        shopRemanisSele.put("Remanis", String.valueOf(sumsale));
        sumsale = 0;

        List<SaleSim_6m> sale6m = buttonsPhoneRepositoriy.getShopRemanisSele6m(strings);
        for (SaleSim_6m sal : sale6m) {
            if (name.equals(sal.getShop())) {
                sumsale += sal.getRemainsSimModem();
            }

        }
        shopRemanisSele.put("Sale 6_3m", String.valueOf(sumsale));
        sumsale = 0;

        List<SaleSim_1m> sale1m = buttonsPhoneRepositoriy.getShopRemanisSele1m(strings);
        for (SaleSim_1m sal : sale1m) {
            if (name.equals(sal.getShop())) {
                sumsale += sal.getRemainsSimModem();
            }

        }
        shopRemanisSele.put("Sale 1m", String.valueOf(sumsale));
        sumsale = 0;


        for (RemanisSim sal : remanis) {
            if (button.authorization_ttList.get(0).getName().equals(sal.getShop())) {
                sumsale += sal.getRemainsSimModem();
            }

        }

        shopRemanisSele.put("RemanisCash", String.valueOf(sumsale));

        shopRemanisSele.put("Order", "0");

        return shopRemanisSele;
    }

    public Map<String, Integer> tableShopRemanisCash(String brendPhone) {

        Map<String, Integer> shopRemanisSele = new TreeMap<>();
        List<RemanisSim> remanis = buttonsPhoneRepositoriy.getShopRemanis(button.graduationButton.get(brendPhone));

        for (RemanisSim sal : remanis) {

            if (button.authorization_ttList.get(0).getName().equals(sal.getShop())) {
                shopRemanisSele.put(sal.getNameSimAndModem(), sal.getRemainsSimAndModem());
            }

        }
        Integer result = shopRemanisSele.entrySet()
                .stream()
                .mapToInt(Map.Entry::getValue)
                .sum();
        shopRemanisSele.put("Итого", result);

        return shopRemanisSele;
    }

    public Map<String, Map<String, String>> tabletableRequirement() {
        Map<String, Map<String, String>> shopRemanisSele = new TreeMap<>();
        button.authorization_ttList = (List<authorization_tt>) authorizationRep.findAll();

        for (authorization_tt authorizationList : button.authorization_ttList) {
            shopRemanisSele.put(authorizationList.getName(), remanisRequirement(authorizationList.getName()));
        }

        return shopRemanisSele;
    }

    private Map<String, String> remanisRequirement(String name) {
        int sumrem = 0;
        int sumsal6 = 0;
        int sumsal1 = 0;
        buttonModel = buttonsPhoneRepositoriy.getModelsButton();
        List<RemanisSim> remanis = buttonsPhoneRepositoriy.getShopRemanis(buttonModel);
        List<SaleSim_6m> sale6m = buttonsPhoneRepositoriy.getShopRemanisSele6m(buttonModel);
        List<SaleSim_1m> sale1m = buttonsPhoneRepositoriy.getShopRemanisSele1m(buttonModel);
        Map<String, String> remanisRequirement = new TreeMap<>();
        for (SaleSim_1m sal : sale1m) {
            if (name.equals(sal.getShop())) {
                sumsal1 += sal.getRemainsSimModem();
            }

        }
        for (SaleSim_6m sal : sale6m) {
            if (name.equals(sal.getShop())) {
                sumsal6 += sal.getRemainsSimModem();
            }

        }
        for (RemanisSim rem : remanis) {
            if (name.equals(rem.getShop())) {
                sumrem += rem.getRemainsSimAndModem();
            }

        }
        int max = Math.max(Math.max(sumsal1, sumsal6 / 2), 27);
        Double percent = Double.valueOf(sumrem) / Double.valueOf(max) * 100;
        remanisRequirement.put("RemBut", String.valueOf(sumrem));
        remanisRequirement.put("Reg", String.valueOf(max));
        remanisRequirement.put("Percent", String.format("%.0f", percent) + "%");
        return remanisRequirement;
    }
}
