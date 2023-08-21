package com.myisu_1.isu.models.Barcode;

import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothesForPhonesRemanis;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class DocUnf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    public String barcode;
    public String nomenclature;
    public Double quantity;
    public Double price;

    @OneToMany(targetEntity = BarcodeUnf.class, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "nomenclature", referencedColumnName = "nomenclature", insertable = false, updatable = false)
    private List<BarcodeUnf> barcodeUnfs;

}
