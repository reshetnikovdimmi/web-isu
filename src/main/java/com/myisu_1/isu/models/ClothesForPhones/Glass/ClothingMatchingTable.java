package com.myisu_1.isu.models.ClothesForPhones.Glass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
public class ClothingMatchingTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String viewClothes;
    private String nameClothes;
    private String phoneClothes;

    public ClothingMatchingTable(String viewClothes, String nameClothes, String phoneClothes) {
        this.viewClothes = viewClothes;
        this.nameClothes = nameClothes;
        this.phoneClothes = phoneClothes;
    }

    public ClothingMatchingTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getViewClothes() {
        return viewClothes;
    }

    public void setViewClothes(String viewClothes) {
        this.viewClothes = viewClothes;
    }

    public String getNameClothes() {
        return nameClothes;
    }

    public void setNameClothes(String nameClothes) {
        this.nameClothes = nameClothes;
    }

    public void setPhoneClothes(String phoneClothes) {
        this.phoneClothes = phoneClothes;
    }
    public String getPhoneClothes() {
        return phoneClothes;
    }
}
