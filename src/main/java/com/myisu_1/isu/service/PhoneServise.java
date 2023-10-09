package com.myisu_1.isu.service;


import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.models.Matrix.Matrix;
import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.models.Phone.MatrixT2;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.distribution.AnalysisDistribution;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class PhoneServise extends AnalysisDistribution {
    @Autowired
    private SalesRepositoriy salesRepositoriy;
    @Autowired
    public PostRepositoriy authorization_tt;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private RemanisSimRepository remanisSimRepository;
    @Autowired
    private SaleSimModemRepository_6m saleSimModemRepository_6m;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository_1m;
    @Autowired
    private MatrixT2Repository matrixT2Repository;
    @Autowired
    private MatrixSparkRepository matrixSparkRepository;

    List<Sales> sales;
    public List<Authorization_tt> authorization_ttList;
    List<Phone_Smart> phoneSmartList;
    List<RemanisSim> remanisSimList;
    Map<String, Map<String, Map<String, Map<String, Integer>>>> remanisSaleShop;

    List<String> matrix_T2;
    List<String> distributionModelMatrix;
    Map<String, Map<String, String>> shopMatrix;
    List<Authorization_tt> clusterT2List;

    private int remanis;
    Matrix matrix;

    public OrderRecommendations distributionModel() {
        indicatorPhoneShop = phoneRepositoriy.getRemainsShopPhoneGroup(null);
        sale1 = phoneRepositoriy.getSale1Phone(null);
        sale6 = phoneRepositoriy.getSale6Phone(null);
        remainsGroup = phoneRepositoriy.getRemainsShopPhone();
        warehouse = authorization_tt.getWarehouseList();
        remainsAll = phoneRepositoriy.remainsAll();
        or = new OrderRecommendations();
        remainsCashGroup(phoneRepositoriy.getGroupView());
        return or;
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

    public List<OrderRecommendations> remanisPhoneShopT2() {


        return remainsSaleShopAll(authorization_tt.getShopT2(),matrix.remainMatrixList);
    }

    public List<OrderRecommendations> remanisPhoneShopMult() {

        return remainsSaleShopAll(authorization_tt.getShopMult(), matrix.remainMatrixList);
    }

    public OrderRecommendations remanisPhoneSach(String matrixT2) {


        indicatorPhoneShop = phoneRepositoriy.getRemainsShopPhoneGroup(matrixT2);
        sale1 = phoneRepositoriy.getSale1Phone(matrixT2);
        sale6 = phoneRepositoriy.getSale6Phone(matrixT2);
        indicatorsPhoneShopGroup(authorization_tt.getShopList(), matrix.remainMatrixList);
        remainsNomenclatureSach(phoneRepositoriy.getModelAll());
       return or;
    }






    public OrderRecommendations remanisSaleShop(String shop) {

        sale1 = phoneRepositoriy.getSale1PhoneShop();
        sale6 = phoneRepositoriy.getSale6PhoneShop();
        remainsGroup = phoneRepositoriy.getRemainsShopPhone();
        distributionPhone(phoneRepositoriy.getGroupView(),shop);
        return or;
    }



    public Map<String, Map<String, Map<String, Map<String, Integer>>>> distributionPhoneList() {


        return remanisSaleShop;
    }





    public Object distributionModelMatrix() {


        return null;
    }


    public Map<String, Map<String, Map<String, Integer>>> tableUpDistriPhone(String shop, String models, String quantity, String brend) {


        return null;
    }



    public Object updateRemanisSaleMatrixT2Shop(String model) {


        return null;
    }

    public Object updateRemanisSaleModelShop(String model) {

        return null;
    }
}
