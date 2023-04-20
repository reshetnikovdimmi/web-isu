package com.myisu_1.isu.service;

import com.myisu_1.isu.models.accessories.SettingAccessories;
import com.myisu_1.isu.models.authorization_tt;
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
    Map<String,Map<String,String>> accessoriesGrup;
    String grups;
    public Map<String,Map<String,String>> AccessoriesCategory() {
        Map<String,Map<String,String>> accessoriesCategoryAll  = new TreeMap<>();

        accessoriesGrup = new TreeMap<>();
        List<SettingAccessories> settingAccessoriesList = (List<SettingAccessories>) settingAccessoriesRepositoriy.findAll();

        for (SettingAccessories accessoriesCategory : settingAccessoriesList) {
            Map<String,String> indicatorsGrup = new TreeMap<>();

            indicatorsGrup.put("ГРУППА",accessoriesCategory.getGroup());
            indicatorsGrup.put("ЦЕНМИН", String.valueOf(accessoriesCategory.getPriceMin()));
            indicatorsGrup.put("ЦЕНМАКС", String.valueOf(accessoriesCategory.getPriceMax()));

            accessoriesGrup.put(accessoriesCategory.getGroup()+" "+
                    accessoriesCategory.getPriceMin()+"-"+
                    accessoriesCategory.getPriceMax(),indicatorsGrup);

            accessoriesCategoryAll.put(
                    accessoriesCategory.getGroup()+" "+
                    accessoriesCategory.getPriceMin()+"-"+
                    accessoriesCategory.getPriceMax(),
                    indicators(accessoriesCategory.getGroup(),accessoriesCategory.getPriceMin(),accessoriesCategory.getPriceMax()));
        }

        return accessoriesCategoryAll;

    }

    private Map<String, String> indicators(String group, int priceMin, int priceMax) {
        Map<String,String> indicators = new TreeMap<>();

        List<String> nomeklature = rangeAccessoriesRepositoriy.getButtonPhonePrice(group,priceMin,priceMax);

        indicators.put("ОСТАТОК",remanisAccessoriesRepositoriy.getRemainsAccessories(nomeklature));
        indicators.put("ПРОД 1М",accessoriesSale1Repositoriy.getAccessoriesSale1(nomeklature));
        indicators.put("ПРОД 6/3М",accessoriesSale6Repositoriy.getAccessoriesSale6(nomeklature));
        indicators.put("Потребность","30");
       return  indicators;
    }

    public Map<String,Map<String,String>> accessoriesCategoryShop(String grup) {
        grups = grup;
        long start = System.currentTimeMillis();
        Map<String,Map<String,String>> accessoriesCategoryAll  = new TreeMap<>();
List<authorization_tt> authorization_ttList = (List<authorization_tt>) authorization_ttRepositoriy.findAll();
        Map<String,String> indicators;

        List<String> nomeklature = rangeAccessoriesRepositoriy.getButtonPhonePrice(accessoriesGrup.get(grup.trim()).get("ГРУППА"),Integer.parseInt(accessoriesGrup.get(grup.trim()).get("ЦЕНМИН")),Integer.parseInt(accessoriesGrup.get(grup.trim()).get("ЦЕНМАКС")));


        for (authorization_tt authorization : authorization_ttList) {
            indicators = new TreeMap<>();

            indicators.put("ОСТАТОК",remanisAccessoriesRepositoriy.getRemainsAccessoriesShop(authorization.getName(), nomeklature));
            indicators.put("ПРОД 1М",accessoriesSale1Repositoriy.getAccessoriesSale1Shop(authorization.getName(), nomeklature));
            indicators.put("ПРОД 6/3М",accessoriesSale6Repositoriy.getAccessoriesSale6Shop(authorization.getName(), nomeklature));
            indicators.put("Потребность","30");
            accessoriesCategoryAll.put(authorization.getName(), indicators);

        }
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
           System.out.println(df.format(new Date(timeWorkCode)));
        return accessoriesCategoryAll;
    }

    public Map<String, Map<String, Map<String, String>>> accessoriesCategoryNomenclatureShop(String shop) {
        Map<String, String> shopRemanisSele = new TreeMap<>();
        Map<String, Map<String, String>> shopRemanisSeles = new TreeMap<>();
        Map<String, Map<String, Map<String, String>>> shopRemanisSeless = new TreeMap<>();
        Map<String, Map<String, Map<String, Map<String, String>>>> modelShopSaleRem = new TreeMap<>();

        shopRemanisSele.put("ОСТАТОК","iuhih");
        shopRemanisSele.put("ПРОД 1М","iuhih");
        shopRemanisSele.put("ПРОД 6/3М","iuhih");

        shopRemanisSeles.put("avAVAVS",shopRemanisSele);
        shopRemanisSeles.put("avAVSVSAVS",shopRemanisSele);
        shopRemanisSeles.put("avAVSVXBSDSAVS",shopRemanisSele);

        shopRemanisSeless.put("SDBSDBDFB",shopRemanisSeles);
        shopRemanisSeless.put("SDBSDBDSBSDFB",shopRemanisSeles);
        shopRemanisSeless.put("SDBSBSBSDBDFB",shopRemanisSeles);

        modelShopSaleRem.put("DBDFD",shopRemanisSeless);
        modelShopSaleRem.put("DSVBSBDFD",shopRemanisSeless);
        modelShopSaleRem.put("DBSBDDFD",shopRemanisSeless);

        return modelShopSaleRem.get("DSVBSBDFD"/*shop*/);
    }
}
