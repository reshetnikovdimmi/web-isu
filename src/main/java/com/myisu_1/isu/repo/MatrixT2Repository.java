package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.Phone.MatrixT2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface MatrixT2Repository extends JpaRepository<MatrixT2, Integer> {
    @Transactional
    Iterable<MatrixT2> deleteByDistributionModel(String distributionModel);
}
