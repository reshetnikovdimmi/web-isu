package com.myisu_1.isu.models.Phone;

public class DistributionPhone {
    String shop;
    String modelPhone;
    int skyPhone;
    int remanisPhone;
    boolean sky;

    public DistributionPhone(String shop, String modelPhone, int skyPhone, int remanisPhone, boolean sky) {
        this.shop = shop;
        this.modelPhone = modelPhone;
        this.skyPhone = skyPhone;
        this.remanisPhone = remanisPhone;
        this.sky = sky;
    }

    public DistributionPhone(String shop, String modelPhone, int remanisPhone, boolean sky) {
        this.shop = shop;
        this.modelPhone = modelPhone;

        this.remanisPhone = remanisPhone;
        this.sky = sky;
    }

    public DistributionPhone(String modelPhone, int skyPhone, int remanisPhone, boolean sky) {
        this.modelPhone = modelPhone;
        this.skyPhone = skyPhone;
        this.remanisPhone = remanisPhone;
        this.sky = sky;
    }

    public DistributionPhone() {
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

    public int getSkyPhone() {
        return skyPhone;
    }

    public void setSkyPhone(int skyPhone) {
        this.skyPhone = skyPhone;
    }

    public int getRemanisPhone() {
        return remanisPhone;
    }

    public void setRemanisPhone(int remanisPhone) {
        this.remanisPhone = remanisPhone;
    }

    public boolean isSky() {
        return sky;
    }

    public void setSky(boolean sky) {
        this.sky = sky;
    }
}
