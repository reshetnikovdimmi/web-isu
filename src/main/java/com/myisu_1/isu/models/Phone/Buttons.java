package com.myisu_1.isu.models.Phone;

import com.myisu_1.isu.models.Shop.Shop;

import java.io.Serializable;

public class Buttons extends Shop implements Serializable  {
    private int id;
    private String model;
    private String brend;
    private String price;

    public Buttons() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrend() {
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Buttons(int id, String model, String brend, String price) {
        this.id = id;
        this.model = model;
        this.brend = brend;
        this.price = price;
    }

    public Buttons(String model, String brend, String price) {
        this.model = model;
        this.brend = brend;
        this.price = price;
    }

}
