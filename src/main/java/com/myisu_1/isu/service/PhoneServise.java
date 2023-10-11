package com.myisu_1.isu.service;


import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.models.Matrix.Matrix;
import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.models.Phone.MatrixT2;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.distribution.AnalysisDistribution;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PhoneServise extends AnalysisDistribution {
    @Autowired
    public SalesRepositoriy salesRepositoriy;
    @Autowired
    public PostRepositoriy authorization_tt;
    @Autowired
    public PhoneRepositoriy phoneRepositoriy;
    @Autowired
    public MatrixT2Repository matrixT2Repository;
    List<Phone_Smart> phoneSmartList;
    List<RemanisSim> remanisSimList;
    List<Authorization_tt> clusterT2List;
    Matrix matrix;

    public OrderRecommendations distributionModel() {
        or = new OrderRecommendations();
        sale1Nomenclature = phoneRepositoriy.getSale1Phone();
        sale6Nomenclature = phoneRepositoriy.getSale6Phone();
        remainsNomenclature = phoneRepositoriy.getRemainsShopPhone();
        warehouse = authorization_tt.getShopList();
        phoneSmarts = phoneRepositoriy.findAll();
        remainsCashGroup(phoneRepositoriy.getGroupView());
        remainsNomenclatureSach(phoneRepositoriy.getModelAll());
        distributionPhone(phoneRepositoriy.getGroupView());
        indicatorsPhoneShopGroup(phoneRepositoriy.getMatrix_T2(), matrix.remainMatrixList);
        return or;
    }

    public List<OrderRecommendations> remanisPhoneShopT2() {
        or.setRemanisPhoneShopT2(remainsSaleShopAll(authorization_tt.getShopT2(), matrix.remainMatrixList));
        return or.getRemanisPhoneShopT2();
    }

    public List<OrderRecommendations> remanisPhoneShopMult() {
        or.setRemanisPhoneShopMult(remainsSaleShopAll(authorization_tt.getShopMult(), matrix.remainMatrixList));
        return or.getRemanisPhoneShopMult();
    }

    public OrderRecommendations remanisPhoneSach(String matrixT2) {
        return or;
    }


    public List<OrderRecommendations> remanisSaleShop(String shop) {
        return or.getDistributionPhone().stream().filter(r -> r.getShop().equals(shop)).collect(Collectors.toList());
    }

    public Object createMatrixT2() {
        matrix = new Matrix();
        matrix.distributionModelList = matrixT2Repository.getDistingMatrix();
        clusterT2List = authorization_tt.getClusterT2List();
        matrix.matrixSparks = new ArrayList<>();
        matrix.remainMatrixList = phoneRepositoriy.getRemainsShopPhoneMatrix(matrix.distributionModelList, authorization_tt.getShopT2());
        for (MatrixT2 m : matrixT2Repository.findAll()) {
            for (Authorization_tt a : clusterT2List) {
                if (Integer.parseInt(m.getCluster()) == Integer.parseInt(String.valueOf(a.getClusterT2().charAt(0)))) {
                    matrix.matrixSparks.add(new MatrixSpark(a.getName(), m.getDistributionModel(), Integer.valueOf(m.getQuantity())));
                }
            }
        }
        return matrix.createMatrix(clusterT2List);
    }

    public OrderRecommendations distributionPhoneList() {
        return or;
    }


    public OrderRecommendations distribution(OrderRecommendations order) {

        return or;
    }
}
