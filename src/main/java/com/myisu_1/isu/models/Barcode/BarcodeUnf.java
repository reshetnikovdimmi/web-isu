package com.myisu_1.isu.models.Barcode;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data

@Entity
public class BarcodeUnf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String barcode;
    private String nomenclature;
    @OneToOne(targetEntity = BarcodeSpark.class, cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "barcode", referencedColumnName = "barcode", insertable = false, updatable = false, foreignKey = @javax.persistence
            .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private BarcodeSpark barcodeSparks;
    public BarcodeUnf() {
    }

    public BarcodeUnf(int id, String barcode, String nomenclature) {
        this.id = id;
        this.barcode = barcode;
        this.nomenclature = nomenclature;
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

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }
}
