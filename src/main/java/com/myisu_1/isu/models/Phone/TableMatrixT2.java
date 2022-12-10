package com.myisu_1.isu.models.Phone;

public class TableMatrixT2 {

    private String shop;
    private String distributionModel;
    private String quantity;

    public TableMatrixT2(String shop, String distributionModel, String quantity) {
        this.shop = shop;
        this.distributionModel = distributionModel;
        this.quantity = quantity;
    }

    public TableMatrixT2() {
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
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
