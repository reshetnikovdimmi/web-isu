package com.myisu_1.isu.models.Marwel;

public class MarvelReporting {
    String name;
    String imei;
    String dataSale;
    String period;
    String promoCode;

    public MarvelReporting() {
    }

    public MarvelReporting(String name, String imei, String dataSale, String period, String promoCode) {
        this.name = name;
        this.imei = imei;
        this.dataSale = dataSale;
        this.period = period;
        this.promoCode = promoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDataSale() {
        return dataSale;
    }

    public void setDataSale(String dataSale) {
        this.dataSale = dataSale;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
}
