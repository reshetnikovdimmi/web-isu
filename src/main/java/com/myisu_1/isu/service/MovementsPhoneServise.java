package com.myisu_1.isu.service;

import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.SaleSim_1m;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovementsPhoneServise extends PhoneServise{
    @Autowired
    private SalesRepositoriy salesRepositoriy;
    @Autowired
    private PostRepositoriy authorization_tt;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private RemanisSimRepository remanisSimRepository;
    @Autowired
    private SaleSimModemRepository saleSimModemRepository;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository_1m;
    @Autowired
    private MatrixT2Repository matrixT2Repository;
    @Autowired
    private MatrixSparkRepository matrixSparkRepository;

    public void LoadAuthorization_ttList() {
        authorization_ttList = (List<authorization_tt>) authorization_tt.findAll();
        sales = (List<Sales>) salesRepositoriy.findAll();
        phoneSmartList = phoneRepositoriy.findAll();
        remanisSimList = (List<RemanisSim>) remanisSimRepository.findAll();
        saleSim_1mList = (List<SaleSim_1m>) saleSimModemRepository_1m.findAll();
        saleSim_6mList = (List<SaleSim_6m>) saleSimModemRepository.findAll();
        matrixT2List = matrixT2Repository.findAll();
        matrixSparkList = matrixSparkRepository.findAll();
        distributionPhoneList = new ArrayList<>();
        System.out.println(authorization_ttList.size());
        Disting();
    }
}
