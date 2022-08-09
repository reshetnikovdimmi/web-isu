package com.myisu_1.isu.models.SIM;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SaleSim_1m {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String shop;
    private String nameSimAndModem;
    private int remainsSimModem;

    public SaleSim_1m(int id, String shop, String nameSimAndModem, int remainsSimModem) {
        this.id = id;
        this.shop = shop;
        this.nameSimAndModem = nameSimAndModem;
        this.remainsSimModem = remainsSimModem;
    }

    public SaleSim_1m() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getNameSimAndModem() {
        return nameSimAndModem;
    }

    public void setNameSimAndModem(String nameSimAndModem) {
        this.nameSimAndModem = nameSimAndModem;
    }

    public int getRemainsSimModem() {
        return remainsSimModem;
    }

    public void setRemainsSimModem(int remainsSimModem) {
        this.remainsSimModem = remainsSimModem;
    }
}
