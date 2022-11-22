package com.myisu_1.isu.models.Phone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MatrixSpark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String model;
    private String sale;

    public MatrixSpark() {
    }

    public MatrixSpark(String model, String sale) {
        this.model = model;
        this.sale = sale;
    }

    public MatrixSpark(int id, String model, String sale) {
        this.id = id;
        this.model = model;
        this.sale = sale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
