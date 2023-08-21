package com.myisu_1.isu.models.Barcode;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class BarcodeSpark implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;
        public String barcode;
        public String nomenclature;
     }
