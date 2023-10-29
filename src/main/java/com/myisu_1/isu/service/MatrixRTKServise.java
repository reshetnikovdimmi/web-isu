package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.models.Matrix.Matrix;
import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.models.RTK.MatrixRTK;
import com.myisu_1.isu.models.distribution.AnalysisDistribution;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MatrixRTKServise extends AnalysisDistribution {
    @Autowired
    private MatrixRTKRepository matrixRTKRepository;
    @Autowired
    private SimAndRtkTableRepositoriy rtkTableRepositoriy;
    @Autowired
    private SaleSimModemRepository_6m saleSimModemRepository6m;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository1m;
    @Autowired
    private RemanisSimRepository remanisSimRepository;
    @Autowired
    private PostRepositoriy authorization_ttRepositoriy;

    Map<String, Map<String, Map<String, Map<String, Integer>>>> shopRTK;
    List<Authorization_tt> authorization;
    List<String> matrixRTKAll;
    List<String> shopRTKList;
    Matrix matrix;
    public Object getSaleRemanisAll() {
        or = new OrderRecommendations();
        remainsNomenclature = rtkTableRepositoriy.remainsRTK();
        sale1Nomenclature = rtkTableRepositoriy.getSale1Phone();
        sale6Nomenclature = rtkTableRepositoriy.getSale6Phone();
        phoneSmarts = rtkTableRepositoriy.phoneSmar();

        warehouse = authorization_ttRepositoriy.getShopList();

        remainsNomenclatureSach(rtkTableRepositoriy.getMatrixRTKAll());
        remainsCashGroup(rtkTableRepositoriy.getGroupView());
        distributionPhone(rtkTableRepositoriy.getGroupView());
        indicatorsPhoneShopGroup(rtkTableRepositoriy.getMatrixRTK(), null);
        return or;

    }

    public Object getTableMatrixRTK() {
        matrix = new Matrix();

        matrix.distributionModelList = matrixRTKRepository.getDistributionModelDistinct();
        List<Authorization_tt> clusterT2List = authorization_ttRepositoriy.getClusterRTKList();
        matrix.matrixSparks = new ArrayList<>();
        matrix.remainMatrixList = rtkTableRepositoriy.getRemainsShopRTKMatrix(matrix.distributionModelList, authorization_ttRepositoriy.getShopRTK());

        for (MatrixRTK m : matrixRTKRepository.findAll()) {
            for (Authorization_tt a : clusterT2List) {
                if (m.getCluster().equals(a.getClusterT2())) {
                    matrix.matrixSparks.add(new MatrixSpark(a.getName(), m.getDistributionModel(), Integer.valueOf(m.getQuantity())));
                }
            }
        }
        matrix.createMatrix(clusterT2List);
        return matrix;
    }




    public List<OrderRecommendations>  createTableDistributionRTK(String shop) {

        return or.getDistributionPhone().stream().filter(r -> r.getShop().equals(shop)).collect(Collectors.toList());
    }

    public OrderRecommendations remanisPhoneSachRTK(String grop) {

        return or;
    }

    public OrderRecommendations distribution(OrderRecommendations order) {
        System.out.println(order);
       // distributions(order,authorization_tt.getShopMult(),matrix.remainMatrixList);
        return or;
    }
}
