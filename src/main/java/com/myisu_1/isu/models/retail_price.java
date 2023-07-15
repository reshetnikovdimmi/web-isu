package com.myisu_1.isu.models;

import com.myisu_1.isu.models.Phone.ButtonsPhone;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class retail_price implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String price;
    int priceInt;

    public retail_price(String name, String price, int priceInt) {
        this.name = name;
        this.price = price;
        this.priceInt = priceInt;
    }

    public retail_price(String name, int priceInt) {
        this.name = name;
        this.priceInt = priceInt;
    }

    public int getPriceInt() {
        return priceInt;
    }

    public void setPriceInt(int priceInt) {
        this.priceInt = priceInt;
    }

    public retail_price() {
    }

    public retail_price(int id, String name, String prise) {
        this.id = id;
        this.name = name;
        this.price = prise;
    }

    public retail_price(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
