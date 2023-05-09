package com.myisu_1.isu.repo;
import com.myisu_1.isu.models.SIM.SimAndRtkTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimAndRtkTableRepositoriy extends JpaRepository<SimAndRtkTable, Integer> {

    @Query("SELECT DISTINCT distributionModel FROM SimAndRtkTable")
    List<String> getDistributionModelDISTINCT();

    @Query("SELECT nameRainbow FROM SimAndRtkTable WHERE distributionModel = ?1")
    List<String> getNameRainbow(String s);

    @Query("SELECT distributionModel FROM SimAndRtkTable WHERE nameRainbow = ?1")
    String getDistributionModelS(String nameRainbow);

    SimAndRtkTable findByNameRainbow(String s);
}
