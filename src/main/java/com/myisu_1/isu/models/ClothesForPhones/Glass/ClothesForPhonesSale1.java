package com.myisu_1.isu.models.ClothesForPhones.Glass;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ClothesForPhonesSale1 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nameShop;

    private String nameClothes;
    private int saleClothes;

    public ClothesForPhonesSale1() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public String getNameClothes() {
        return nameClothes;
    }

    public void setNameClothes(String nameClothes) {
        this.nameClothes = nameClothes;
    }

    public int getSaleClothes() {
        return saleClothes;
    }

    public void setSaleClothes(int saleClothes) {
        this.saleClothes = saleClothes;
    }
}
