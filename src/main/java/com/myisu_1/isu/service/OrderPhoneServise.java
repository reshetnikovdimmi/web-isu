package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Phone.DistributionPhone;
import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.models.Phone.MatrixT2;
import com.myisu_1.isu.models.Phone.OrderPhone;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.MatrixSparkRepository;
import com.myisu_1.isu.repo.MatrixT2Repository;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.SalesRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderPhoneServise extends MovementsPhoneServise {
    @Autowired
    private MatrixSparkRepository matrixSparkRepository;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private SalesRepositoriy salesRepositoriy;
    @Autowired
    private MatrixT2Repository matrixT2Repository;

    List<OrderPhone> orderPhoneList;
    List<OrderPhone> orderPhoneMatrixT2List;
    List<MatrixSpark> matrixSparkList;
    List<String> distinctMatrix_T2;
    List<String> distingShop;
    HashMap<String, String> sale;


    public List<OrderPhone> orderFromT2Warehouse() {
        distinctMatrix_T2 = distinctMatrix_T2();
        orderPhoneMatrixT2List = new ArrayList<>();
        orderPhoneList = new ArrayList<>();
        matrixSparkList = matrixSparkTableUpdate();
        distingShop = new ArrayList<>();
        distributionPhoneList = new ArrayList<>();
      //  List<MatrixT2> matrixT2List = matrixT2Repository.findAll();
        for (int i = 0; i < authorization_ttList.size(); i++) {
            if (!authorization_ttList.get(i).getClusterT2().isEmpty()) {
                distributionsPhoneList(authorization_ttList.get(i).getName());
                distingShop.add(authorization_ttList.get(i).getName());
            }

        }
        saleall(distingShop);
        for (int j = 0; j < distinctMatrix_T2.size(); j++) {
            int quantity = 0;
            for (int i = 0; i < distributionPhoneList.size(); i++) {
                if (distinctMatrix_T2.get(j).equals(distributionPhoneList.get(i).getModelPhone()) && distributionPhoneList.get(i).getSkyPhone() > distributionPhoneList.get(i).getRemanisPhone()) {
                    quantity = quantity + distributionPhoneList.get(i).getRemanisPhone() - distributionPhoneList.get(i).getSkyPhone();

                }

            }
            orderPhoneList.add(new OrderPhone(distinctMatrix_T2.get(j), String.valueOf(quantity), salePhone(matrixSparkList, distinctMatrix_T2.get(j))));
        }


        return orderPhoneList;
    }

    public List<OrderPhone> orderFromMatrixT2Warehouse() {

        orderPhoneMatrixT2List = new ArrayList<>();
        orderPhoneList = new ArrayList<>();
        matrixSparkList = matrixSparkTableUpdate();
        distingShop = new ArrayList<>();
        distributionPhoneList = new ArrayList<>();
        List<MatrixT2> matrixT2List = matrixT2Repository.findAll();
        List<String> distingMatrixT2List = new ArrayList<>();
        List<String> distingMatrixT2;
        for (int i = 0; i < authorization_ttList.size(); i++) {
            if (!authorization_ttList.get(i).getClusterT2().isEmpty()) {
                distributionsPhoneList(authorization_ttList.get(i).getName());
                distingShop.add(authorization_ttList.get(i).getName());
            }

        }
        for (int i = 0; i < matrixT2List.size(); i++) {

            distingMatrixT2List.add(matrixT2List.get(i).getDistributionModel());

        }
        distingMatrixT2 = distingMatrixT2List
                .stream()
                .distinct()
                .collect(Collectors.toList());


        saleall(distingShop);
        for (int j = 0; j < distingMatrixT2.size(); j++) {

            int quantity = 0;
            for (int i = 0; i < distributionPhoneList.size(); i++) {
                for (int k = 0; k < authorization_ttList.size(); k++) {
                    if (distingMatrixT2.get(j).equals(distributionPhoneList.get(i).getModelPhone())&&authorization_ttList.get(k).getName().equals(distributionPhoneList.get(i).getShop())) {
                        quantity = quantity + orderInMatrixT2(matrixT2List, authorization_ttList.get(k),distributionPhoneList.get(i),distingMatrixT2.get(j));
                       }
                }

            }

            orderPhoneList.add(new OrderPhone(distingMatrixT2.get(j), String.valueOf(quantity), salePhone(matrixSparkList, distingMatrixT2.get(j))));
        }


        return orderPhoneList;
    }

    private int orderInMatrixT2(List<MatrixT2> matrixT2List, com.myisu_1.isu.models.authorization_tt authorization_tt, DistributionPhone distributionPhone, String s) {
        int quantity = 0;
        for (int i =0;i<matrixT2List.size();i++){
            if(!authorization_tt.getClusterT2().isEmpty()&& matrixT2List.get(i).getDistributionModel().equals(s) && matrixT2List.get(i).getCluster().equals(String.valueOf(authorization_tt.getClusterT2().charAt(0))) && distributionPhone.getRemanisPhone()<Integer.parseInt(matrixT2List.get(i).getQuantity())){
                quantity =Integer.parseInt(matrixT2List.get(i).getQuantity()) - distributionPhone.getRemanisPhone();

            }

        }

        return quantity;
    }

    private void saleall(List<String> distingShop) {
        long start = System.currentTimeMillis();
        sale = new HashMap<>();


        List<String> model_GB = new ArrayList<>();
        for (int i = 0; i < phoneSmartList.size(); i++) {


            model_GB.add(phoneSmartList.get(i).getModel_GB());


        }

        List<String> distinctModel_GB = distinctModel_GB(model_GB);

        for (int k = 0; k < distinctModel_GB.size(); k++) {

            int remanis = 0;
            for (int i = 0; i < distingShop.size(); i++) {

                for (int j = 0; j < remanisSimList.size(); j++) {
                    for (int z = 0; z < phoneSmartList.size(); z++) {
                        if (distingShop.get(i).equals(remanisSimList.get(j).getShop()) && phoneSmartList.get(z).getModel_GB().equals(distinctModel_GB.get(k)) && phoneSmartList.get(z).getModel().equals(remanisSimList.get(j).getNameSimAndModem())) {
                            remanis = remanis + remanisSimList.get(j).getRemainsSimModem();


                        }


                    }


                }


            }

            sale.put(distinctModel_GB.get(k), String.valueOf(remanis));
        }
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        System.out.println(df.format(new Date(timeWorkCode)) + "--" + "saleall");

    }

    public List<OrderPhone> orderFromAllWarehouse() {
        distinctMatrix_T2 = distinctMatrix_T2();
        orderPhoneList = new ArrayList<>();
        matrixSparkList = matrixSparkTableUpdate();
        distingShop = new ArrayList<>();
        distributionPhoneList = new ArrayList<>();
        for (int i = 2; i < authorization_ttList.size(); i++) {

            distributionsPhoneList(authorization_ttList.get(i).getName());
            distingShop.add(authorization_ttList.get(i).getName());


        }
        saleall(distingShop);
        for (int j = 0; j < distinctMatrix_T2.size(); j++) {
            int quantity = 0;
            for (int i = 0; i < distributionPhoneList.size(); i++) {
                if (distinctMatrix_T2.get(j).equals(distributionPhoneList.get(i).getModelPhone()) && distributionPhoneList.get(i).getSkyPhone() > distributionPhoneList.get(i).getRemanisPhone()) {
                    quantity = quantity + distributionPhoneList.get(i).getRemanisPhone() - distributionPhoneList.get(i).getSkyPhone();

                }

            }
            orderPhoneList.add(new OrderPhone(distinctMatrix_T2.get(j), String.valueOf(quantity), salePhone(matrixSparkList, distinctMatrix_T2.get(j))));
        }


        return orderPhoneList;
    }

    public List<OrderPhone> orderFromWarehouse() {
        distinctMatrix_T2 = distinctMatrix_T2();
        orderPhoneList = new ArrayList<>();
        matrixSparkList = matrixSparkTableUpdate();
        distingShop = new ArrayList<>();
        distributionPhoneList = new ArrayList<>();

        for (int i = 2; i < authorization_ttList.size(); i++) {
            if (authorization_ttList.get(i).getClusterT2().isEmpty()) {
                distributionsPhoneList(authorization_ttList.get(i).getName());
                distingShop.add(authorization_ttList.get(i).getName());

            }

        }
        saleall(distingShop);
        for (int j = 0; j < distinctMatrix_T2.size(); j++) {
            int quantity = 0;
            for (int i = 0; i < distributionPhoneList.size(); i++) {
                if (distinctMatrix_T2.get(j).equals(distributionPhoneList.get(i).getModelPhone()) && distributionPhoneList.get(i).getSkyPhone() > distributionPhoneList.get(i).getRemanisPhone()) {
                    quantity = quantity + distributionPhoneList.get(i).getRemanisPhone() - distributionPhoneList.get(i).getSkyPhone();
                }

            }
            orderPhoneList.add(new OrderPhone(distinctMatrix_T2.get(j), String.valueOf(quantity), salePhone(matrixSparkList, distinctMatrix_T2.get(j))));
        }


        return orderPhoneList;
    }

    private HashMap<String, String[]> salePhone(List<MatrixSpark> matrixSparkList, String s) {

        List<String> distinctModel_GB;

        String[] list;
        HashMap<String, String[]> salePhone = new HashMap<>();
        List<String> model_GB = new ArrayList<>();
        for (int i = 0; i < phoneSmartList.size(); i++) {

            if (s.equals(phoneSmartList.get(i).getMatrix_T2())) {

                model_GB.add(phoneSmartList.get(i).getModel_GB());

            }
        }

        distinctModel_GB = distinctModel_GB(model_GB);

        for (int i = 0; i < matrixSparkList.size(); i++) {

            for (int j = 0; j < distinctModel_GB.size(); j++) {
                if (matrixSparkList.get(i).getModel().equals(distinctModel_GB.get(j))) {
                    list = new String[2];
                    list[0] = matrixSparkList.get(i).getSale();
                    list[1] = remanisList(distinctModel_GB.get(j));
                    salePhone.put(matrixSparkList.get(i).getModel(), list);
                }
            }
        }

        return salePhone;
    }

    private String remanisList(String s) {


        return sale.get(s);
    }


    public List<MatrixSpark> matrixSparkTableUpdate() {
        List<MatrixSpark> matrixSparkList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<Sales> matrixSpark = (List<Sales>) salesRepositoriy.findAll();
        List<Phone_Smart> phoneSmartList = phoneRepositoriy.findAll();

        HashSet<String> hashDistinct = new HashSet<>();
        for (int i = 0; i < phoneSmartList.size(); i++) {
            hashDistinct.add(phoneSmartList.get(i).getModel_GB());
        }


        for (int i = 0; i < matrixSpark.size(); i++) {
            list.add(matrixSpark.get(i).getNomenclature());
        }

        Map<String, Long> frequency =
                list.stream().collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()));

        Iterator<String> i = hashDistinct.iterator();
        while (i.hasNext()) {
            long summ = 0;
            String iter = i.next();

            for (int j = 0; j < phoneSmartList.size(); j++) {
                for (Map.Entry<String, Long> item : frequency.entrySet()) {
                    if (phoneSmartList.get(j).getModel().equals(item.getKey()) && iter.equals(phoneSmartList.get(j).getModel_GB())) {
                        summ = summ + item.getValue();
                    }
                }
            }
            matrixSparkList.add(new MatrixSpark(iter, String.valueOf(summ)));
        }
        Collections.sort(matrixSparkList, new Comparator<MatrixSpark>() {
            @Override
            public int compare(MatrixSpark o1, MatrixSpark o2) {
                return Integer.parseInt(o2.getSale()) - Integer.parseInt(o1.getSale());
            }

        });

        return matrixSparkList;
    }

    private List<String> distinctModel_GB(List<String> model_GB) {
        List<String> distingodel_GB;


        distingodel_GB = model_GB
                .stream()
                .distinct()
                .collect(Collectors.toList());

        return distingodel_GB;
    }

    private List<String> distinctMatrix_T2() {
        List<String> distingMatrixT2;
        ArrayList<String> listWithDuplicate = new ArrayList<>();
        for (int i = 0; i < phoneSmartList.size(); i++) {
            listWithDuplicate.add(phoneSmartList.get(i).getMatrix_T2());

        }

        distingMatrixT2 = listWithDuplicate
                .stream()
                .distinct()
                .collect(Collectors.toList());

        return distingMatrixT2;
    }


    public List<OrderPhone> orderFromMinT2Warehouse() {
        allMovements();
        requirementPhone();

        return orderFromT2Warehouse();
    }
}

