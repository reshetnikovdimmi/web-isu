package com.myisu_1.isu.models.accessories;




import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SettingAccessories implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String group;
    private int priceMin;
    private int priceMax;

    public SettingAccessories() {
    }

    public SettingAccessories(int id,  String group, int priceMin, int priceMax) {
        this.id = id;
        this.group = group;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    public SettingAccessories(String group, int priceMin, int priceMax) {
        this.group = group;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(int priceMin) {
        this.priceMin = priceMin;
    }

    public int getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(int priceMax) {
        this.priceMax = priceMax;
    }
}
