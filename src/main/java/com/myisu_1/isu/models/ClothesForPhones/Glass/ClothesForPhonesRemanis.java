package com.myisu_1.isu.models.ClothesForPhones.Glass;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@Data
@Entity
public class ClothesForPhonesRemanis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nameShop;
    private String namesClothes;
    private int remanisClothes;

    public ClothesForPhonesRemanis(String nameShop, String namesClothes, int remanisClothes) {
        this.nameShop = nameShop;
        this.namesClothes = namesClothes;
        this.remanisClothes = remanisClothes;
    }

    public ClothesForPhonesRemanis(int id, String nameShop, String namesClothes, int remanisClothes) {
        this.id = id;
        this.nameShop = nameShop;
        this.namesClothes = namesClothes;
        this.remanisClothes = remanisClothes;
    }

    public ClothesForPhonesRemanis() {
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

    public String getNamesClothes() {
        return namesClothes;
    }

    public void setNamesClothes(String namesClothes) {
        this.namesClothes = namesClothes;
    }

    public int getRemanisClothes() {
        return remanisClothes;
    }

    public void setRemanisClothes(int remanisClothes) {
        this.remanisClothes = remanisClothes;
    }
}
