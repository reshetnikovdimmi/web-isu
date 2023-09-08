package com.myisu_1.isu.repo;
import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.dto.RemainsGroupCash;
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

    @Query("SELECT DISTINCT nameRainbow FROM SimAndRtkTable " +
            " WHERE nameRainbow  NOT IN ?2 AND view IN ?1")
    List<String> getNull(List<String> sim, List<String> shop);

    @Query("SELECT DISTINCT nameRainbow FROM SimAndRtkTable " +
            " WHERE view IN ?1")
    List<String> getNull(List<String> sim);

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(r.shop, c.distributionModel, c.view, SUM( r.remainsSimAndModem)) FROM SimAndRtkTable c " +
            "LEFT JOIN  c.remanisSims r WHERE r.shop IS NOT NULL and c.distributionModel IS NOT NULL GROUP BY r.shop, c.distributionModel, c.view ORDER BY r.remainsSimAndModem DESC")
    List<OrderRecommendations> remainsSim();
    @Query("SELECT DISTINCT new com.myisu_1.isu.dto.RemainsGroupCash(distributionModel, view) FROM SimAndRtkTable WHERE distributionModel IS NOT NULL GROUP BY distributionModel " )
    List<RemainsGroupCash> getGroupView();
}
