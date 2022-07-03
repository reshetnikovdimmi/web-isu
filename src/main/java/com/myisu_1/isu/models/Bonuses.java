package com.myisu_1.isu.models;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class Bonuses {

    String models;
    String imei;
    String dataSale;
    String dataPeriod;
    Double compensation;

    public Bonuses(String models, String imei, String dataSale, String dataPeriod, Double compensation) {
        this.models = models;
        this.imei = imei;
        this.dataSale = dataSale;
        this.dataPeriod = dataPeriod;
        this.compensation = compensation;
    }

    public Bonuses() {
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
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

    public String getDataPeriod() {
        return dataPeriod;
    }

    public void setDataPeriod(String dataPeriod) {
        this.dataPeriod = dataPeriod;
    }

    public Double getCompensation() {
        return compensation;
    }

    public void setCompensation(Double compensation) {
        this.compensation = compensation;
    }
}
