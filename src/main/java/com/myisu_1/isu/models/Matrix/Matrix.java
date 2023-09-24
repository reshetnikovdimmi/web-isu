package com.myisu_1.isu.models.Matrix;

import com.myisu_1.isu.dto.OrderRecommendations;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
@Getter
@Setter
public class Matrix {
    public List<String> distributionModelList;
    public List<OrderRecommendations> remainMatrixList;
    Matrix dtoMatrix;
    Map<String,Map<String,Integer>> shopRemains ;


    public Object createMatrix() {
        shopRemains = new HashMap<>();
        Map<String,Integer> distributionMode = new HashMap<>();

for (OrderRecommendations o:remainMatrixList){
    shopRemains.put(o.getShop(),distributionMode);
}


        dtoMatrix = new Matrix();
        dtoMatrix.setDistributionModelList(distributionModelList);
        dtoMatrix.setShopRemains(shopRemains);
        return dtoMatrix;
    }
}
