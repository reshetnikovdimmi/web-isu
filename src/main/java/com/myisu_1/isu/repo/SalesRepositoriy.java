package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.Sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface SalesRepositoriy extends JpaRepository<Sales, Integer> {
    @Modifying
    @Transactional
    @Query("update Sales u set u.nomenclature = ?1 where u.nomenclature = ?2 ")
    void updatModelSale(String Model, String models);
    @Modifying
    @Transactional
    @Query("update Sales u set u.shop = ?1 where u.shop = ?2 ")
    void updateNameShop(String namerainbow, String name);


    @Query("SELECT  MONTH(dateSales), COUNT(nomenclature) FROM Sales where dateSales > ?1 GROUP BY MONTH(dateSales)")
    List<Object> getMap(Date d);

    @Query("SELECT DISTINCT shop, MONTH(dateSales), COUNT(nomenclature) FROM Sales where dateSales > ?1 GROUP BY shop, MONTH(dateSales)")

    List<Object> getShopMap(Date yearStartDate);

    @Query("SELECT DISTINCT MONTH(dateSales) FROM Sales where dateSales > ?1  ORDER BY dateSales ASC")
    List<String> getDistinct(Date yearStartDate);
    @Query("SELECT new com.myisu_1.isu.models.Sales(imeis, shop, nomenclature, dateSales) FROM Sales where (?1 is null or dateSales >= ?1) AND (?2 is null or dateSales <= ?2) AND (?3 is null or shop = ?3)")
    List<Sales> getSaleAll(Date startDate, Date endDate, String shop);
}
