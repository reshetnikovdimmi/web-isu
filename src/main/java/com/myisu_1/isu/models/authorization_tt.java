package com.myisu_1.isu.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class authorization_tt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String pasword;
    private String name;
    private String clusterT2;
    private String clusterRtk;
    private String simT2;
    private String simMts;
    private String simBee;
    private String simMf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClusterT2() {
        return clusterT2;
    }

    public void setClusterT2(String clusterT2) {
        this.clusterT2 = clusterT2;
    }

    public String getClusterRtk() {
        return clusterRtk;
    }

    public void setClusterRtk(String clusterRtk) {
        this.clusterRtk = clusterRtk;
    }

    public String getSimT2() {
        return simT2;
    }

    public void setSimT2(String simT2) {
        this.simT2 = simT2;
    }

    public String getSimMts() {
        return simMts;
    }

    public void setSimMts(String simMts) {
        this.simMts = simMts;
    }

    public String getSimBee() {
        return simBee;
    }

    public void setSimBee(String simBee) {
        this.simBee = simBee;
    }

    public String getSimMf() {
        return simMf;
    }

    public void setSimMf(String simMf) {
        this.simMf = simMf;
    }
}
