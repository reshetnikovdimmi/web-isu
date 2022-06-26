package com.myisu_1.isu.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ListOFgoods implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Model;
    private int Price;
    private int PricePromo;
    @Temporal(TemporalType.DATE)
    private Date StartPromo;
    @Temporal(TemporalType.DATE)
    private Date EndPromo;
    private int DiscountUE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getPricePromo() {
        return PricePromo;
    }

    public void setPricePromo(int pricePromo) {
        PricePromo = pricePromo;
    }

    public Date getStartPromo() {
        return StartPromo;
    }

    public void setStartPromo(Date startPromo) {
        StartPromo = startPromo;
    }

    public Date getEndPromo() {
        return EndPromo;
    }

    public void setEndPromo(Date endPromo) {
        EndPromo = endPromo;
    }

    public int getDiscountUE() {
        return DiscountUE;
    }

    public void setDiscountUE(int discountUE) {
        DiscountUE = discountUE;
    }

    public ListOFgoods() {
    }

    public ListOFgoods(int id, String model, int price, int pricePromo, Date startPromo, Date endPromo, int discountUE) {
        this.id = id;
        Model = model;
        Price = price;
        PricePromo = pricePromo;
        StartPromo = startPromo;
        EndPromo = endPromo;
        DiscountUE = discountUE;
    }
}
