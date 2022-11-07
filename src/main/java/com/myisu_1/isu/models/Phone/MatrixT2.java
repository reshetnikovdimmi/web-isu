package com.myisu_1.isu.models.Phone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MatrixT2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String distributionModel;
    private String cluster;
    private String quantity;

    public MatrixT2() {
    }

    public MatrixT2(int id, String distributionModel, String cluster, String quantity) {
        this.id = id;
        this.distributionModel = distributionModel;
        this.cluster = cluster;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistributionModel() {
        return distributionModel;
    }

    public void setDistributionModel(String distributionModel) {
        this.distributionModel = distributionModel;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
