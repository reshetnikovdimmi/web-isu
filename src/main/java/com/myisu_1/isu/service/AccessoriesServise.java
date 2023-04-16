package com.myisu_1.isu.service;

import com.myisu_1.isu.models.accessories.SettingAccessories;
import com.myisu_1.isu.repo.ButtonsPhoneRepositoriy;
import com.myisu_1.isu.repo.RangeAccessoriesRepositoriy;
import com.myisu_1.isu.repo.SettingAccessoriesRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class AccessoriesServise {
    @Autowired
    private SettingAccessoriesRepositoriy settingAccessoriesRepositoriy;
    @Autowired
    private RangeAccessoriesRepositoriy rangeAccessoriesRepositoriy;
    public Map<String,Map<String,String>> AccessoriesCategory() {
        Map<String,Map<String,String>> accessoriesCategoryAll  = new TreeMap<>();


        List<SettingAccessories> settingAccessoriesList = settingAccessoriesRepositoriy.findAll();
        for (SettingAccessories accessoriesCategory : settingAccessoriesList) {

            accessoriesCategoryAll.put(
                    accessoriesCategory.getGroup()+"-"+
                    accessoriesCategory.getPriceMin()+"-"+
                    accessoriesCategory.getPriceMax(),
                    indicators());
        }

        return accessoriesCategoryAll;
    }

    private Map<String, String> indicators() {
        Map<String,String> indicators = new TreeMap<>();
        System.out.println(rangeAccessoriesRepositoriy.getButtonPhonePrice("AUX I-коннектор",100));
        indicators.put("ОСТАТОК","10");
        indicators.put("ПРОД 1М","20");
        indicators.put("ПРОД 6/3М","30");
        indicators.put("Потребность","30");
       return  indicators;
    }
}
