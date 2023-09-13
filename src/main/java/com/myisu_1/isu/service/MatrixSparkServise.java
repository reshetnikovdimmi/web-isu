package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.repo.MatrixSparkRepository;
import com.myisu_1.isu.repo.MatrixT2Repository;
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
    @Autowired
    private MatrixT2Repository matrixT2Repository;


    public Iterable<MatrixSpark> matrixSparktable(String shop) {

        return matrixSparkRepository.findByShop(shop);
    }

    public Iterable<MatrixSpark> matrixSparkTableUpdate(String shop) {
        matrixSparkRepository.deleteByShop(shop);
        List<String> matrix = matrixT2Repository.getDistingMatrix();
        List<MatrixSpark> saleAll = phoneRepositoriy.getSaleAllMatrix(matrix);
        List<MatrixSpark> sale6 =  phoneRepositoriy.getSale6Shop(shop);
        List<MatrixSpark> sale1 = phoneRepositoriy.getSale1Shop(shop);
        List<MatrixSpark> dto = new ArrayList<>();
        for (String s:matrix){
            MatrixSpark matrixSpark = new MatrixSpark();
            matrixSpark.setShop(shop);
            matrixSpark.setGroup(s);
            MatrixSpark saleAlls = saleAll.stream().filter(r -> r.getGroup().equals(s)).findAny().orElse(null);
            matrixSpark.setSaleAll(saleAlls==null?null:saleAlls.getSaleAll());
            MatrixSpark sales6 = sale6.stream().filter(r -> r.getGroup().equals(s)).findAny().orElse(null);
            matrixSpark.setSale6(sales6==null?null:sales6.getSaleAll());
            MatrixSpark sales1 = sale1.stream().filter(r -> r.getGroup().equals(s)).findAny().orElse(null);
            matrixSpark.setSale1(sales1==null?null:sales1.getSaleAll());

            dto.add(matrixSpark);
        }


        return dto;
    }

    public Object saveSparkSale(List<MatrixSpark> sim) {
        matrixSparkRepository.saveAll(sim);
       System.out.println(sim);
        return  matrixSparkRepository.findAll();
    }
}
