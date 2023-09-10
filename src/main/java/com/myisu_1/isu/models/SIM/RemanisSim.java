package com.myisu_1.isu.models.SIM;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@ToString
@Entity
public class RemanisSim implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String shop;
    public String nameSimAndModem;
    public int remainsSimAndModem;

    public RemanisSim(int id, String shop) {
        this.id = id;
        this.shop = shop;
    }

    public RemanisSim(String nameSimAndModem, int remainsSimAndModem) {
        this.nameSimAndModem = nameSimAndModem;
        this.remainsSimAndModem = remainsSimAndModem;
    }

    public RemanisSim(int id, String shop, String nameSimAndModem, int remainsSimModem) {
        this.id = id;
        this.shop = shop;
        this.nameSimAndModem = nameSimAndModem;
        this.remainsSimAndModem = remainsSimModem;
    }

    public RemanisSim() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getNameSimAndModem() {
        return nameSimAndModem;
    }

    public void setNameSimAndModem(String nameSimAndModem) {
        this.nameSimAndModem = nameSimAndModem;
    }

    public int getRemainsSimModem() {
        return remainsSimAndModem;
    }

    public void setRemainsSimModem(int remainsSimModem) {
        this.remainsSimAndModem = remainsSimModem;
    }
}
