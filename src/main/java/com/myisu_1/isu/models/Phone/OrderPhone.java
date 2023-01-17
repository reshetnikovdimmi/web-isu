package com.myisu_1.isu.models.Phone;

import java.util.HashMap;

public class OrderPhone {
    private String distributionModel;
    private String quantity;
    HashMap<String,String[]> salePhone;

    public OrderPhone(String distributionModel, String quantity, HashMap<String, String[]> salePhone) {
        this.distributionModel = distributionModel;
        this.quantity = quantity;
        this.salePhone = salePhone;
    }

    public OrderPhone() {
    }



    public HashMap<String, String[]> getSalePhone() {
        return salePhone;
    }

    public void setSalePhone(HashMap<String, String[]> salePhone) {
        this.salePhone = salePhone;
    }



    public String getDistributionModel() {
        return distributionModel;
    }

    public void setDistributionModel(String distributionModel) {
        this.distributionModel = distributionModel;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
