package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClothingMatchingTableRepositoriy extends JpaRepository<ClothingMatchingTable, Integer>{


    @Query("SELECT DISTINCT  new com.myisu_1.isu.models.ClothesForPhones.Glass.Glass(c.nameClothes,SUM (p.saleClothes)) FROM ClothingMatchingTable c " +
            "JOIN c.clothersSale6 p WHERE c.viewClothes = ?1 GROUP BY c.nameClothes")
    List<Glass> getSal6Clothes(String vtew);

    @Query("SELECT DISTINCT  new com.myisu_1.isu.models.ClothesForPhones.Glass.Glass(c.nameClothes,SUM (p.saleClothes)) FROM ClothingMatchingTable c " +
            "JOIN c.clothersSale1 p WHERE c.viewClothes = ?1 GROUP BY c.nameClothes")
    List<Glass> getSal1Clothes(String vtew);

    @Query("SELECT DISTINCT  new com.myisu_1.isu.models.ClothesForPhones.Glass.Glass(c.nameClothes,SUM (r.remanisClothes)) FROM ClothingMatchingTable c " +
            "JOIN c.clothersPhone r WHERE c.viewClothes = ?1 GROUP BY c.nameClothes")
    List<Glass> getRemanisClothes(String vtew);

    @Query("SELECT  new com.myisu_1.isu.models.ClothesForPhones.Glass.Glass(r.nameShop,SUM (r.remanisClothes)) FROM ClothingMatchingTable c " +
            "JOIN c.clothersPhone r WHERE c.nameClothes = ?1 AND c.viewClothes = ?2  GROUP BY r.nameShop")
    List<Glass> tableShopRemanis(String s1, String s);

}
