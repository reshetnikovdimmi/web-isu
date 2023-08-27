package com.myisu_1.isu.models.SIM;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ShopPlanSim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String shop;
    private String nameSimModem;
    private int plan;

    public ShopPlanSim(int id, String shop, String nameSimAndModem, int plan) {
        this.id = id;
        this.shop = shop;
        this.nameSimModem = nameSimAndModem;
        this.plan = plan;
    }

    public ShopPlanSim(String shop, String nameSimModem) {
        this.shop = shop;
        this.nameSimModem = nameSimModem;
    }

    public ShopPlanSim(String shop, String nameSimModem, int plan) {
        this.shop = shop;
        this.nameSimModem = nameSimModem;
        this.plan = plan;
    }

    public ShopPlanSim() {
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

    public String getNameSimModem() {
        return nameSimModem;
    }

    public void setNameSimModem(String nameSimModem) {
        this.nameSimModem = nameSimModem;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }
}
