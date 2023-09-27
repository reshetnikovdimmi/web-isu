package com.myisu_1.isu.models.RTK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MatrixRTK {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String cluster;
    private String distributionModel;
    private int quantity;


}
