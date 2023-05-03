package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.ShopPlanSim;
import com.myisu_1.isu.models.Sales;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ShopPlanSimRepository extends CrudRepository<ShopPlanSim, Integer> {
    @Transactional
    @Modifying
    @Query("update ShopPlanSim u set u.plan= ?1  where u.shop = ?2 AND u.nameSimModem=?3 ")

    void updatePlanSim(Integer i,String s, String s1);


    boolean existsByShopAndNameSimModem(String s, String s1);

}
