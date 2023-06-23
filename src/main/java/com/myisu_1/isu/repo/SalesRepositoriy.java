package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.Sales;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SalesRepositoriy extends CrudRepository<Sales, Integer> {
    @Modifying
    @Transactional
    @Query("update Sales u set u.nomenclature = ?1 where u.nomenclature = ?2 ")
    void updatModelSale(String Model, String models);
    @Modifying
    @Transactional
    @Query("update Sales u set u.shop = ?1 where u.shop = ?2 ")
    void updateNameShop(String namerainbow, String name);
}
