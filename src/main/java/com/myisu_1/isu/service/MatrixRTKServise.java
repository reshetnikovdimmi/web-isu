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
import java.util.TreeMap;

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

    public Object getSaleRemanisAll() {
        remainsNomenclature = rtkTableRepositoriy.remainsSim();
        warehouse = authorization_ttRepositoriy.getWarehouseList();
        or = new OrderRecommendations();
        remainsCashGroup(rtkTableRepositoriy.getGroupView());
        return or;

    }

    public Object getTableMatrixRTK() {
        Matrix matrix = new Matrix();

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

        return matrix.createMatrix(clusterT2List);
    }

    public Object getSaleRemanisShop(String grop) {

        Map<String, Map<String, Integer>> distributionModel = new TreeMap<>();
        Map<String, Integer> saleRemanis;

        for (String shop : shopRTKList) {
            saleRemanis = new TreeMap<>();
            saleRemanis.put("remanis", remanisSimRepository.getRemanisRTKGropShop(rtkTableRepositoriy.getNameRainbow(grop), shop));
            saleRemanis.put("sale6", saleSimModemRepository6m.getSale6DistrModel(rtkTableRepositoriy.getNameRainbow(grop), shop));
            saleRemanis.put("sale1", saleSimModemRepository1m.getSale1DistrModel(rtkTableRepositoriy.getNameRainbow(grop), shop));
            distributionModel.put(shop, saleRemanis);
        }

        return distributionModel;

    }

    public Map<String, Map<String, Map<String, Integer>>> remanisSaleShopRTK(String shop) {
        if (shopRTK.containsKey(shop)) {
            return shopRTK.get(shop);
        } else {


            for (String shops : shopRTKList) {
                Map<String, Map<String, Map<String, Integer>>> distributionModel = new TreeMap<>();
                for (String matrixRTK : matrixRTKAll) {
                    List<String> nameRTK = rtkTableRepositoriy.getNameRainbow(matrixRTK);
                    Map<String, Map<String, Integer>> model = new TreeMap<>();
                    for (String name : nameRTK) {
                        Map<String, Integer> indicator = new TreeMap<>();

                        indicator.put("sale1", saleSimModemRepository1m.getSale1SimShop(name, shops));
                        indicator.put("sale6", saleSimModemRepository6m.getSale6SimShop(name, shops));
                        indicator.put("remanis", remanisSimRepository.getRemanisSimShop(name, shops));
                        indicator.put("remanisCash", remanisSimRepository.getRemanisSimShop(name, shop));
                        indicator.put("order", 0);
                        model.put(name, indicator);

                        indicator = new TreeMap<>();
                        indicator.put("totalRemanis", remanisSimRepository.totalSimRTK(rtkTableRepositoriy.getNameRainbow(matrixRTK), shops));
                        indicator.put("totalSale1", saleSimModemRepository1m.getSale1DistrModel(rtkTableRepositoriy.getNameRainbow(matrixRTK), shops));
                        indicator.put("totalSale6", saleSimModemRepository6m.getSale6DistrModel(rtkTableRepositoriy.getNameRainbow(matrixRTK), shops));
                        indicator.put("totalRemanisCash", remanisSimRepository.totalSimRTK(rtkTableRepositoriy.getNameRainbow(matrixRTK), authorization.get(2).getName()));
                        indicator.put("orderCash", 0);
                        model.put("total", indicator);


                    }


                    distributionModel.put(matrixRTK, model);
                }
                shopRTK.put(shops, distributionModel);
            }

            return shopRTK.get(shop);
        }


    }

    public Object remanisCashRTK(String grop) {

        return remanisSaleShopRTK(authorization.get(2).getName()).get(grop);

    }


    public Map<String, Map<String, Map<String, Integer>>> createTableDistributionRTK(String shop) {

        return shopRTK.get(shop);
    }
}
