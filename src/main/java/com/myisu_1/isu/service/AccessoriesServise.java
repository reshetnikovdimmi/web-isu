package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.models.accessories.SettingAccessories;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AccessoriesServise {
    @Autowired
    private SettingAccessoriesRepositoriy settingAccessoriesRepositoriy;
    @Autowired
    private RangeAccessoriesRepositoriy rangeAccessoriesRepositoriy;
    @Autowired
    private RemanisAccessoriesRepositoriy remanisAccessoriesRepositoriy;
    @Autowired
    private AccessoriesSale1Repositoriy accessoriesSale1Repositoriy;
    @Autowired
    private AccessoriesSale6Repositoriy accessoriesSale6Repositoriy;
    @Autowired
    private PostRepositoriy authorization_ttRepositoriy;
    Map<String, Map<String, String>> accessoriesGrup;
    String grups;
    List<Authorization_tt> authorization_ttList;
    Map<String, Map<String, Map<String, Map<String, String>>>> modelShopSaleRem;
    Map<String, Map<String, Map<String, String>>> shopRemanisSeless;

    public Map<String, Map<String, String>> AccessoriesCategory() {
        Map<String, Map<String, String>> accessoriesCategoryAll = new TreeMap<>();
        modelShopSaleRem = new TreeMap<>();
        accessoriesGrup = new TreeMap<>();

        List<SettingAccessories> settingAccessoriesList = (List<SettingAccessories>) settingAccessoriesRepositoriy.findAll();

        for (SettingAccessories accessoriesCategory : settingAccessoriesList) {
            Map<String, String> indicatorsGrup = new TreeMap<>();

            indicatorsGrup.put("ГРУППА", accessoriesCategory.getGroup());
            indicatorsGrup.put("ЦЕНМИН", String.valueOf(accessoriesCategory.getPriceMin()));
            indicatorsGrup.put("ЦЕНМАКС", String.valueOf(accessoriesCategory.getPriceMax()));

            accessoriesGrup.put(accessoriesCategory.getGroup() + " " +
                    accessoriesCategory.getPriceMin() + "-" +
                    accessoriesCategory.getPriceMax(), indicatorsGrup);

            accessoriesCategoryAll.put(
                    accessoriesCategory.getGroup() + " " +
                            accessoriesCategory.getPriceMin() + "-" +
                            accessoriesCategory.getPriceMax(),
                    indicators(accessoriesCategory.getGroup(), accessoriesCategory.getPriceMin(), accessoriesCategory.getPriceMax()));
        }

        return accessoriesCategoryAll;

    }

    private Map<String, String> indicators(String group, int priceMin, int priceMax) {
        Map<String, String> indicators = new TreeMap<>();

        List<String> nomeklature = rangeAccessoriesRepositoriy.getButtonPhonePrice(group, priceMin, priceMax);

        indicators.put("ОСТАТОК", remanisAccessoriesRepositoriy.getRemainsAccessories(nomeklature));
        String sale1 = accessoriesSale1Repositoriy.getAccessoriesSale1(nomeklature);
        indicators.put("ПРОД 1М", sale1);
        String sale6 = accessoriesSale6Repositoriy.getAccessoriesSale6(nomeklature);
        indicators.put("ПРОД 6/3М", sale6);
        String requirement = "0";

        if (sale1 == null) {
            sale1 = "0";
        }
        if (sale6 == null) {
            sale6 = "0";
        }
        requirement = String.valueOf(Math.max(Integer.parseInt(sale1), Integer.parseInt(sale6) / 6));
        indicators.put("Потребность", requirement);
        return indicators;
    }

    public Map<String, Map<String, String>> accessoriesCategoryShop(String grup) {
        grups = grup;
        long start = System.currentTimeMillis();
        Map<String, Map<String, String>> accessoriesCategoryAll = new TreeMap<>();
        authorization_ttList = (List<Authorization_tt>) authorization_ttRepositoriy.findAll();
        Map<String, String> indicators;

        List<String> nomeklature = rangeAccessoriesRepositoriy.getButtonPhonePrice(accessoriesGrup.get(grup.trim()).get("ГРУППА"), Integer.parseInt(accessoriesGrup.get(grup.trim()).get("ЦЕНМИН")), Integer.parseInt(accessoriesGrup.get(grup.trim()).get("ЦЕНМАКС")));

        Integer minRemanis = settingAccessoriesRepositoriy.getMinRemanis(accessoriesGrup.get(grup.trim()).get("ГРУППА"), Integer.parseInt(accessoriesGrup.get(grup.trim()).get("ЦЕНМИН")), Integer.parseInt(accessoriesGrup.get(grup.trim()).get("ЦЕНМАКС")));

        for (Authorization_tt authorization : authorization_ttList) {
            indicators = new TreeMap<>();
            String remanis = remanisAccessoriesRepositoriy.getRemainsAccessoriesShop(authorization.getName(), nomeklature);
            String sale1 = accessoriesSale1Repositoriy.getAccessoriesSale1Shop(authorization.getName(), nomeklature);
            String sale6 = accessoriesSale6Repositoriy.getAccessoriesSale6Shop(authorization.getName(), nomeklature);

            indicators.put("ОСТАТОК", remanis);
            indicators.put("ПРОД 1М", sale1);
            indicators.put("ПРОД 6/3М", sale6);

            if (sale1 == null) {
                sale1 = "0";
            }
            if (sale6 == null) {
                sale6 = "0";
            }
            if (remanis == null) {
                remanis = "0";
            }

            indicators.put("Потребность", String.valueOf(Math.max(Integer.parseInt(sale1), Integer.parseInt(sale6) / 6)+minRemanis-Integer.parseInt(remanis)));
            accessoriesCategoryAll.put(authorization.getName(), indicators);

        }
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        System.out.println(df.format(new Date(timeWorkCode)));
        return accessoriesCategoryAll;
    }

    public Map<String, Map<String, Map<String, String>>> accessoriesCategoryNomenclatureShop(String shop) {
        if (modelShopSaleRem == null || !modelShopSaleRem.containsKey(shop)) {


            Map<String, String> shopRemanisSele = null;
            Map<String, Map<String, String>> shopRemanisSeles;

            shopRemanisSeless = new TreeMap<>();

            for (Map.Entry entry : accessoriesGrup.entrySet()) {
                Map<String, String> shopRemanisSel = (Map<String, String>) entry.getValue();
                shopRemanisSeles = new HashMap<>();

                int sumost = 0;
                int sumSale1 = 0;
                int sumSale6 = 0;
                int sumCash = 0;
                int sumOrder = 0;

                List<String> nomeklature = rangeAccessoriesRepositoriy.getButtonPhonePrice(shopRemanisSel.get("ГРУППА"), Integer.parseInt(shopRemanisSel.get("ЦЕНМИН")), Integer.parseInt(shopRemanisSel.get("ЦЕНМАКС")));

                for (String nomeklatures : nomeklature) {
                    shopRemanisSele = new TreeMap<>();
                    String ostCash = null;
                    String order = null;

                    if (modelShopSaleRem.get(shop) != null) {
                        order = modelShopSaleRem.get(shop).get(entry.getKey()).get(nomeklatures).get("ЗАКАЗ");
                    }

                    String ost = remanisAccessoriesRepositoriy.getNameAccesShopPriceMinMax(nomeklatures, shop, Integer.parseInt(accessoriesGrup.get(entry.getKey()).get("ЦЕНМИН")), Integer.parseInt(accessoriesGrup.get(grups).get("ЦЕНМАКС")));
                    String sale1 = accessoriesSale1Repositoriy.getSale1AccesShopPriceMinMax(nomeklatures, shop, Integer.parseInt(accessoriesGrup.get(entry.getKey()).get("ЦЕНМИН")), Integer.parseInt(accessoriesGrup.get(grups).get("ЦЕНМАКС")));
                    String sale6 = accessoriesSale6Repositoriy.getSale6AccesShopPriceMinMax(nomeklatures, shop, Integer.parseInt(accessoriesGrup.get(entry.getKey()).get("ЦЕНМИН")), Integer.parseInt(accessoriesGrup.get(grups).get("ЦЕНМАКС")));
                    if (!modelShopSaleRem.containsKey(authorization_ttList.get(0).getName())) {

                        ostCash = remanisAccessoriesRepositoriy.getNameAccesShopPriceMinMax(nomeklatures, authorization_ttList.get(0).getName(), Integer.parseInt(accessoriesGrup.get(entry.getKey()).get("ЦЕНМИН")), Integer.parseInt(accessoriesGrup.get(grups).get("ЦЕНМАКС")));

                    } else {

                        ostCash = String.valueOf(modelShopSaleRem.get(authorization_ttList.get(0).getName()).get(entry.getKey()).get(nomeklatures).get("ОСТАТОК"));
                    }


                    if (ost != null) {
                        sumost = sumost + Integer.parseInt(ost);
                    }
                    if (sale1 != null) {
                        sumSale1 = sumSale1 + Integer.parseInt(sale1);
                    }
                    if (sale6 != null) {
                        sumSale6 = sumSale6 + Integer.parseInt(sale6);
                    }
                    if (ostCash != null && ostCash != "") {
                        sumCash = sumCash + Integer.parseInt(ostCash);
                    }
                    if (order != null && order != "") {
                        sumOrder = sumOrder + Integer.parseInt(order);
                    }
                    if (ost == null) {
                        ost = "";
                    }
                    if (sale1 == null) {
                        sale1 = "";
                    }
                    if (sale6 == null) {
                        sale6 = "";
                    }
                    if (ostCash == null) {
                        ostCash = "";
                    }

                    shopRemanisSele.put("ОСТАТОК", ost);
                    shopRemanisSele.put("ПРОД 1М", sale1);
                    shopRemanisSele.put("ПРОД 6/3М", sale6);

                    shopRemanisSele.put("ОСТСК", ostCash);
                    shopRemanisSele.put("ЗАКАЗ", order);
                    shopRemanisSeles.put(nomeklatures, shopRemanisSele);

                }

                Map<String, String> shopRemanisSum = new HashMap<>();
                shopRemanisSum.put("ОСТАТОК", String.valueOf(sumost));
                shopRemanisSum.put("ПРОД 1М", String.valueOf(sumSale1));
                shopRemanisSum.put("ПРОД 6/3М", String.valueOf(sumSale6));
                shopRemanisSum.put("ОСТСК", String.valueOf(sumCash));
                shopRemanisSum.put("ЗАКАЗ", String.valueOf(sumOrder));
                shopRemanisSeles.put("ИТОГО", shopRemanisSum);

                shopRemanisSeless.put((String) entry.getKey(), shopRemanisSeles);
            }
            modelShopSaleRem.put(shop, shopRemanisSeless);
        }
        return modelShopSaleRem.get(shop);
    }

    public Map<String, Map<String, String>> accessoriesCategoryCash(String grup) {
        if (modelShopSaleRem == null || !modelShopSaleRem.containsKey(authorization_ttList.get(0).getName())) {
            accessoriesCategoryNomenclatureShop(authorization_ttList.get(0).getName());

        }

        return modelShopSaleRem.get(authorization_ttList.get(0).getName()).get(grup);
    }

    public Map<String, Map<String, Map<String, String>>> tableUpDistributionShop(String shop, String models, String quantity, String brend) {


        String orderResult = String.valueOf(Integer.parseInt(modelShopSaleRem.get(shop).get(brend).get("ИТОГО").get("ЗАКАЗ")) + Integer.parseInt(quantity));
        modelShopSaleRem.get(shop).get(brend).get("ИТОГО").replace("ЗАКАЗ", orderResult);

        String remCash = String.valueOf(Integer.parseInt(modelShopSaleRem.get(shop).get(brend).get("ИТОГО").get("ОСТСК")) - Integer.parseInt(quantity));
        modelShopSaleRem.get(shop).get(brend).get("ИТОГО").replace("ОСТСК", remCash);

        String sumRemCash = String.valueOf(Integer.parseInt(modelShopSaleRem.get(authorization_ttList.get(0).getName()).get(brend).get("ИТОГО").get("ОСТАТОК")) - Integer.parseInt(quantity));
        modelShopSaleRem.get(authorization_ttList.get(0).getName()).get(brend).get("ИТОГО").replace("ОСТАТОК", sumRemCash);

        String rem = String.valueOf(Integer.parseInt(modelShopSaleRem.get(shop).get(brend).get(models).get("ОСТСК")) - Integer.parseInt(quantity));


        for (Map.Entry entry : modelShopSaleRem.entrySet()) {
            modelShopSaleRem.get(entry.getKey()).get(brend).get(models).replace("ОСТСК", rem);
        }


        modelShopSaleRem.get(authorization_ttList.get(0).getName()).get(brend).get(models).replace("ОСТАТОК", rem);
        modelShopSaleRem.get(shop).get(brend).get(models).replace("ЗАКАЗ", quantity);


        return modelShopSaleRem.get(shop);
    }

    public Map<String, Map<String, String>> accessoriesCategoryMaxSale(String grop) {
        List<String> nomeklature = rangeAccessoriesRepositoriy.getButtonPhonePrice(accessoriesGrup.get(grop.trim()).get("ГРУППА"), Integer.parseInt(accessoriesGrup.get(grop.trim()).get("ЦЕНМИН")), Integer.parseInt(accessoriesGrup.get(grop.trim()).get("ЦЕНМАКС")));

        Map<String, Map<String, String>> shopRemanisSeles = new HashMap<>();
        for (String entry : nomeklature) {
            Map<String, String> shopRemanisSele = new TreeMap<>();
            rangeAccessoriesRepositoriy.findByTele2(entry);
            shopRemanisSele.put("ПРОД 6/3М", accessoriesSale6Repositoriy.getSale6AccesMax(entry));
            shopRemanisSele.put("ПРОД 1М", accessoriesSale1Repositoriy.getSale1AccesMax(entry));
            shopRemanisSele.put("ТЕЛЕ2", String.valueOf(rangeAccessoriesRepositoriy.findByTele2(entry)));
            shopRemanisSeles.put(entry, shopRemanisSele);
        }


        return shopRemanisSeles;
    }

    public Map<String, Map<String, Map<String, Map<String, String>>>> exselDistributionAccessories() {

        return modelShopSaleRem;
    }
}
