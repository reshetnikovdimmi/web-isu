package com.myisu_1.isu.service;


import com.myisu_1.isu.models.Phone.DistributionPhone;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class PhoneServise {
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
    public List<authorization_tt> authorization_ttList;
    List<Phone_Smart> phoneSmartList;
    List<RemanisSim> remanisSimList;
    Map<String, Map<String, Map<String, Map<String, Integer>>>> remanisSaleShop;
    List<DistributionPhone> distributionPhoneList;
    List<String> matrix_T2;
    List<String> distributionModelMatrix;

    private int remanis;


    public Map<String, Map<String, Integer>> distributionModel() {
        authorization_ttList = (List<com.myisu_1.isu.models.authorization_tt>) authorization_tt.findAll();
        matrix_T2 = phoneRepositoriy.getMatrixT2Disting();
        remanisSaleShop = new TreeMap<>();
        Map<String, Map<String, Integer>> distributionModel = new TreeMap<>();

        for (String matrix : matrix_T2) {

            Map<String, Integer> indicator = new TreeMap<>();

            indicator.put("remanis", phoneRepositoriy.getPhoneRemanisSum(matrix));
            indicator.put("sale1", phoneRepositoriy.getPhoneSale1Sum(matrix));
            indicator.put("sale6", phoneRepositoriy.getPhoneSale6Sum(matrix));
            indicator.put("remanisSach", phoneRepositoriy.getPhoneRemanSachAll(matrix, authorization_ttList.get(0).getName(), authorization_ttList.get(2).getName()));
            distributionModel.put(matrix, indicator);
        }
        return distributionModel;
    }


    public Iterable<DistributionPhone> distributionPhoneList() {


        return distributionPhoneList;
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
                    if (remanisSaleShop.containsKey(authorization_ttList.get(2).getName())) {
                        indicator.put("remanisCash2", remanisSaleShop.get(authorization_ttList.get(2).getName()).get(matrix).get(models).get("remanisCash2"));
                    } else {
                        indicator.put("remanisCash2", remanisSimRepository.getRemanisSimShop(models, authorization_ttList.get(2).getName()));
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
                if (remanisSaleShop.containsKey(authorization_ttList.get(2).getName())) {
                    indicator.put("totalRemanisCash2", remanisSaleShop.get(authorization_ttList.get(2).getName()).get(matrix).get("total").get("totalRemanisCash2"));
                } else {
                    indicator.put("totalRemanisCash2", phoneRepositoriy.getPhoneRemanMatrix(matrix, authorization_ttList.get(2).getName()));
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

    public Object remanisPhoneSach(String matrixT2) {

        Map<String, Map<String, Integer>> remanisCash = new TreeMap<>();
        int total = 0;
        int total2 = 0;
        for (String model : phoneRepositoriy.getModelMatrixT2List(matrixT2)) {
            Map<String, Integer> remanis = new TreeMap<>();
            Integer rem;
            Integer rem2;

            rem = remanisSaleShop(authorization_ttList.get(0).getName()).get(matrixT2).get(model).get("remanis");
            rem2 = remanisSaleShop(authorization_ttList.get(2).getName()).get(matrixT2).get(model).get("remanis");

            if (rem != null && rem > 0 || rem2 != null && rem2 > 0) {
                remanis.put("Cash", rem);
                remanis.put("Cash2", rem2);
                if (rem != null) {
                    total += rem;
                }
                if (rem2 != null) {
                    total2 += rem2;
                }
            }
            if (!remanis.isEmpty()) {
                remanisCash.put(model, remanis);
            }

        }
        Map<String, Integer> remanis = new TreeMap<>();
        remanis.put("totalCash", total);
        remanis.put("totalCash2", total2);
        remanisCash.put("total", remanis);

        return remanisCash;
    }

    public Map<String, Map<String, Integer>> remanisPhoneShopT2() {

        return indicatorPhoneShop(authorization_tt.getShopT2());
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

    public Map<String, Map<String, Integer>> remanisPhoneShopMult() {

        return indicatorPhoneShop(authorization_tt.getShopMult());
    }

    public Object distributionModelMatrix() {
        distributionModelMatrix = matrixT2Repository.getDistributionModelMatrixDisting();

        return distributionModelMatrix;
    }

    public Object createMatrixT2() {
        Map<String, Map<String, String>> shopMatrix = new TreeMap<>();


        for (String shop : authorization_tt.getShopMatrixT2()) {
            Map<String, String> indicator = new TreeMap<>();
            int cou = 0;
            Double total = 0.0;
            for (String matrix : distributionModelMatrix) {
                String proz = null;
                Integer remMatr = phoneRepositoriy.getPhoneRemanMatrix(matrix, shop);

                Integer kl = matrixT2Repository.getQuantityMatrix(matrix, String.valueOf(authorization_tt.getClusterT2(shop).charAt(0)));
                if (remMatr == null) {
                    remMatr = 0;
                }
                if (remMatr != null && kl != 0) {
                    proz = String.format("%.0f", (double) remMatr / (double) kl * 100);
                    if (Double.parseDouble(proz) > 100) {
                        proz = "100";
                    }

                }
                if (remMatr != null && kl == 0) {
                    proz = "ЛОЖЬ";
                }
                if (kl > 0) {
                    total += Double.parseDouble(proz);
                    cou++;
                }

                indicator.put(matrix, proz + "%");
            }
            indicator.put("total", String.format("%.0f", total / cou) + "%");
            shopMatrix.put(shop, indicator);

        }

        return shopMatrix;
    }

    public Map<String, Map<String, Map<String, Integer>>> tableUpDistriPhone(String shop, String models, String quantity, String brend) {
        System.out.println(shop + "--" + models + "--" + quantity + "--" + brend);

        remanisSaleShop.get(shop).get(brend).get(models).replace("order", Integer.valueOf(quantity));

        Integer orderCash = remanisSaleShop.get(shop).get(brend).get("total").get("orderCash");
        if (orderCash == null) {
            orderCash = 0;
        }
        remanisSaleShop.get(shop).get(brend).get("total").replace("orderCash", Integer.valueOf(quantity) + orderCash);

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
            remanisSaleShop.get(authorization_ttList.get(2).getName()).get(brend).get(models).replace("remanis", remanisCash2 - Integer.valueOf(quantity));
            for (Map.Entry entry : remanisSaleShop.entrySet()) {

                remanisSaleShop.get(entry.getKey()).get(brend).get(models).replace("remanisCash2", remanisCash2 - Integer.valueOf(quantity));
            }

        } else {
            remanisSaleShop.get(authorization_ttList.get(0).getName()).get(brend).get(models).replace("remanis", remanisCash - Integer.valueOf(quantity));
            for (Map.Entry entry : remanisSaleShop.entrySet()) {
                remanisSaleShop.get(entry.getKey()).get(brend).get(models).replace("remanisCash", remanisCash - Integer.valueOf(quantity));
            }

        }

        if (authorization_tt.getShopMatrixT2().contains(shop) && totalRemanisCash2 > 0) {
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

    public Object updateRemanisSaleMatrixT2Shop(String model) {
        Map<String, Map<String, Integer>> shop = new TreeMap<>();
System.out.println(model);
        for (authorization_tt shops: authorization_ttList){
            Map<String, Integer> indicators = new TreeMap<>();
            indicators.put("remanis",phoneRepositoriy.getPhoneRemanMatrix(model, shops.getName()));
            indicators.put("sale1", phoneRepositoriy.getPhoneSale1Matrix(model, shops.getName()));
            indicators.put("sale6", phoneRepositoriy.getPhoneSale6Matrix(model, shops.getName()));
            shop.put(shops.getName(),indicators);
        }

        return shop;
    }

    public Object updateRemanisSaleModelShop(String model) {
        Map<String, Map<String, Integer>> shop = new TreeMap<>();

        List<String> modelGb = phoneRepositoriy.getPhonaModelGb(model);
        System.out.println(modelGb);
        for (authorization_tt shops: authorization_ttList){
            Map<String, Integer> indicators = new TreeMap<>();
            indicators.put("remanis",phoneRepositoriy.getRemanisModelGbShop(modelGb, shops.getName()));
            indicators.put("sale1", phoneRepositoriy.getSale1DistrModelGb(modelGb, shops.getName()));
            indicators.put("sale6", phoneRepositoriy.getSale6DistrModelGb(modelGb, shops.getName()));
            shop.put(shops.getName(),indicators);
        }

        return shop;
    }
}
