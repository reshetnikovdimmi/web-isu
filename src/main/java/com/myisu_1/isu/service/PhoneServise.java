package com.myisu_1.isu.service;


import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.dto.RemainsGroupCash;
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
        indicatorPhoneShop = phoneRepositoriy.getRemainsShopPhoneGroup();
        sale1 = phoneRepositoriy.getSale1Phone();
        sale6 = phoneRepositoriy.getSale6Phone();
        remains = phoneRepositoriy.getRemainsShopPhoneMatrixT2();
        warehouse = authorization_tt.getWarehouseList();
        or = new OrderRecommendations();
        return indicatorsPhoneShop(phoneRepositoriy.getGroupView());
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
       remains = phoneRepositoriy.getRemainsShopPhone(matrixT2);
        indicatorPhoneShop = phoneRepositoriy.getRemainsShopPhoneGroup();
        sale1 = phoneRepositoriy.getSale1Phone();
        sale6 = phoneRepositoriy.getSale6Phone();
        indicatorsPhoneShopGroup(authorization_tt.getShopMult(), matrix.remainMatrixList);
        indicatorsPhoneSach(phoneRepositoriy.getRemainsGroupView(matrixT2));
       return or;
    }




    public Map<String, Map<String, Map<String, Map<String, Integer>>>> distributionPhoneList() {


        return remanisSaleShop;
    }

    public Map<String, Map<String, Map<String, Integer>>> remanisSaleShop(String shop) {
        System.out.println(shop);
        if (!remanisSaleShop.containsKey(shop)) {
            Map<String, Map<String, Map<String, Integer>>> matrixT2 = new TreeMap<>();


            for (String matrix : matrix_T2) {
                Map<String, Map<String, Integer>> model = new TreeMap<>();
                Map<String, Integer> indicator;
                for (String models : phoneRepositoriy.getModelMatrixT2List(matrix)) {
                    indicator = new TreeMap<>();

                    indicator.put("remanis", remanisSimRepository.getRemanisSimShop(models, shop));

                    indicator.put("sale1", saleSimModemRepository_1m.getSale1SimShop(models, shop));
                    indicator.put("sale6", saleSimModemRepository_6m.getSale6SimShop(models, shop));
                    if (remanisSaleShop.containsKey(authorization_ttList.get(0).getName())) {
                        indicator.put("remanisCash", remanisSaleShop.get(authorization_ttList.get(0).getName()).get(matrix).get(models).get("remanisCash"));
                    } else {
                        indicator.put("remanisCash", remanisSimRepository.getRemanisSimShop(models, authorization_ttList.get(0).getName()));
                    }
                    if (remanisSaleShop.containsKey(authorization_ttList.get(1).getName())) {
                        indicator.put("remanisCash2", remanisSaleShop.get(authorization_ttList.get(1).getName()).get(matrix).get(models).get("remanisCash2"));
                    } else {
                        indicator.put("remanisCash2", remanisSimRepository.getRemanisSimShop(models, authorization_ttList.get(1).getName()));
                    }

                    indicator.put("order", 0);

                    model.put(models, indicator);


                }
                indicator = new TreeMap<>();
                indicator.put("totalRemanis", phoneRepositoriy.getPhoneRemanMatrix(matrix, shop));
                indicator.put("totalSale1", phoneRepositoriy.getPhoneSale1Matrix(matrix, shop));
                indicator.put("totalSale6", phoneRepositoriy.getPhoneSale6Matrix(matrix, shop));
                if (remanisSaleShop.containsKey(authorization_ttList.get(0).getName())) {
                    indicator.put("totalRemanisCash", remanisSaleShop.get(authorization_ttList.get(0).getName()).get(matrix).get("total").get("totalRemanisCash"));
                } else {
                    indicator.put("totalRemanisCash", phoneRepositoriy.getPhoneRemanMatrix(matrix, authorization_ttList.get(0).getName()));
                }
                if (remanisSaleShop.containsKey(authorization_ttList.get(1).getName())) {
                    indicator.put("totalRemanisCash2", remanisSaleShop.get(authorization_ttList.get(1).getName()).get(matrix).get("total").get("totalRemanisCash2"));
                } else {
                    indicator.put("totalRemanisCash2", phoneRepositoriy.getPhoneRemanMatrix(matrix, authorization_ttList.get(1).getName()));
                }

                indicator.put("orderCash", 0);

                indicator.put("matrix", 3);

                model.put("total", indicator);

                matrixT2.put(matrix, model);

            }
            remanisSaleShop.put(shop, matrixT2);
        }


        return remanisSaleShop.get(shop);
    }




    private Map<String, Map<String, Integer>> indicatorPhoneShop(List<String> shopT2) {
        Map<String, Map<String, Integer>> remanisPhoneShop1 = new TreeMap<>();
        List<String> model = phoneRepositoriy.getModelList();
        for (String shop : shopT2) {
            Map<String, Integer> indicator = new TreeMap<>();
            indicator.put("remanis", remanisSimRepository.getRemanisRTKGropShop(model, shop));
            indicator.put("sale1", saleSimModemRepository_1m.getSale1DistrModel(model, shop));
            indicator.put("sale6", saleSimModemRepository_6m.getSale6DistrModel(model, shop));
            indicator.put("requirement", 27);
            indicator.put("assortment", 55);
            remanisPhoneShop1.put(shop, indicator);
        }


        return remanisPhoneShop1;
    }


    public Object distributionModelMatrix() {
        distributionModelMatrix = matrixT2Repository.getDistributionModelMatrixDisting();

        return distributionModelMatrix;
    }


    public Map<String, Map<String, Map<String, Integer>>> tableUpDistriPhone(String shop, String models, String quantity, String brend) {
        System.out.println(shop + "--" + models + "--" + quantity + "--" + brend);

        remanisSaleShop.get(shop).get(brend).get(models).replace("order", Integer.valueOf(quantity));

        Integer orderCash = remanisSaleShop.get(shop).get(brend).get("total").get("orderCash");
        if (orderCash == null) {
            orderCash = 0;
        }
        Integer remanisShop = phoneRepositoriy.getPhoneRemanMatrix(brend, shop);
        if (remanisShop == null) {
            remanisShop = 0;
        }
        remanisSaleShop.get(shop).get(brend).get("total").replace("orderCash", Integer.valueOf(quantity) + orderCash);

        updateMatrixT2(remanisShop + Integer.valueOf(quantity) + orderCash, shop, brend);

        Integer remanisCash2 = remanisSaleShop.get(shop).get(brend).get(models).get("remanisCash2");
        Integer remanisCash = remanisSaleShop.get(shop).get(brend).get(models).get("remanisCash");

        Integer totalRemanisCash2 = remanisSaleShop.get(shop).get(brend).get("total").get("totalRemanisCash2");
        Integer totalRemanisCash = remanisSaleShop.get(shop).get(brend).get("total").get("totalRemanisCash");

        System.out.println(totalRemanisCash2);

        if (remanisCash2 == null) {
            remanisCash2 = 0;
        }
        if (remanisCash == null) {
            remanisCash = 0;
        }
        if (totalRemanisCash2 == null) {
            totalRemanisCash2 = 0;
        }
        if (totalRemanisCash == null) {
            totalRemanisCash = 0;
        }

        if (authorization_tt.getShopMatrixT2().contains(shop) && remanisCash2 > 0) {
            remanisSaleShop.get(authorization_ttList.get(1).getName()).get(brend).get(models).replace("remanis", remanisCash2 - Integer.valueOf(quantity));
            for (Map.Entry entry : remanisSaleShop.entrySet()) {

                remanisSaleShop.get(entry.getKey()).get(brend).get(models).replace("remanisCash2", remanisCash2 - Integer.valueOf(quantity));
            }

        } else {
            remanisSaleShop.get(authorization_ttList.get(0).getName()).get(brend).get(models).replace("remanis", remanisCash - Integer.valueOf(quantity));
            for (Map.Entry entry : remanisSaleShop.entrySet()) {
                remanisSaleShop.get(entry.getKey()).get(brend).get(models).replace("remanisCash", remanisCash - Integer.valueOf(quantity));
            }

        }

        if (authorization_tt.getShopMatrixT2().contains(shop) && remanisCash2 > 0) {
            for (Map.Entry entry : remanisSaleShop.entrySet()) {
                remanisSaleShop.get(entry.getKey()).get(brend).get("total").replace("totalRemanisCash2", totalRemanisCash2 - Integer.valueOf(quantity));
            }

        } else {
            for (Map.Entry entry : remanisSaleShop.entrySet()) {
                remanisSaleShop.get(entry.getKey()).get(brend).get("total").replace("totalRemanisCash", totalRemanisCash - Integer.valueOf(quantity));
            }

        }


        return remanisSaleShop.get(shop);
    }

    private void updateMatrixT2(int i, String shop, String brend) {

        if (authorization_tt.getShopMatrixT2().contains(shop)) {

            Integer kl = matrixT2Repository.getQuantityMatrix(brend, String.valueOf(authorization_tt.getClusterT2(shop).charAt(0)));
            Double remMatr = null;
            if (kl != null) {
                if (kl < i) {
                    remMatr = 100.00;
                } else {
                    remMatr = (double) i / (double) kl * 100;
                }
                shopMatrix.get(shop).replace(brend, String.format("%.0f", remMatr) + "%");

                int cou = 0;
                int matrix = 0;
                for (Map.Entry entry : shopMatrix.get(shop).entrySet()) {


                    if (!entry.getKey().equals("total") && !entry.getValue().equals("ЛОЖЬ%")) {
                        cou++;
                        matrix += Integer.parseInt(entry.getValue().toString().replace("%", ""));

                    }


                }

                shopMatrix.get(shop).replace("total", String.format("%.0f", (double) matrix / (double) cou) + "%");
            }
        }
    }


    public Object updateRemanisSaleMatrixT2Shop(String model) {
        Map<String, Map<String, Integer>> shop = new TreeMap<>();

        for (Authorization_tt shops : authorization_ttList) {
            Map<String, Integer> indicators = new TreeMap<>();
            indicators.put("remanis", phoneRepositoriy.getPhoneRemanMatrix(model, shops.getName()));
            indicators.put("sale1", phoneRepositoriy.getPhoneSale1Matrix(model, shops.getName()));
            indicators.put("sale6", phoneRepositoriy.getPhoneSale6Matrix(model, shops.getName()));
            shop.put(shops.getName(), indicators);
        }

        return shop;
    }

    public Object updateRemanisSaleModelShop(String model) {
        Map<String, Map<String, Integer>> shop = new TreeMap<>();

        List<String> modelGb = phoneRepositoriy.getPhonaModelGb(model);
        System.out.println(modelGb);
        for (Authorization_tt shops : authorization_ttList) {
            Map<String, Integer> indicators = new TreeMap<>();
            indicators.put("remanis", phoneRepositoriy.getRemanisModelGbShop(modelGb, shops.getName()));
            indicators.put("sale1", phoneRepositoriy.getSale1DistrModelGb(modelGb, shops.getName()));
            indicators.put("sale6", phoneRepositoriy.getSale6DistrModelGb(modelGb, shops.getName()));
            shop.put(shops.getName(), indicators);
        }

        return shop;
    }
}
