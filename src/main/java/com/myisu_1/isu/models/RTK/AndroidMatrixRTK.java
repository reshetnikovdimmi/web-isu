package com.myisu_1.isu.models.RTK;

public class AndroidMatrixRTK {
    String shop;
    String nomenclature;
    String remanis;
    String distributionModel;
    String sufficiency;
    String matrixRTK;

    public AndroidMatrixRTK() {
    }

    public AndroidMatrixRTK(String shop, String nomenclature, String remanis, String distributionModel, String sufficiency, String matrixRTK) {
        this.shop = shop;
        this.nomenclature = nomenclature;
        this.remanis = remanis;
        this.distributionModel = distributionModel;
        this.sufficiency = sufficiency;
        this.matrixRTK = matrixRTK;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getRemanis() {
        return remanis;
    }

    public void setRemanis(String remanis) {
        this.remanis = remanis;
    }

    public String getDistributionModel() {
        return distributionModel;
    }

    public void setDistributionModel(String distributionModel) {
        this.distributionModel = distributionModel;
    }

    public String getSufficiency() {
        return sufficiency;
    }

    public void setSufficiency(String sufficiency) {
        this.sufficiency = sufficiency;
    }

    public String getMatrixRTK() {
        return matrixRTK;
    }

    public void setMatrixRTK(String matrixRTK) {
        this.matrixRTK = matrixRTK;
    }
}
