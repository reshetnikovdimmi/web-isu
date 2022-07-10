package com.myisu_1.isu.models;

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
   }
