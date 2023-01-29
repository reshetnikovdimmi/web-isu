package com.myisu_1.isu.models.ClothesForPhones.Glass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
public class ClothesForPhonesRemanis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nameClothes;
    private String remanisClothes;

    public ClothesForPhonesRemanis(int id, String nameClothes, String remanisClothes) {
        this.id = id;
        this.nameClothes = nameClothes;
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

    public String getNameClothes() {
        return nameClothes;
    }

    public void setNameClothes(String nameClothes) {
        this.nameClothes = nameClothes;
    }

    public String getRemanisClothes() {
        return remanisClothes;
    }

    public void setRemanisClothes(String remanisClothes) {
        this.remanisClothes = remanisClothes;
    }
}
