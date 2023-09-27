package com.myisu_1.isu.repo;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.RTK.MatrixRTK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatrixRTKRepository extends JpaRepository<MatrixRTK, Integer> {
    @Query("SELECT DISTINCT cluster FROM MatrixRTK")
    List<String> getClusterDistinct();

    @Query("SELECT DISTINCT distributionModel FROM MatrixRTK ORDER BY distributionModel ASC")
    List<String> getDistributionModelDistinct();


    @Query("SELECT quantity FROM MatrixRTK WHERE cluster = ?1 AND distributionModel = ?2")
    Integer getQuantity(String cluster, String models);


}