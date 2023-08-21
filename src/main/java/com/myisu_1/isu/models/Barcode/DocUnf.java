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
    public String nomenclatures;
    public Double quantity;
    public Double price;

    @ManyToMany(targetEntity = BarcodeUnf.class, cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "nomenclatures", referencedColumnName = "nomenclature", insertable = false, updatable = false)
    private List<BarcodeUnf> barcodeUnfs;

}
