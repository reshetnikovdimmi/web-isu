package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.accessories.AccessoriesSale6;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccessoriesSale6Repositoriy extends JpaRepository<AccessoriesSale6, Integer> {

    @Query("SELECT SUM(saleAccessories) FROM AccessoriesSale6 WHERE nameAccessories IN :strings")
    String getAccessoriesSale6(List<String> strings);


    @Query("SELECT SUM(saleAccessories) FROM AccessoriesSale6 WHERE nameShop = ?1 AND nameAccessories IN ?2")
    String getAccessoriesSale6Shop(String name, List<String> nomeklature);
}