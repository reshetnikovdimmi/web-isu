package com.myisu_1.isu.models.Phone;

import com.myisu_1.isu.models.Shop.Shop;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Buttons extends Shop implements Serializable  {
    private int id;
    private String model;
    private String brend;
    private String price;
    Map<String, Map<String, Map<String, Map<String, String>>>> modelShopSaleRem = new TreeMap<>();
    Map<String, Map<String, Map<String, String>>> modelShopSale = new TreeMap<>();
    Map<String, Map<String, String>> modelShop;

    public Map<String, Map<String, Map<String, Map<String, String>>>> getModelShopSaleRem() {
        return modelShopSaleRem;
    }

    public void setModelShopSaleRem(Map<String, Map<String, Map<String, Map<String, String>>>> modelShopSaleRem) {
        this.modelShopSaleRem = modelShopSaleRem;
    }

    public Map<String, Map<String, Map<String, String>>> getModelShopSale() {
        return modelShopSale;
    }

    public void setModelShopSale(Map<String, Map<String, Map<String, String>>> modelShopSale) {
        this.modelShopSale = modelShopSale;
    }

    public Map<String, Map<String, String>> getModelShop() {
        return modelShop;
    }

    public void setModelShop(Map<String, Map<String, String>> modelShop) {
        this.modelShop = modelShop;
    }

    public Buttons() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrend() {
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Buttons(int id, String model, String brend, String price) {
        this.id = id;
        this.model = model;
        this.brend = brend;
        this.price = price;
    }

    public Buttons(String model, String brend, String price) {
        this.model = model;
        this.brend = brend;
        this.price = price;
    }

}
