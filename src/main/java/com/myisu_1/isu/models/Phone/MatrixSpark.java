package com.myisu_1.isu.models.Phone;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Getter
@Setter
@Entity
public class MatrixSpark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String shop;
    private String group;
    private Integer saleAll;
    private Integer sale6;
    private Integer sale1;
    private Integer matrix;

    public MatrixSpark() {
    }

}
