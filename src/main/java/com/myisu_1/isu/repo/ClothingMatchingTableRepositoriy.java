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
    @Query("SELECT DISTINCT  new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop, c.nameClothes,c.viewClothes, SUM (p.remanisClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersPhone p WHERE p.nameShop IN ?1 AND c.nameClothes IN ?2 GROUP BY c.nameClothes, c.viewClothes" )
    List<OrderRecommendations> getRemainsCash(List<String> s, List<String> phone);

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop, c.nameClothes,c.viewClothes, SUM (p.remanisClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersPhone p   GROUP BY c.nameClothes, p.nameShop,c.viewClothes ")
    List<OrderRecommendations> getRemainsShopPhoneTotal();

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop, c.nameClothes,c.viewClothes, SUM (p.saleClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale1 p   GROUP BY c.nameClothes, p.nameShop,c.viewClothes ")
    List<OrderRecommendations> getSale1ShopPhoneTotal();

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop, c.nameClothes,c.viewClothes, SUM (p.saleClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale6 p   GROUP BY c.nameClothes, p.nameShop,c.viewClothes ")
    List<OrderRecommendations> getSale6ShopPhoneTotal();

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(c.nameClothes,c.viewClothes, SUM (p.remanisClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersPhone p   GROUP BY c.nameClothes, c.viewClothes ORDER BY p.remanisClothes ASC")
    List<OrderRecommendations> getRemainsShopPhoneAll();

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations( c.nameClothes,c.viewClothes, SUM (p.saleClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale1 p   GROUP BY c.nameClothes, c.viewClothes ")
    List<OrderRecommendations> getSale1ShopPhoneAll();
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations( c.nameClothes,c.viewClothes, SUM (p.saleClothes)) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale6 p   GROUP BY c.nameClothes, c.viewClothes ")
    List<OrderRecommendations> getSale6ShopPhoneAll();
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop,c.nameClothes, p.namesClothes,c.viewClothes, p.remanisClothes) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersPhone p WHERE p.nameShop IN ?1")
    List<OrderRecommendations> getRemainsNomenclatureSohp(List<String> listCash);

    @Query("SELECT phoneClothes FROM ClothingMatchingTable WHERE nameClothes = ?1")
    List<String> getRemainsNomenclatureModels(String models);

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(c.nameClothes, c.phoneClothes,c.viewClothes, p.remanisClothes) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersPhone p WHERE p.nameShop = ?1 AND c.nameClothes =?2 AND c.viewClothes= ?3 ")
    List<OrderRecommendations> getRemainsNomenclatureSohpAll(String shop, String models, String view);

    @Modifying
    @Transactional
    @Query("update ClothingMatchingTable u set u.nameClothes = ?1 where u.nameClothes = ?2")
    void updateBrend(String brend, String brend1);

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(c.nameClothes, c.phoneClothes,c.viewClothes, p.saleClothes) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale1 p WHERE p.nameShop = ?1 AND c.nameClothes =?2 AND c.viewClothes= ?3 ")
    List<OrderRecommendations> getSale1NomenclatureSohpAll(String shop, String models, String view);
    @Query("SELECT phoneClothes FROM ClothingMatchingTable WHERE nameClothes =?1 AND viewClothes= ?2")
    List<String> getNomenklatureGroup(String models, String view);

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(c.nameClothes, c.phoneClothes,c.viewClothes, p.saleClothes) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersSale6 p WHERE p.nameShop = ?1 AND c.nameClothes =?2 AND c.viewClothes= ?3 ")
     List<OrderRecommendations> getSale6NomenclatureSohpAll(String shop, String models, String view);

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.nameShop, c.nameClothes, c.phoneClothes,c.viewClothes, p.remanisClothes) FROM ClothingMatchingTable c   " +
            "JOIN c.clothersPhone p WHERE p.nameShop IN ?1")
    List<OrderRecommendations> getRemainsNomenclatureCash(List<String> listCash);
}
