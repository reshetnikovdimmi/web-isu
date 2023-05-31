package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.Phone.MatrixT2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MatrixT2Repository extends JpaRepository<MatrixT2, Integer> {
    @Transactional
    Iterable<MatrixT2> deleteByDistributionModel(String distributionModel);

    @Query("SELECT DISTINCT distributionModel FROM MatrixT2 ORDER BY distributionModel ASC")
    List<String> getDistributionModelMatrixDisting();

    @Query("SELECT quantity FROM MatrixT2 WHERE distributionModel = ?1 AND cluster =?2")
    Integer getQuantityMatrix(String distributionModel, String cluster);
}
