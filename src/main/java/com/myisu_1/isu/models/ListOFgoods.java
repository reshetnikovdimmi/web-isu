package com.myisu_1.isu.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ListOFgoods implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Name;
    private String Brend;
    private String Model;
    private String Matrix;
    private int PriceZak;
    private int Price;
    private int PricePromo;
    @Temporal(TemporalType.DATE)
    private Date StartPromo;
    @Temporal(TemporalType.DATE)
    private Date EndPromo;
    private int DiscountUE;
    private int ValueVUE;

    public ListOFgoods(int id, String name, String brend, String model, String matrix, int priceZak, int price, int pricePromo, Date startPromo, Date endPromo, int discountUE, int valueVUE) {
        this.id = id;
        Name = name;
        Brend = brend;
        Model = model;
        Matrix = matrix;
        PriceZak = priceZak;
        Price = price;
        PricePromo = pricePromo;
        StartPromo = startPromo;
        EndPromo = endPromo;
        DiscountUE = discountUE;
        ValueVUE = valueVUE;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBrend() {
        return Brend;
    }

    public void setBrend(String brend) {
        Brend = brend;
    }

    public String getMatrix() {
        return Matrix;
    }

    public void setMatrix(String matrix) {
        Matrix = matrix;
    }

    public int getPriceZak() {
        return PriceZak;
    }

    public void setPriceZak(int priceZak) {
        PriceZak = priceZak;
    }

    public int getValueVUE() {
        return ValueVUE;
    }

    public void setValueVUE(int valueVUE) {
        ValueVUE = valueVUE;
    }

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
