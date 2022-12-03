package com.myisu_1.isu.models.Phone;

public class Assortment {
    String shop;
    String modelPhone;
    int quantity;

    public Assortment() {
    }

    public Assortment(String shop, String modelPhone, int quantity) {
        this.shop = shop;
        this.modelPhone = modelPhone;
        this.quantity = quantity;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getModelPhone() {
        return modelPhone;
    }

    public void setModelPhone(String modelPhone) {
        this.modelPhone = modelPhone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
