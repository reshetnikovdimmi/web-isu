package com.myisu_1.isu.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Combo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String imei;
    private String combo;
    private String  resume;
    private String reason;
    private String size;
    private double payment;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Combo(int id, Date date, String imei, String combo, String resume, String reason, String size, double payment) {
        this.id = id;
        this.date = date;
        this.imei = imei;
        this.combo = combo;
        this.resume = resume;
        this.reason = reason;
        this.size = size;
        this.payment = payment;
    }

    public Combo(Date date, String imei, String combo, String resume, String reason, String size, double payment) {
        this.date = date;
        this.imei = imei;
        this.combo = combo;
        this.resume = resume;
        this.reason = reason;
        this.size = size;
        this.payment = payment;
    }

    public Combo() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
