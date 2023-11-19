package com.myisu_1.isu.repo;

import com.myisu_1.isu.dto.SimPlan;
import com.myisu_1.isu.models.SIM.ShopPlanSim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShopPlanSimRepository extends JpaRepository<ShopPlanSim, Integer> {
    @Transactional
    @Modifying
    @Query("update ShopPlanSim u set u.plan= ?2  where u.id = ?1 ")

    void updatePlanSim(Integer id,Integer plan);

    @Query("SELECT nameSimModem  FROM ShopPlanSim WHERE shop = ?1")
    List<String> simShopPlanRem(String shop);

    List<ShopPlanSim> findByShop(String shop);


}
