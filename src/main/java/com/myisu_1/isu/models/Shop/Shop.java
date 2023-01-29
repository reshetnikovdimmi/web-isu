package com.myisu_1.isu.models.Shop;

import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.authorization_tt;

import java.util.List;

public abstract class Shop {
    String nameShop;
    public List<authorization_tt> authorization_ttList;
    public List<Phone_Smart> phoneSmartList;










    public List<authorization_tt> getAuthorization_ttList() {
        return authorization_ttList;
    }

    public void setAuthorization_ttList(List<authorization_tt> authorization_ttList) {
        this.authorization_ttList = authorization_ttList;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }






}
