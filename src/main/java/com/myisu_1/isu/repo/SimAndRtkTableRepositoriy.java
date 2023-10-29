package com.myisu_1.isu.repo;
import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.dto.RemainsGroupCash;
import com.myisu_1.isu.models.Phone_Smart;
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

    @Query("SELECT DISTINCT nameRainbow FROM SimAndRtkTable WHERE view = 'RTK'")
    List<String> getMatrixRTKAll();

    @Query("SELECT DISTINCT distributionModel FROM SimAndRtkTable WHERE view = 'RTK'")
    List<String> getMatrixRTK();

    @Query("SELECT DISTINCT nameRainbow FROM SimAndRtkTable " +
            " WHERE nameRainbow  NOT IN ?2 AND view IN ?1")
    List<String> getNull(List<String> sim, List<String> shop);

    @Query("SELECT DISTINCT nameRainbow FROM SimAndRtkTable " +
            " WHERE view IN ?1")
    List<String> getNull(List<String> sim);

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(r.shop, c.distributionModel, c.nameRainbow,  r.remainsSimAndModem) FROM SimAndRtkTable c " +
            " JOIN  c.remanisSims r WHERE c.view = 'RTK'")
    List<OrderRecommendations> remainsRTK();

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(r.shop, c.distributionModel, c.nameRainbow, c.view,  r.remainsSimAndModem) FROM SimAndRtkTable c " +
            " JOIN  c.remanisSims r")
    List<OrderRecommendations> remainsSim();
    
    @Query("SELECT DISTINCT new com.myisu_1.isu.dto.RemainsGroupCash(distributionModel) FROM SimAndRtkTable WHERE view = 'RTK' " )
    List<RemainsGroupCash> getGroupView();
    @Query("SELECT DISTINCT new com.myisu_1.isu.dto.RemainsGroupCash(distributionModel, view) FROM SimAndRtkTable" )
    List<RemainsGroupCash> getGroupViewSim();
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.shop,c.distributionModel, SUM (p.remainsSimAndModem)) FROM SimAndRtkTable c   " +
            "JOIN c.remanisSims p WHERE c.distributionModel IN ?1 AND p.shop IN ?2  GROUP BY p.shop, c.distributionModel")
    List<OrderRecommendations> getRemainsShopRTKMatrix(List<String> matrix, List<String> shopT2);

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.shop,c.distributionModel, c.nameRainbow, p.remainsSimModem) FROM SimAndRtkTable c   " +
            "JOIN c.saleSim1ms p WHERE c.view = 'RTK'")
    List<OrderRecommendations> getSale1Phone();

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.shop,c.distributionModel, c.nameRainbow, p.remainsSimModem) FROM SimAndRtkTable c   " +
            "JOIN c.saleSim6ms p WHERE c.view = 'RTK'")
    List<OrderRecommendations> getSale6Phone();
    @Query("SELECT new com.myisu_1.isu.models.Phone_Smart (distributionModel, nameRarus,nameRainbow,nameSpark,view) FROM SimAndRtkTable")
    List<Phone_Smart> phoneSmar();
}
