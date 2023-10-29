package com.myisu_1.isu.models.Matrix;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.models.Phone.MatrixSpark;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Data
@Getter
@Setter
public class Matrix {
    public List<String> distributionModelList;
    public List<OrderRecommendations> remainMatrixList;
    public List<MatrixSpark> matrixSparks;

    Map<String,Map<String,Integer>> shopRemains ;
    Map<String,Map<String,String>> shopQuantity ;
    Map<String,Map<String,Integer>> shopRemainsSpark ;
    Map<String,Map<String,String>> shopQuantitySpark ;

    public void  createMatrix(List<Authorization_tt> shopT2) {
        shopRemains = new TreeMap<>();
        shopQuantity = new TreeMap<>();
        for (Authorization_tt o:shopT2){
            shopRemains.put(o.getName(),distributionMode(o.getName()));
            shopQuantity.put(o.getName(),distributionModeQuantity(o.getName()));
        }
    }

    public void  createMatrixSpark(List<Authorization_tt> shopT2) {
        shopRemainsSpark = new TreeMap<>();
        shopQuantitySpark = new TreeMap<>();
        for (Authorization_tt o:shopT2){

            shopRemainsSpark.put(o.getName(),distributionMode(o.getName()));
            shopQuantitySpark.put(o.getName(),distributionModeQuantity(o.getName()));
        }
    }

    private Map<String, Integer> distributionMode(String shop) {

        Map<String,Integer>  distributionMode = new LinkedHashMap<>();
        for (String s:distributionModelList){
            distributionMode.put(s,searchCluster(shop,s));
        }
        double average = distributionMode.values().stream().filter(r->r!=null).mapToDouble(Integer::doubleValue).average().orElse(0);
        distributionMode.put("итог", (int) average);
        return distributionMode;
    }
    private Map<String, String> distributionModeQuantity(String shop) {

        Map<String,String>  distributionModeQuantity = new LinkedHashMap<>();
        for (String s:distributionModelList){
            distributionModeQuantity.put(s,searchClusterQuantity(shop,s));
        }

        return distributionModeQuantity;
    }
    private String searchClusterQuantity(String shop, String s) {
        OrderRecommendations  rem = remainMatrixList.stream().filter(r -> r.getShop().equals(shop) && r.getGroup().equals(s)).findAny().orElse(null);
        int matrixSpark = matrixSparks.stream().filter(r -> r.getShop().equals(shop) && r.getGroup().equals(s)).mapToInt(r->r.getMatrix()).sum();

        return matrixSpark + "/" +  String.valueOf(rem == null ? 0 : rem.getRemainsShopL());
    }

    private Integer searchCluster(String shop, String s) {
        int  rem = remainMatrixList.stream().filter(r -> r.getShop().equals(shop) && r.getGroup().equals(s)).mapToInt(r-> Math.toIntExact(r.getRemainsShopL())).sum();

        int matrixSpark = matrixSparks.stream().filter(r -> r.getShop().equals(shop) && r.getGroup().equals(s)).mapToInt(r->r.getMatrix()).sum();

        return matrixSpark == 0 ? null : rem == 0 ? 0 : (int) Math.min(Double.valueOf(rem) / Double.valueOf(matrixSpark) * 100, 100);
    }
}
