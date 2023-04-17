package com.myisu_1.isu.service;

import com.myisu_1.isu.models.accessories.SettingAccessories;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    public Map<String,Map<String,String>> AccessoriesCategory() {
        Map<String,Map<String,String>> accessoriesCategoryAll  = new TreeMap<>();

        accessoriesGrup = new TreeMap<>();
        List<SettingAccessories> settingAccessoriesList = settingAccessoriesRepositoriy.findAll();

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
        System.out.println(accessoriesGrup);
        System.out.println(grup.trim());
        System.out.println(accessoriesGrup.get(grup.trim()));
        Map<String,Map<String,String>> accessoriesCategoryAll  = new TreeMap<>();
List<authorization_tt> authorization_ttList = (List<authorization_tt>) authorization_ttRepositoriy.findAll();
        Map<String,String> indicators = new TreeMap<>();

        List<String> nomeklature = rangeAccessoriesRepositoriy.getButtonPhonePrice(accessoriesGrup.get(grup.trim()).get("ГРУППА"),Integer.parseInt(accessoriesGrup.get(grup.trim()).get("ЦЕНМИН")),Integer.parseInt(accessoriesGrup.get(grup.trim()).get("ЦЕНМАКС")));

        indicators.put("ОСТАТОК",remanisAccessoriesRepositoriy.getRemainsAccessories(nomeklature));
        indicators.put("ПРОД 1М",accessoriesSale1Repositoriy.getAccessoriesSale1(nomeklature));
        indicators.put("ПРОД 6/3М",accessoriesSale6Repositoriy.getAccessoriesSale6(nomeklature));
        indicators.put("Потребность","30");
        for (authorization_tt authorization : authorization_ttList) {
            accessoriesCategoryAll.put(authorization.getName(), indicators);

        }

        return accessoriesCategoryAll;
    }
}
