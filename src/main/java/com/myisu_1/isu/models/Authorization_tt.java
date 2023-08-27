package com.myisu_1.isu.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authorization_tt {
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
    private String shopIskra;
    private String shopRarus;

    public Authorization_tt(int id, String login, String name, String clusterT2, String clusterRtk, String simT2, String simMts, String simBee, String simMf, String shopIskra, String shopRarus) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.clusterT2 = clusterT2;
        this.clusterRtk = clusterRtk;
        this.simT2 = simT2;
        this.simMts = simMts;
        this.simBee = simBee;
        this.simMf = simMf;
        this.shopIskra = shopIskra;
        this.shopRarus = shopRarus;
    }

    public Authorization_tt(String login, String pasword, String name, String clusterT2, String clusterRtk, String simT2, String simMts, String simBee, String simMf, String shopIskra, String shopRarus) {
        this.login = login;
        this.pasword = pasword;
        this.name = name;
        this.clusterT2 = clusterT2;
        this.clusterRtk = clusterRtk;
        this.simT2 = simT2;
        this.simMts = simMts;
        this.simBee = simBee;
        this.simMf = simMf;
        this.shopIskra = shopIskra;
        this.shopRarus = shopRarus;
    }

    public Authorization_tt() {
    }

    public Authorization_tt(int id, String login, String pasword, String name, String clusterT2, String clusterRtk, String simT2, String simMts, String simBee, String simMf, String shopIskra, String shopRarus) {
        this.id = id;
        this.login = login;
        this.pasword = pasword;
        this.name = name;
        this.clusterT2 = clusterT2;
        this.clusterRtk = clusterRtk;
        this.simT2 = simT2;
        this.simMts = simMts;
        this.simBee = simBee;
        this.simMf = simMf;
        this.shopIskra = shopIskra;
        this.shopRarus = shopRarus;
    }

    public String getShopIskra() {
        return shopIskra;
    }

    public void setShopIskra(String shopIskra) {
        this.shopIskra = shopIskra;
    }

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
    public String getShopRarus() {
        return shopRarus;
    }

    public void setShopRarus(String shopRarus) {
        this.shopRarus = shopRarus;
    }
}
