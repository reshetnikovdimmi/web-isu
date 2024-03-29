package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.retail_price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepositoriy extends JpaRepository<retail_price, Integer> {

    @Query("SELECT DISTINCT priceInt  FROM retail_price WHERE name LIKE %?1%")
    Integer getPriceModelGB(String s);
}
