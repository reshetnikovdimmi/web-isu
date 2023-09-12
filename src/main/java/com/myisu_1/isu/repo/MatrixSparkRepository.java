package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.Phone.MatrixSpark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatrixSparkRepository extends JpaRepository<MatrixSpark, Integer> {
    Iterable<MatrixSpark> findByShop(String shop);
}
