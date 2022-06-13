package com.myisu_1.isu.models;

import javax.persistence.*;


@Entity
public class price_promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;
    String brend;
    String models;
    String price;
    String price_promo;
    String start_promo;
    String end_promo;
    String marwel;

    public price_promo(String brend, String models, String price, String price_promo, String start_promo, String end_promo, String marwel, String tfn, String vvp, String merlion) {
        this.brend = brend;
        this.models = models;
        this.price = price;
        this.price_promo = price_promo;
        this.start_promo = start_promo;
        this.end_promo = end_promo;
        this.marwel = marwel;
        this.tfn = tfn;
        this.vvp = vvp;
        this.merlion = merlion;
    }

    String tfn;
    String vvp;
    String merlion;

    public price_promo() {
    }

    public price_promo(int id, String brend, String models, String price, String price_promo, String start_promo, String end_promo, String marwel, String tfn, String vvp, String merlion) {
        this.id = id;
        this.brend = brend;
        this.models = models;
        this.price = price;
        this.price_promo = price_promo;
        this.start_promo = start_promo;
        this.end_promo = end_promo;
        this.marwel = marwel;
        this.tfn = tfn;
        this.vvp = vvp;
        this.merlion = merlion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrend() {
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_promo() {
        return price_promo;
    }

    public void setPrice_promo(String price_promo) {
        this.price_promo = price_promo;
    }

    public String getStart_promo() {
        return start_promo;
    }

    public void setStart_promo(String start_promo) {
        this.start_promo = start_promo;
    }

    public String getEnd_promo() {
        return end_promo;
    }

    public void setEnd_promo(String end_promo) {
        this.end_promo = end_promo;
    }

    public String getMarwel() {
        return marwel;
    }

    public void setMarwel(String marwel) {
        this.marwel = marwel;
    }

    public String getTfn() {
        return tfn;
    }

    public void setTfn(String tfn) {
        this.tfn = tfn;
    }

    public String getVvp() {
        return vvp;
    }

    public void setVvp(String vvp) {
        this.vvp = vvp;
    }

    public String getMerlion() {
        return merlion;
    }

    public void setMerlion(String merlion) {
        this.merlion = merlion;
    }
}
