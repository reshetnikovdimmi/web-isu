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
    private Long saleAll;
    private Long sale6;
    private Long sale1;
    private Integer matrix;

    public MatrixSpark() {
    }

    public MatrixSpark(String shop, String group, Long saleAll, Long sale6, Long sale1, Integer matrix) {
        this.shop = shop;
        this.group = group;
        this.saleAll = saleAll;
        this.sale6 = sale6;
        this.sale1 = sale1;
        this.matrix = matrix;
    }

    public MatrixSpark(String shop, String group, Integer matrix) {
        this.shop = shop;
        this.group = group;
        this.matrix = matrix;
    }

    public MatrixSpark(String shop, String group, Long saleAll) {
        this.shop = shop;
        this.group = group;
        this.saleAll = saleAll;
    }
}
