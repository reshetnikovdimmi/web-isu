package com.myisu_1.isu.models.accessories;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@ToString
@Entity
public class AccessoriesSale1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nameShop;
    private String nameAccessories;
    private int saleAccessories;
}
