package com.myisu_1.isu.models.ClothesForPhones.Glass;

import javax.persistence.*;

@Entity
public class ClothesForPhonesSale6 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nameShop;

    private String nameClothes;
    private int saleClothes;

    public ClothesForPhonesSale6(String nameShop, String nameClothes, int saleClothes) {
        this.nameShop = nameShop;
        this.nameClothes = nameClothes;
        this.saleClothes = saleClothes;
    }

    public ClothesForPhonesSale6() {
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
