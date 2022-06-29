package com.myisu_1.isu.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Suppliers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String imei;
    private String suppliers;

    public Suppliers(String imei, String suppliers) {
        this.imei = imei;
        this.suppliers = suppliers;
    }

    public Suppliers(int id, String imei, String suppliers) {
        this.id = id;
        this.imei = imei;
        this.suppliers = suppliers;
    }

    public Suppliers() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(String suppliers) {
        this.suppliers = suppliers;
    }
}
