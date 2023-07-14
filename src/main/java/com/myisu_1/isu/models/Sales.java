package com.myisu_1.isu.models;


import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Sales implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String imeis;
    private String shop;
    private String nomenclature;
    @Temporal(TemporalType.DATE)
    Date dateSales;

    public Sales(String imeis, String nomenclature) {
        this.imeis = imeis;
        this.nomenclature = nomenclature;
    }

    public Sales(int id, String nomenclature) {
        this.id = id;
        this.nomenclature = nomenclature;
    }

    public Sales(String imeis, String shop, String nomenclature, Date dateSales) {
        this.imeis = imeis;
        this.shop = shop;
        this.nomenclature = nomenclature;
        this.dateSales = dateSales;
    }

    public Sales(int id, String imeis, String shop, String nomenclature, Date dateSales) {
        this.id = id;
        this.imeis = imeis;
        this.shop = shop;
        this.nomenclature = nomenclature;
        this.dateSales = dateSales;
    }


   public Sales() {
   }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImeis() {
        return imeis;
    }

    public void setImeis(String imeis) {
        this.imeis = imeis;
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

    public Date getDateSales() {
        return dateSales;
    }

    public void setDateSales(Date dateSales) {
        this.dateSales = dateSales;
    }

}
