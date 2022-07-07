package com.myisu_1.isu.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class TradeIN implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date receiptDate;
    private String Nomenclature;
    private String IMEI;
    private int productPrice;
    private int discount;
    private int amount;

    public TradeIN(int id, Date receiptDate, String nomenclature, String IMEI, int productPrice, int discount, int amount) {
        this.id = id;
        this.receiptDate = receiptDate;
        Nomenclature = nomenclature;
        this.IMEI = IMEI;
        this.productPrice = productPrice;
        this.discount = discount;
        this.amount = amount;
    }

    public TradeIN() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getNomenclature() {
        return Nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        Nomenclature = nomenclature;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
