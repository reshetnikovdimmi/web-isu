package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Phone.MatrixT2;
import com.myisu_1.isu.repo.MatrixT2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatrixT2Servise {
    @Autowired
    private MatrixT2Repository matrixT2Repository;

    public List<MatrixT2> matrixT2table() {

        return matrixT2Repository.findAll();
    }
}
