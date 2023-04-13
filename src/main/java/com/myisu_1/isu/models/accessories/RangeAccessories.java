package com.myisu_1.isu.models.accessories;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@ToString
@Entity
public class RangeAccessories implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String accessoriesName;
    private String accessoriesCategory;
    boolean tele2;

    public RangeAccessories() {
    }

    public RangeAccessories(int id, String accessoriesName, String accessoriesCategory, boolean tele2) {
        this.id = id;
        this.accessoriesName = accessoriesName;
        this.accessoriesCategory = accessoriesCategory;
        this.tele2 = tele2;
    }

    public RangeAccessories(String accessoriesName, String accessoriesCategory, boolean tele2) {
        this.accessoriesName = accessoriesName;
        this.accessoriesCategory = accessoriesCategory;
        this.tele2 = tele2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessoriesName() {
        return accessoriesName;
    }

    public void setAccessoriesName(String accessoriesName) {
        this.accessoriesName = accessoriesName;
    }

    public String getAccessoriesCategory() {
        return accessoriesCategory;
    }

    public void setAccessoriesCategory(String accessoriesCategory) {
        this.accessoriesCategory = accessoriesCategory;
    }

    public boolean isTele2() {
        return tele2;
    }

    public void setTele2(boolean tele2) {
        this.tele2 = tele2;
    }
}
