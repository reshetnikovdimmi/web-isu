package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.accessories.AccessoriesSale1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccessoriesSale1Repositoriy extends JpaRepository<AccessoriesSale1, Integer> {
    @Query("SELECT SUM(saleAccessories) FROM AccessoriesSale1 WHERE nameAccessories IN :strings")
    String getAccessoriesSale1 (List<String> strings);


    @Query("SELECT SUM(saleAccessories) FROM AccessoriesSale1 WHERE nameShop = ?1 AND nameAccessories IN ?2")
    String getAccessoriesSale1Shop(String name, List<String> nomeklature);
}
