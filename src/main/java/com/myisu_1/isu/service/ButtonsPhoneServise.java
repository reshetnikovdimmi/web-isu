package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.models.Phone.Buttons;
import com.myisu_1.isu.models.Phone.ButtonsPhone;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.SaleSim_1m;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.distribution.AnalysisDistribution;
import com.myisu_1.isu.repo.ButtonsPhoneRepositoriy;
import com.myisu_1.isu.repo.PostRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ButtonsPhoneServise extends AnalysisDistribution {
    Buttons button = new Buttons();
    @Autowired
    private ButtonsPhoneRepositoriy buttonsPhoneRepositoriy;
    @Autowired
    private PostRepositoriy authorizationRep;
    List<Buttons> buttons;
    List<String> buttonModel;
    Map<String, Map<String, Map<String, Map<String, String>>>> modelShopSaleRem;

    public List<Buttons> findAllButtonsPhone() {
        return buttonsPhoneRepositoriy.getButtonPhonePrice();
    }

    public List<OrderRecommendations> remainsCashGroup() {
        List<Buttons> buttons = buttonsPhoneRepositoriy.getButtonPhonePrice();
        List<ButtonsPhone> bpList = new ArrayList<>();
        for (Buttons b : buttons) {
            ButtonsPhone bp = new ButtonsPhone();
            bp.setGroup(searchGroup(b.getPrice(), b.getBrend()));
            bp.setBrend(b.getBrend());
            bp.setModel(b.getModel());
            bpList.add(bp);
        }
        buttonsPhoneRepositoriy.deleteAll();
        buttonsPhoneRepositoriy.saveAll(bpList);
        remains = buttonsPhoneRepositoriy.getRemainsShopButton();
        warehouse = authorizationRep.getWarehouseList();
        return remainsCashGroup(buttonsPhoneRepositoriy.getGroupView());
    }
    private String searchGroup(String price, String brend) {

        if(price!=null){
            if(Double.valueOf(price)<=1000)return brend+ "--"+"1000";
            if(Double.valueOf(price)<=2000)return brend+ "--"+"2000";
            if(Double.valueOf(price)<=3000)return brend+ "--"+"3000";
            if(Double.valueOf(price)<=4000)return brend+ "--"+"4000";
            if(Double.valueOf(price)<=5000)return brend+ "--"+"5000";
            if(Double.valueOf(price)<=6000)return brend+ "--"+"6000";
        }

        return null;
    }

    private Map<String, String> networkBalance(List<String> value) {
        Map<String, String> networkBalance = new TreeMap<>();
        networkBalance.put("Remanis", buttonsPhoneRepositoriy.getRemanisButton(value));
        networkBalance.put("Sale 6_3m", buttonsPhoneRepositoriy.getSale6mButton(value));
        networkBalance.put("Sale 1m", buttonsPhoneRepositoriy.getSale1mButton(value));
        return networkBalance;
    }

    public Map<String, Map<String, Map<String, String>>> tableShopRemanis(String shop) {

        if (!modelShopSaleRem.isEmpty() && modelShopSaleRem.containsKey(shop)) {
            return modelShopSaleRem.get(shop);
        } else {

            Map<String, Map<String, Map<String, String>>> modelShopSale = new TreeMap<>();
            Map<String, Map<String, String>> modelShop;


            for (String List : button.graduationButton.keySet()) {
                modelShop = new TreeMap<>();
                modelShop.put("ИТОГО", shopRemanisSele(button.graduationButton.get(List), shop,List));
                for (String Lis : button.graduationButton.get(List)) {
                    modelShop.put(Lis, model(shop, Lis, List));
                }

                modelShopSale.put(List, modelShop);

            }
            modelShopSaleRem.put(shop, modelShopSale);
        }

        return modelShopSaleRem.get(shop);
    }

    private Map<String, String> model(String authorizationList, String lis, String list) {
        Map<String, String> model = new TreeMap<>();


        model.put("ОСТ", buttonsPhoneRepositoriy.getShopRemanisModel(authorizationList, lis));
        model.put("ПРОД6", buttonsPhoneRepositoriy.getShopRemanisSele6mModel(authorizationList, lis));
        model.put("ПРОД1", buttonsPhoneRepositoriy.getShopRemanisSele1mModel(authorizationList, lis));
        if(modelShopSaleRem.containsKey(button.authorization_ttList.get(0).getName())){


            model.put("ОСТСК", String.valueOf(modelShopSaleRem.get(button.authorization_ttList.get(0).getName()).get(list).get(lis).get("ОСТСК")));
        }else {
            model.put("ОСТСК", buttonsPhoneRepositoriy.getShopRemanisModel(button.authorization_ttList.get(0).getName(), lis));
        }

        model.put("ЗАКАЗ", "0");

        return model;

    }


    public Map<String, Map<String, String>> tableShopRemanisSele(String brendPhone) {


        Map<String, Map<String, String>> shopRemanisSele = new TreeMap<>();

        for (Authorization_tt authorizationList : button.authorization_ttList) {
            shopRemanisSele.put(authorizationList.getName(), shopRemanisSele(button.graduationButton.get(brendPhone), authorizationList.getName(), brendPhone));

        }
        return shopRemanisSele;
    }

    private Map<String, String> shopRemanisSele(List<String> strings, String name, String list) {
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
if(modelShopSaleRem.containsKey(button.authorization_ttList.get(0).getName())){


    shopRemanisSele.put("RemanisCash", String.valueOf(tableShopRemanisCash(list).get("Итого")));
}else {
    shopRemanisSele.put("RemanisCash", String.valueOf(sumsale));
}


        shopRemanisSele.put("Order", "0");

        return shopRemanisSele;
    }

    public Map<String, Integer> tableShopRemanisCash(String brendPhone) {
        Map<String, Integer> shopRemanisSele = new TreeMap<>();
        if (!modelShopSaleRem.containsKey(button.authorization_ttList.get(0).getName())) {
            tableShopRemanis(button.authorization_ttList.get(0).getName());
        }

        for (Map.Entry entry : modelShopSaleRem.get(button.authorization_ttList.get(0).getName()).get(brendPhone).entrySet()) {
            if (!entry.getKey().equals("ИТОГО") && modelShopSaleRem.get(button.authorization_ttList.get(0).getName()).get(brendPhone).get(entry.getKey()).get("ОСТСК") != null) {
                shopRemanisSele.put(String.valueOf(entry.getKey()), Integer.parseInt(modelShopSaleRem.get(button.authorization_ttList.get(0).getName()).get(brendPhone).get(entry.getKey()).get("ОСТСК")));
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
        button.authorization_ttList = (List<Authorization_tt>) authorizationRep.findAll();

        for (Authorization_tt authorizationList : button.authorization_ttList) {
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

    public Map<String, Map<String, Map<String, String>>> tableUpDistributionButton(String shop, String models, String quantity, String brend) {
        String orderResult= String.valueOf(Integer.parseInt(modelShopSaleRem.get(shop).get(brend).get("ИТОГО").get("Order")) + Integer.parseInt(quantity));
        String rem = String.valueOf(Integer.parseInt(modelShopSaleRem.get(shop).get(brend).get(models).get("ОСТСК")) - Integer.parseInt(quantity));;
        String remCash = String.valueOf(Integer.parseInt(modelShopSaleRem.get(shop).get(brend).get("ИТОГО").get("RemanisCash")) - Integer.parseInt(quantity));

        modelShopSaleRem.get(shop).get(brend).get(models).replace("ЗАКАЗ", quantity);
        modelShopSaleRem.get(shop).get(brend).get("ИТОГО").replace("Order", orderResult);
        modelShopSaleRem.get(button.authorization_ttList.get(0).getName()).get(brend).get(models).replace("ОСТСК", rem);
        modelShopSaleRem.get(shop).get(brend).get(models).replace("ОСТСК", rem);
        modelShopSaleRem.get(shop).get(brend).get("ИТОГО").replace("RemanisCash", remCash);



        return  modelShopSaleRem.get(shop);
    }

    public Map<String, Map<String, Map<String, Map<String, String>>>> exselDistributionButto() {

        return modelShopSaleRem;
    }
}
