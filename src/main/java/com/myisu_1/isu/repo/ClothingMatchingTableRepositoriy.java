package com.myisu_1.isu.repo;


import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClothingMatchingTableRepositoriy extends JpaRepository<ClothingMatchingTable, Integer>{




    @Query("SELECT phoneClothes FROM ClothingMatchingTable WHERE nameClothes = ?1 AND viewClothes = ?2")
    List<String> getClothingList(String brend, String view);


    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(c.nameClothes,c.viewClothes, SUM (p.remanisClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersPhone p   GROUP BY c.nameClothes, c.viewClothes ORDER BY p.remanisClothes ASC")
    List<OrderRecommendations> getRemainsShopPhoneAll();

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations( c.nameClothes,c.viewClothes, SUM (p.saleClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale1 p   GROUP BY c.nameClothes, c.viewClothes ")
    List<OrderRecommendations> getSale1ShopPhoneAll();
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations( c.nameClothes,c.viewClothes, SUM (p.saleClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale6 p   GROUP BY c.nameClothes, c.viewClothes ")
    List<OrderRecommendations> getSale6ShopPhoneAll();


    @Modifying
    @Transactional
    @Query("update ClothingMatchingTable u set u.nameClothes = ?1 where u.nameClothes = ?2")
    void updateBrend(String brend, String brend1);

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop, c.nameClothes,c.viewClothes, SUM (p.remanisClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersPhone p   GROUP BY c.nameClothes, c.viewClothes,p.nameShop")
    List<OrderRecommendations> getRemainsShopClothingGroup();
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop,c.nameClothes,c.viewClothes, SUM (p.saleClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale1 p   GROUP BY c.nameClothes, c.viewClothes,p.nameShop")
        List<OrderRecommendations> getSale1ShopClothingGroup();
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop,c.nameClothes,c.viewClothes, SUM (p.saleClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale6 p   GROUP BY c.nameClothes, c.viewClothes,p.nameShop")
    List<OrderRecommendations> getSale6ShopClothingGroup();

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop,c.nameClothes, p.namesClothes,c.viewClothes, p.remanisClothes) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersPhone p")
    List<OrderRecommendations> getRemainsShopClothingGroupAll();
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop,c.nameClothes, p.nameClothes,c.viewClothes, p.saleClothes) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale1 p")
    List<OrderRecommendations> getSale1ShopClothingGroupAll();
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop,c.nameClothes, p.nameClothes,c.viewClothes, p.saleClothes) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale6 p")
    List<OrderRecommendations> getSale6ShopClothingGroupAll();

    @Query("SELECT DISTINCT viewClothes FROM ClothingMatchingTable WHERE phoneClothes = ?1")
    String getView(String nomenkl);
}
