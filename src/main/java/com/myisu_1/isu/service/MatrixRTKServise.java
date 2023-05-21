package com.myisu_1.isu.service;

import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class MatrixRTKServise {
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
    List<authorization_tt> authorization;
    List<String> matrixRTKAll;
    List<String> shopRTKList;
    public Object getSaleRemanisAll() {
        authorization  = (List<authorization_tt>) authorization_ttRepositoriy.findAll();
        Map<String,Map<String,Integer>> distributionModel = new TreeMap<>();
        Map<String,Integer> saleRemanis;
        matrixRTKAll = rtkTableRepositoriy.getMatrixRTKAll();
        shopRTK = new TreeMap<>();
        shopRTKList = authorization_ttRepositoriy.getShopRTK();
        List<String> sach  = authorization_ttRepositoriy.getWarehouseList();

        for (String matrixRTK : matrixRTKAll){
            saleRemanis = new TreeMap<>();
            saleRemanis.put("remanisCash",remanisSimRepository.getRemanisRTKCash(rtkTableRepositoriy.getNameRainbow(matrixRTK),sach));
            saleRemanis.put("sale6",saleSimModemRepository6m.getSale6RTK(rtkTableRepositoriy.getNameRainbow(matrixRTK)));
            saleRemanis.put("sale1",saleSimModemRepository1m.getSale1RTK(rtkTableRepositoriy.getNameRainbow(matrixRTK)));
            distributionModel.put(matrixRTK,saleRemanis);
        }

        return distributionModel;

    }

    public Object getSaleRemanisShop(String grop) {
System.out.println(grop);
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

                indicator.put("gvu",111);
                indicator.put("gvu1",111);
                indicator.put("remanisCash",111);

                model.put(name,indicator);

                indicator = new TreeMap<>();
                indicator.put("totalRemanisCash",111);

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
    Map<String,Map<String,Double>> shop = new TreeMap<>();

        for (String shops : shopRTKList){
          List<String> distributionModel = matrixRTKRepository.getDistributionModelDistinct();
            Map<String,Double> indicator = new TreeMap<>();
            for (String model : distributionModel){

                Integer remanis = remanisSimRepository.getRemanisRTKGropShop(rtkTableRepositoriy.getNameRainbow(model),shops);

                Integer matrix = matrixRTKRepository.getQuantity(authorization_ttRepositoriy.getClusterRTK(shops),model);

                Double sufficiency = null;

                if (matrix!=null && matrix!=0 && remanis!=null){
                    sufficiency =  (double)remanis/ (double)matrix;

                }
                if (matrix!=null && matrix!=0 && remanis==null){
                    sufficiency = 0.0;

                }
                if (sufficiency!=null && sufficiency>1){
                    sufficiency = 1.0;

                }

                indicator.put(model,sufficiency);

            }
            indicator.put("и",100.0);

            shop.put(shops,indicator);
        }

        return shop;
    }

    public Map<String, Map<String, Map<String, Integer>>> createTableDistributionRTK(String shop) {

      return   shopRTK.get(shop);
    }
}
