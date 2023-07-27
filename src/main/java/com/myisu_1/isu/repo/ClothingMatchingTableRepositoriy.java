package com.myisu_1.isu.repo;


import com.myisu_1.isu.dto.RemainsGroupCash;
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

    @Query("SELECT  new com.myisu_1.isu.models.ClothesForPhones.Glass.Glass(c.phoneClothes, r.remanisClothes) FROM ClothingMatchingTable c " +
            "RIGHT JOIN c.clothersPhone r WHERE c.nameClothes = ?1 AND c.viewClothes = ?2 AND r.nameShop = ?3 ")
    List<Glass> getBrendShop(String brend, String view, String shop);

    @Query("SELECT phoneClothes FROM ClothingMatchingTable WHERE nameClothes = ?1 AND viewClothes = ?2")
    List<String> getClothingList(String brend, String view);

    @Query("SELECT DISTINCT new com.myisu_1.isu.models.ClothesForPhones.Glass.Glass(c.phoneClothes, r.remanisClothes) FROM ClothingMatchingTable c " +
            "RIGHT JOIN c.clothersPhone r WHERE c.viewClothes = ?1 AND r.nameShop = ?2 ")
    List<Glass>  getClothingAll(String view, String shop);

    @Query("SELECT DISTINCT nameClothes FROM ClothingMatchingTable WHERE phoneClothes IN :clothing")
    List<String> getBrendList(List<String> clothing);
    @Query("SELECT  new com.myisu_1.isu.dto.RemainsGroupCash(c.nameClothes,c.viewClothes, SUM (p.remanisClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersPhone p WHERE p.nameShop = ?1 GROUP BY c.nameClothes")
    List<RemainsGroupCash> getRemainsCash(String s);
}
