package com.myisu_1.isu.models.Marwel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class MarvelClassifier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String RainbowNomenclature;
    private String ManufacturersArticle;
    private String Name;

    public MarvelClassifier() {
    }

    public MarvelClassifier(int id, String rainbowNomenclature, String manufacturersArticle, String name) {
        this.id = id;
        RainbowNomenclature = rainbowNomenclature;
        ManufacturersArticle = manufacturersArticle;
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRainbowNomenclature() {
        return RainbowNomenclature;
    }

    public void setRainbowNomenclature(String rainbowNomenclature) {
        RainbowNomenclature = rainbowNomenclature;
    }

    public String getManufacturersArticle() {
        return ManufacturersArticle;
    }

    public void setManufacturersArticle(String manufacturersArticle) {
        ManufacturersArticle = manufacturersArticle;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
