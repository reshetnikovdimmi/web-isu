package com.myisu_1.isu.models.SIM;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SimAndRtkTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String view;
    private String nameSpark;
    private String nameRarus;
    private String distributionModel;
    private String toOrder;


    public SimAndRtkTable(int id, String view, String nameSpark, String nameRarus, String distributionModel, String toOrder) {
        this.id = id;
        this.view = view;
        this.nameSpark = nameSpark;
        this.nameRarus = nameRarus;
        this.distributionModel = distributionModel;
        this.toOrder = toOrder;
    }

    public SimAndRtkTable(String view, String nameSpark, String nameRarus, String distributionModel, String toOrder) {
        this.view = view;
        this.nameSpark = nameSpark;
        this.nameRarus = nameRarus;
        this.distributionModel = distributionModel;
        this.toOrder = toOrder;
    }

    public SimAndRtkTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getNameSpark() {
        return nameSpark;
    }

    public void setNameSpark(String nameSpark) {
        this.nameSpark = nameSpark;
    }

    public String getNameRarus() {
        return nameRarus;
    }

    public void setNameRarus(String nameRarus) {
        this.nameRarus = nameRarus;
    }

    public String getDistributionModel() {
        return distributionModel;
    }

    public void setDistributionModel(String distributionModel) {
        this.distributionModel = distributionModel;
    }

    public String getToOrder() {
        return toOrder;
    }

    public void setToOrder(String toOrder) {
        this.toOrder = toOrder;
    }
}
