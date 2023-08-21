package com.myisu_1.isu.models.Barcode;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class BarcodeSpark {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;
        public String barcode;
        public String nomenclature;
     }
