package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import com.myisu_1.isu.models.Phone_Smart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhoneRepositoriy extends JpaRepository<Phone_Smart, Integer> {
    @Query("SELECT DISTINCT Brend FROM Phone_Smart")
    List<String> getBrendDisting();

  @Query("SELECT  new com.myisu_1.isu.models.ClothesForPhones.Glass.Glass(p.Brend, SUM (r.remainsSimAndModem)) FROM Phone_Smart p " +
          "JOIN p.remanisSims r GROUP BY p.Brend")
   List<Glass> getBrendRemanis();

    @Query("SELECT  new com.myisu_1.isu.models.ClothesForPhones.Glass.Glass(r.shop, SUM (r.remainsSimAndModem)) FROM Phone_Smart p " +
            "JOIN p.remanisSims r WHERE p.Brend = ?1 GROUP BY r.shop")

    List<Glass> getBrendShopRemanis(String s);

}
