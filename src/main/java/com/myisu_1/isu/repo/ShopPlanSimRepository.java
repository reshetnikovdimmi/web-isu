package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.ShopPlanSim;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShopPlanSimRepository extends CrudRepository<ShopPlanSim, Integer> {
    @Transactional
    @Modifying
    @Query("update ShopPlanSim u set u.plan= ?1  where u.shop = ?2 AND u.nameSimModem=?3 ")

    void updatePlanSim(Integer i,String s, String s1);


    boolean existsByShopAndNameSimModem(String s, String s1);


    @Query("SELECT SUM(plan)  FROM ShopPlanSim WHERE shop IN ?1 AND nameSimModem = ?2")
    Integer getPlanShopSim(List<String> authorization, String nameRainbow);

    @Query("SELECT nameSimModem  FROM ShopPlanSim WHERE shop = ?1")
    List<String> simShopPlanRem(String shop);
}
