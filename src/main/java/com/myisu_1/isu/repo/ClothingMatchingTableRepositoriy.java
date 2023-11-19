package com.myisu_1.isu.repo;


import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.dto.RemainsGroupCash;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import com.myisu_1.isu.models.Phone_Smart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClothingMatchingTableRepositoriy extends JpaRepository<ClothingMatchingTable, Integer>{


    @Modifying
    @Transactional
    @Query("update ClothingMatchingTable u set u.nameClothes = ?1 where u.nameClothes = ?2")
    void updateBrend(String brend, String brend1);


    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop,c.nameClothes, p.nameClothes,c.viewClothes, p.saleClothes) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale1 p")
    List<OrderRecommendations> getSale1ShopClothingGroupAll();
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop,c.nameClothes, p.nameClothes,c.viewClothes, p.saleClothes) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale6 p")
    List<OrderRecommendations> getSale6ShopClothingGroupAll();

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(r.nameShop, c.nameClothes, c.phoneClothes, c.viewClothes,  r.remanisClothes) FROM ClothingMatchingTable c " +
            " JOIN  c.clothersPhone r")

    List<OrderRecommendations> remainsSim();
    @Query("SELECT DISTINCT new com.myisu_1.isu.dto.RemainsGroupCash(nameClothes, viewClothes) FROM ClothingMatchingTable" )

    List<RemainsGroupCash> getGroupViewSim();
    @Query("SELECT DISTINCT nameClothes FROM ClothingMatchingTable")
    List<String> getDistributionModelDISTINCT();
    @Query("SELECT new com.myisu_1.isu.models.Phone_Smart (nameClothes, phoneClothes,phoneClothes,phoneClothes,viewClothes) FROM ClothingMatchingTable")
    List<Phone_Smart> phoneSmar();
    @Query("SELECT DISTINCT phoneClothes FROM ClothingMatchingTable")
    List<String> getNameRainbows();
}
