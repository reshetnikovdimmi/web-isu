package com.myisu_1.isu.models.Barcode;

import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothesForPhonesRemanis;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class DocUnf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String barcode;
    private String nomenclatures;
    private Double quantity;
    private Double price;

    @OneToMany(targetEntity = BarcodeUnf.class, cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "nomenclature", referencedColumnName = "nomenclatures", insertable = false, updatable = false)
    private List<BarcodeUnf> barcodeUnfs;

    public DocUnf(int id, String barcode, String nomenclatures, Double quantity, Double price, List<BarcodeUnf> barcodeUnfs) {
        this.id = id;
        this.barcode = barcode;
        this.nomenclatures = nomenclatures;
        this.quantity = quantity;
        this.price = price;
        this.barcodeUnfs = barcodeUnfs;
    }

    public DocUnf(int id, String barcode, String nomenclatures, Double price) {
        this.id = id;
        this.barcode = barcode;
        this.nomenclatures = nomenclatures;
        this.price = price;
    }

    public DocUnf(String barcode, String nomenclatures, Double quantity, Double price) {
        this.barcode = barcode;
        this.nomenclatures = nomenclatures;
        this.quantity = quantity;
        this.price = price;
    }

    public DocUnf() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNomenclatures() {
        return nomenclatures;
    }

    public void setNomenclatures(String nomenclatures) {
        this.nomenclatures = nomenclatures;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<BarcodeUnf> getBarcodeUnfs() {
        return barcodeUnfs;
    }

    public void setBarcodeUnfs(List<BarcodeUnf> barcodeUnfs) {
        this.barcodeUnfs = barcodeUnfs;
    }
}
