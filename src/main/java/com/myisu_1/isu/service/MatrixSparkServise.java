package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.repo.MatrixSparkRepository;
import com.myisu_1.isu.repo.MatrixT2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatrixSparkServise {
    @Autowired
    private MatrixSparkRepository matrixSparkRepository;

    public Iterable<MatrixSpark> matrixSparktable() {

        return  matrixSparkRepository.findAll();
    }
}
