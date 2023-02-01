package com.myisu_1.isu.models.ClothesForPhones.Glass;

import com.myisu_1.isu.models.Shop.Shop;
import com.myisu_1.isu.models.authorization_tt;

import java.util.ArrayList;
import java.util.List;

public class ClothingMatching extends Shop {
    public List<String> brendDisting;
    public List<ClothingMatchingTable> clothingMatchingTableList;

    public void prints() {

    }

    public List<ClothingMatchingTable> saveCloting(List<ClothingMatchingTable> sim) {
        clothingMatchingTableList = new ArrayList<>();
        for (ClothingMatchingTable num : sim) {
           // System.out.println(num.getNameClothes()+"--"+num.getPhoneClothes()+"--"+num.getViewClothes());
            clothingMatchingTableList.add(new ClothingMatchingTable(num.getViewClothes(),num.getPhoneClothes(),num.getNameClothes()));
        }

        return clothingMatchingTableList;
    }
}