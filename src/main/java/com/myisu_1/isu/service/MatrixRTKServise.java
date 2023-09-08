package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.models.distribution.AnalysisDistribution;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    Map<String,Map<String,Map<String,Map<String,Integer>>>> shopRTK;
    List<Authorization_tt> authorization;
    List<String> matrixRTKAll;
    List<String> shopRTKList;
    public Object getSaleRemanisAll() {
        remains = rtkTableRepositoriy.remainsSim();
        remainsCash = authorization_ttRepositoriy.getWarehouseList();
        return remainsCashGroup(rtkTableRepositoriy.getGroupView());

    }

    public Object getSaleRemanisShop(String grop) {

        Map<String,Map<String,Integer>> distributionModel = new TreeMap<>();
        Map<String,Integer> saleRemanis;

        for (String shop : shopRTKList){
            saleRemanis = new TreeMap<>();
            saleRemanis.put("remanis",remanisSimRepository.getRemanisRTKGropShop(rtkTableRepositoriy.getNameRainbow(grop),shop));
            saleRemanis.put("sale6",saleSimModemRepository6m.getSale6DistrModel(rtkTableRepositoriy.getNameRainbow(grop),shop));
            saleRemanis.put("sale1",saleSimModemRepository1m.getSale1DistrModel(rtkTableRepositoriy.getNameRainbow(grop),shop));
            distributionModel.put(shop,saleRemanis);
        }

        return distributionModel;

    }

    public Map<String,Map<String,Map<String,Integer>>> remanisSaleShopRTK(String shop) {
if (shopRTK.containsKey(shop)){
    return shopRTK.get(shop);
}else {


    for (String shops : shopRTKList){
        Map<String,Map<String,Map<String,Integer>>> distributionModel = new TreeMap<>();
        for (String matrixRTK : matrixRTKAll){
            List<String> nameRTK = rtkTableRepositoriy.getNameRainbow(matrixRTK);
            Map<String,Map<String,Integer>> model = new TreeMap<>();
            for (String name : nameRTK){
                Map<String,Integer> indicator = new TreeMap<>();

                indicator.put("sale1", saleSimModemRepository1m.getSale1SimShop(name, shops));
                indicator.put("sale6", saleSimModemRepository6m.getSale6SimShop(name, shops));
                indicator.put("remanis", remanisSimRepository.getRemanisSimShop(name, shops));
                indicator.put("remanisCash",remanisSimRepository.getRemanisSimShop(name,shop));
                indicator.put("order", 0);
                model.put(name,indicator);

                indicator = new TreeMap<>();
                indicator.put("totalRemanis", remanisSimRepository.totalSimRTK(rtkTableRepositoriy.getNameRainbow(matrixRTK), shops));
                indicator.put("totalSale1", saleSimModemRepository1m.getSale1DistrModel(rtkTableRepositoriy.getNameRainbow(matrixRTK), shops));
                indicator.put("totalSale6", saleSimModemRepository6m.getSale6DistrModel(rtkTableRepositoriy.getNameRainbow(matrixRTK), shops));
                indicator.put("totalRemanisCash",remanisSimRepository.totalSimRTK(rtkTableRepositoriy.getNameRainbow(matrixRTK), authorization.get(2).getName()));
                indicator.put("orderCash", 0);
                model.put("total",indicator);


            }


            distributionModel.put(matrixRTK,model);
        }
        shopRTK.put(shops,distributionModel);
    }

    return shopRTK.get(shop);
}


    }

    public Object remanisCashRTK(String grop) {

      return   remanisSaleShopRTK(authorization.get(2).getName()).get(grop);

    }

    public Object getTableMatrixRTK() {
    Map<String,Map<String,String>> shop = new TreeMap<>();

        for (String shops : shopRTKList){
            Double sufficiencyTotal = 0.0;
            int cou = 0;
          List<String> distributionModel = matrixRTKRepository.getDistributionModelDistinct();
            Map<String,String> indicator = new TreeMap<>();
            for (String model : distributionModel){

                Integer remanis = remanisSimRepository.getRemanisRTKGropShop(rtkTableRepositoriy.getNameRainbow(model),shops);

                Integer matrix = matrixRTKRepository.getQuantity(authorization_ttRepositoriy.getClusterRTK(shops),model);

                Double sufficiency = null;
                String s = null;

                if (matrix!=null && matrix!=0 && remanis!=null){
                    sufficiency =  (double)remanis/ (double)matrix;

                }
                if (matrix!=null && matrix!=0 && remanis==null){
                    sufficiency = 0.0;

                }
                if (sufficiency!=null && sufficiency>1){
                    sufficiency = 1.0;

                }
                if (matrix!=null && sufficiency!=null){
                    sufficiencyTotal += sufficiency;
                    cou++;
                }
                if (sufficiency!=null){
                    s = String.format("%.0f", sufficiency * 100) + "%";

                }
                

                indicator.put(model,s);

            }

            indicator.put("total",String.format("%.0f",sufficiencyTotal/cou*100)+"%");

            shop.put(shops,indicator);
        }

        return shop;
    }

    public Map<String, Map<String, Map<String, Integer>>> createTableDistributionRTK(String shop) {

      return   shopRTK.get(shop);
    }
}
