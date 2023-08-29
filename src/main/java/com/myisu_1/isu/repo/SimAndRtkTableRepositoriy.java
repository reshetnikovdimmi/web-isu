package com.myisu_1.isu.repo;
import com.myisu_1.isu.dto.SimPlan;
import com.myisu_1.isu.models.SIM.ShopPlanSim;
import com.myisu_1.isu.models.SIM.SimAndRtkTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimAndRtkTableRepositoriy extends JpaRepository<SimAndRtkTable, Integer> {

    @Query("SELECT DISTINCT distributionModel FROM SimAndRtkTable")
    List<String> getDistributionModelDISTINCT();

    @Query("SELECT DISTINCT nameRainbow FROM SimAndRtkTable")
    List<String> getNameRainbows();

    @Query("SELECT nameRainbow FROM SimAndRtkTable WHERE distributionModel = ?1")
    List<String> getNameRainbow(String s);

    @Query("SELECT distributionModel FROM SimAndRtkTable WHERE nameRainbow = ?1")
    String getDistributionModelS(String nameRainbow);

    SimAndRtkTable findByNameRainbow(String s);

    @Query("SELECT DISTINCT distributionModel FROM SimAndRtkTable WHERE view = 'RTK'")
    List<String> getMatrixRTKAll();

    @Query("SELECT DISTINCT new com.myisu_1.isu.dto.SimPlan(p.id,c.nameRainbow, s.remainsSimModem, o.remainsSimModem, r.remainsSimAndModem, p.plan) FROM SimAndRtkTable c " +
            "LEFT JOIN c.shopPlanSims p  LEFT JOIN  c.remanisSims r LEFT JOIN c.saleSim6ms s LEFT JOIN c.saleSim1ms o WHERE r.shop = ?1 AND s.shop = ?1 AND o.shop = ?1 AND p.shop = ?1  AND c.view IN ?2")
    List<SimPlan> simPlanOrder(String shop, List<String> sim);

    @Query("SELECT DISTINCT nameRainbow FROM SimAndRtkTable " +
            " WHERE nameRainbow NOT IN ?2 AND view IN ?1")
    List<String> getNull(List<String> sim, List<String> shop);

    @Query("SELECT DISTINCT nameRainbow FROM SimAndRtkTable " +
            " WHERE view IN ?1")
    List<String> getNull(List<String> sim);
}
