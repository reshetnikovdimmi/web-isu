package com.myisu_1.isu.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ValueVUE implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomenclature;
    private String imei;
    private String quality;
    @Temporal(TemporalType.DATE)
    private Date dateOFsale;
    private String shop;
    private int valueVUE;

    public ValueVUE(int id, String nomenclature, String imei, String quality, Date dateOFsale, String shop, int valueVUE) {
        this.id = id;
        this.nomenclature = nomenclature;
        this.imei = imei;
        this.quality = quality;
        this.dateOFsale = dateOFsale;
        this.shop = shop;
        this.valueVUE = valueVUE;
    }

    public ValueVUE() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Date getDateOFsale() {
        return dateOFsale;
    }

    public void setDateOFsale(Date dateOFsale) {
        this.dateOFsale = dateOFsale;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public int getValueVUE() {
        return valueVUE;
    }

    public void setValueVUE(int valueVUE) {
        this.valueVUE = valueVUE;
    }
}
