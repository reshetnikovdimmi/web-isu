package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.repo.MatrixSparkRepository;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.SalesRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatrixSparkServise {
    @Autowired
    private MatrixSparkRepository matrixSparkRepository;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private SalesRepositoriy salesRepositoriy;

    public Iterable<MatrixSpark> matrixSparktable(String shop) {

        return matrixSparkRepository.findByShop(shop);
    }

    public Iterable<MatrixSpark> matrixSparkTableUpdate() {


        return null;
    }

    public Object saveSparkSale(List<MatrixSpark> sim) {
        matrixSparkRepository.deleteAll();
        matrixSparkRepository.saveAll(sim);
        return  matrixSparkRepository.findAll();
    }
}
