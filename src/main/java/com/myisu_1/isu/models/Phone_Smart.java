package com.myisu_1.isu.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Phone_Smart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Matrix_T2;
    private String Brend;
    private String Model;
    private String Model_GB;

    public Phone_Smart() {
    }

    public Phone_Smart(String brend) {
        Brend = brend;
    }

    public Phone_Smart(int id, String matrix_T2, String brend, String model, String model_GB) {
        this.id = id;
        Matrix_T2 = matrix_T2;
        Brend = brend;
        Model = model;
        Model_GB = model_GB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatrix_T2() {
        return Matrix_T2;
    }

    public void setMatrix_T2(String matrix_T2) {
        Matrix_T2 = matrix_T2;
    }

    public String getBrend() {
        return Brend;
    }

    public void setBrend(String brend) {
        Brend = brend;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getModel_GB() {
        return Model_GB;
    }

    public void setModel_GB(String model_GB) {
        Model_GB = model_GB;
    }
}