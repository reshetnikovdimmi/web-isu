package com.myisu_1.isu.service;


import com.myisu_1.isu.models.Phone.*;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.SaleSim_1m;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PhoneServise {
    @Autowired
    private SalesRepositoriy salesRepositoriy;
    @Autowired
    private PostRepositoriy authorization_tt;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private RemanisSimRepository remanisSimRepository;
    @Autowired
    private SaleSimModemRepository saleSimModemRepository;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository_1m;
    @Autowired
    private MatrixT2Repository matrixT2Repository;
    @Autowired
    private MatrixSparkRepository matrixSparkRepository;

    List<Sales> sales;
    List<authorization_tt> authorization_ttList;
    List<Phone_Smart> phoneSmartList;
    List<RemanisSim> remanisSimList;
    List<SaleSim_1m> saleSim_1mList;
    List<SaleSim_6m> saleSim_6mList;
    List<MatrixT2> matrixT2List;
    List<MatrixSpark> matrixSparkList;
    List<Assortment> assortmentList;
    List<DistributionPhone> remanisPhoneList;
    List<String> disting = new ArrayList<>();
    HashMap<String, String> distingGB = new HashMap<>();
    List<DistributionPhone> distributionPhoneList;


    public void LoadAuthorization_ttList() {
        authorization_ttList = (List<authorization_tt>) authorization_tt.findAll();
        sales = (List<Sales>) salesRepositoriy.findAll();
        phoneSmartList = phoneRepositoriy.findAll();
        remanisSimList = (List<RemanisSim>) remanisSimRepository.findAll();
        saleSim_1mList = (List<SaleSim_1m>) saleSimModemRepository_1m.findAll();
        saleSim_6mList = (List<SaleSim_6m>) saleSimModemRepository.findAll();
        matrixT2List = matrixT2Repository.findAll();
        matrixSparkList = matrixSparkRepository.findAll();

        Disting();
    }

    private void Disting() {
        assortmentList = new ArrayList<>();
        ArrayList<String> listWithDuplicateValues = new ArrayList<>();
        for (int i = 0; i < phoneSmartList.size(); i++) {
            listWithDuplicateValues.add(phoneSmartList.get(i).getMatrix_T2());
            distingGB.put(phoneSmartList.get(i).getModel_GB(), phoneSmartList.get(i).getMatrix_T2());
        }

        disting = (ArrayList) listWithDuplicateValues
                .stream()
                .distinct()
                .collect(Collectors.toList());

        for (int i = 3; i < authorization_ttList.size() - 1; i++) {

            for (int j = 0; j < disting.size(); j++) {
                int max = 0;
                int saleSim_1m = 0;
                int saleSim_6m = 0;
                int matrixT2 = 0;
                int matrixSpark = 0;

                for (int k = 0; k < phoneSmartList.size(); k++) {

                    for (int z = 0; z < saleSim_1mList.size(); z++) {
                        if (disting.get(j).equals(phoneSmartList.get(k).getMatrix_T2()) && phoneSmartList.get(k).getModel().equals(saleSim_1mList.get(z).getNameSimAndModem()) && authorization_ttList.get(i).getName().equals(saleSim_1mList.get(z).getShop()) || disting.get(j).equals(phoneSmartList.get(k).getMatrix_T2()) && phoneSmartList.get(k).getModel().equals(saleSim_1mList.get(z).getNameSimAndModem()) && authorization_ttList.get(i).getShopIskra().equals(saleSim_1mList.get(z).getShop())) {
                            saleSim_1m = saleSim_1m + saleSim_1mList.get(z).getRemainsSimModem();

                        }
                    }
                    for (int z = 0; z < saleSim_6mList.size(); z++) {

                        if (disting.get(j).equals(phoneSmartList.get(k).getMatrix_T2()) && phoneSmartList.get(k).getModel().equals(saleSim_6mList.get(z).getNameSimAndModem()) && authorization_ttList.get(i).getName().equals(saleSim_6mList.get(z).getShop()) || disting.get(j).equals(phoneSmartList.get(k).getMatrix_T2()) && phoneSmartList.get(k).getModel().equals(saleSim_6mList.get(z).getNameSimAndModem()) && authorization_ttList.get(i).getShopIskra().equals(saleSim_6mList.get(z).getShop())) {
                            saleSim_6m = saleSim_6m + saleSim_6mList.get(z).getRemainsSimModem();

                        }

                    }
                    for (int z = 0; z < matrixT2List.size(); z++) {
                        if (disting.get(j).equals(matrixT2List.get(z).getDistributionModel()) && !authorization_ttList.get(i).getClusterT2().isEmpty() && matrixT2List.get(z).getCluster().equals(String.valueOf(authorization_ttList.get(i).getClusterT2().charAt(0)))) {
                            matrixT2 = Integer.parseInt(matrixT2List.get(z).getQuantity());

                        }

                    }


                }
                for (int z = 0; z < matrixSparkList.size(); z++) {
                    for (Map.Entry entry : distingGB.entrySet()) {
                        if (disting.get(j).equals(entry.getValue()) && entry.getKey().equals(matrixSparkList.get(z).getModel())) {
                            matrixSpark++;
                        }
                    }
                }
                max = Math.max(Math.max(saleSim_1m, saleSim_6m / 6), Math.max(matrixT2, matrixSpark));
                // поменять на искру
                assortmentList.add(new Assortment(authorization_ttList.get(i).getName(), disting.get(j), max));

            }
        }
    }


    public Iterable<RequirementPhone> requirementPhone() {
        remanisPhoneList = new ArrayList<>();
        List<RequirementPhone> requirementPhoneList = new ArrayList<>();

        for (int i = 3; i < authorization_ttList.size() - 1; i++) {
            String shop = authorization_ttList.get(i).getName();
            String shopSpark = authorization_ttList.get(i).getShopIskra();
            String cluster = authorization_ttList.get(i).getClusterT2();
            int remanisPhone = remanisPhone(shop, shopSpark);
            int requirementPhones = requirementPhones(shop, shopSpark, cluster);
            Double representationPhone = representationPhone(shop, shopSpark);
            double percent = Double.valueOf(remanisPhone) / Double.valueOf(requirementPhones);

            if (percent > 1) {
                percent = 1;
            }

            requirementPhoneList.add(new RequirementPhone(
                    shop,
                    cluster,
                    remanisPhone,
                    requirementPhones,
                    representationPhone,
                    Double.parseDouble(String.format("%.2f", percent).replaceAll(",", "."))));
        }
        List sortedUsers = requirementPhoneList
                .stream()
                .sorted(Comparator.comparing(RequirementPhone::getPercent))
                .collect(Collectors.toList());
        return sortedUsers;
    }

    private Double representationPhone(String shop, String shopSpark) {

        int cou = 0;
        Double assortment = 0.0;

        for (int i = 0; i < disting.size(); i++) {

            int sum = 0;
            int sum_1 = 0;
            for (int j = 0; j < phoneSmartList.size(); j++) {
                for (int k = 0; k < remanisSimList.size(); k++) {
                    if (shop.equals(remanisSimList.get(k).getShop()) && disting.get(i).equals(phoneSmartList.get(j).getMatrix_T2()) && phoneSmartList.get(j).getModel().equals(remanisSimList.get(k).getNameSimAndModem())) {
                        sum = sum + remanisSimList.get(k).getRemainsSimModem();

                    }

                }

            }
            for (int j = 0; j < assortmentList.size(); j++) {
                if (shop.equals(assortmentList.get(j).getShop()) && disting.get(i).equals(assortmentList.get(j).getModelPhone())) {
                    if (assortmentList.get(j).getQuantity() != 0) {

                        cou++;
                        sum_1 = sum / assortmentList.get(j).getQuantity();
                        if (sum_1 > 1) {
                            sum_1 = 1;

                        }

                    } else if (sum > assortmentList.get(j).getQuantity()) {
                        cou++;
                        sum_1 = 1;

                    }

                }
            }
            // поменять на искру
            remanisPhoneList.add(new DistributionPhone(shop, disting.get(i), sum, false));

            assortment = assortment + sum_1;
        }

        return Double.parseDouble(String.format("%.2f", assortment / cou).replaceAll(",", "."));
    }

    private Integer requirementPhones(String shop, String shopSpark, String cluster) {

        int sale_1m = 0;
        int sale_6m = 0;
        int min = matrixSparkList.size();
        for (int i = 0; i < phoneSmartList.size(); i++) {
            for (int j = 0; j < saleSim_1mList.size(); j++) {
                if (shop.equals(saleSim_1mList.get(j).getShop()) && phoneSmartList.get(i).getModel().equals(saleSim_1mList.get(j).getNameSimAndModem()) || shopSpark.equals(saleSim_1mList.get(j).getShop()) && phoneSmartList.get(i).getModel().equals(saleSim_1mList.get(j).getNameSimAndModem())) {
                    sale_1m = sale_1m + saleSim_1mList.get(j).getRemainsSimModem();

                }
            }

        }
        for (int i = 0; i < phoneSmartList.size(); i++) {
            for (int j = 0; j < saleSim_6mList.size(); j++) {
                if (shop.equals(saleSim_6mList.get(j).getShop()) && phoneSmartList.get(i).getModel().equals(saleSim_6mList.get(j).getNameSimAndModem()) || shopSpark.equals(saleSim_6mList.get(j).getShop()) && phoneSmartList.get(i).getModel().equals(saleSim_6mList.get(j).getNameSimAndModem())) {
                    sale_6m = sale_6m + saleSim_6mList.get(j).getRemainsSimModem();

                }
            }

        }
        sale_6m = sale_6m / 3;

        return Math.max(sale_1m, Math.max(sale_6m, min));
    }

    private Integer remanisPhone(String shop, String shopSpark) {

        int quantity = 0;
        for (int i = 0; i < phoneSmartList.size(); i++) {
            for (int j = 0; j < remanisSimList.size(); j++) {
                if (shop.equals(remanisSimList.get(j).getShop()) && phoneSmartList.get(i).getModel().equals(remanisSimList.get(j).getNameSimAndModem()) || shopSpark.equals(remanisSimList.get(j).getShop()) && phoneSmartList.get(i).getModel().equals(remanisSimList.get(j).getNameSimAndModem())) {

                    quantity = quantity + remanisSimList.get(j).getRemainsSimModem();
                }

            }
        }

        return quantity;
    }

    public Iterable<RemanisPhoneWarehouse> remanisWarehousePhone() {
        List<RemanisPhoneWarehouse> remanisPhoneWarehouseList = new ArrayList<>();
        for (int i = 0; i < remanisSimList.size(); i++) {
            for (int j = 0; j < phoneSmartList.size(); j++) {
                if (remanisSimList.get(i).getShop().equals(authorization_ttList.get(0).getName()) && phoneSmartList.get(j).getModel().equals(remanisSimList.get(i).getNameSimAndModem()) || remanisSimList.get(i).getShop().equals(authorization_ttList.get(2).getName()) && phoneSmartList.get(j).getModel().equals(remanisSimList.get(i).getNameSimAndModem())) {
                    RemanisPhoneWarehouse remanisPhoneWarehouse = new RemanisPhoneWarehouse();
                    if (remanisSimList.get(i).getShop().equals(authorization_ttList.get(0).getName())) {
                        remanisPhoneWarehouse.setModelPhone(remanisSimList.get(i).getNameSimAndModem());
                        remanisPhoneWarehouse.setRemanisMainWarehouse(remanisSimList.get(i).getRemainsSimModem());
                    }
                    if (remanisSimList.get(i).getShop().equals(authorization_ttList.get(2).getName())) {
                        remanisPhoneWarehouse.setModelPhone(remanisSimList.get(i).getNameSimAndModem());
                        remanisPhoneWarehouse.setRemanisWarehouseT2(remanisSimList.get(i).getRemainsSimModem());

                    }
                    remanisPhoneWarehouseList.add(remanisPhoneWarehouse);
                }
                if (remanisSimList.get(i).getShop().equals(authorization_ttList.get(0).getShopIskra()) && phoneSmartList.get(j).getModel().equals(remanisSimList.get(i).getNameSimAndModem()) || remanisSimList.get(i).getShop().equals(authorization_ttList.get(2).getShopIskra()) && phoneSmartList.get(j).getModel().equals(remanisSimList.get(i).getNameSimAndModem())) {
                    RemanisPhoneWarehouse remanisPhoneWarehouse = new RemanisPhoneWarehouse();
                    if (remanisSimList.get(i).getShop().equals(authorization_ttList.get(0).getName())) {
                        remanisPhoneWarehouse.setModelPhone(remanisSimList.get(i).getNameSimAndModem());
                        remanisPhoneWarehouse.setRemanisMainWarehouse(remanisSimList.get(i).getRemainsSimModem());
                    }
                    if (remanisSimList.get(i).getShop().equals(authorization_ttList.get(2).getName())) {
                        remanisPhoneWarehouse.setModelPhone(remanisSimList.get(i).getNameSimAndModem());
                        remanisPhoneWarehouse.setRemanisWarehouseT2(remanisSimList.get(i).getRemainsSimModem());

                    }
                    remanisPhoneWarehouseList.add(remanisPhoneWarehouse);
                }
            }
        }

        return remanisPhoneWarehouseList;
    }

    public Iterable<DistributionPhone> distributionPhone(String shop) {
        if (distributionPhoneList != null) {
            distributionPhoneList.clear();
        }

        distributionPhoneList = new ArrayList<>();

        for (int i = 0; i < assortmentList.size(); i++) {
            for (int j = 0; j < remanisPhoneList.size(); j++) {
                if (shop.equals(assortmentList.get(i).getShop()) && assortmentList.get(i).getShop().equals(remanisPhoneList.get(j).getShop()) && assortmentList.get(i).getModelPhone().equals(remanisPhoneList.get(j).getModelPhone())) {
                    DistributionPhone distributionPhone = new DistributionPhone();
                    distributionPhone.setShop(assortmentList.get(i).getShop());
                    distributionPhone.setModelPhone(assortmentList.get(i).getModelPhone());
                    distributionPhone.setSkyPhone(assortmentList.get(i).getQuantity());
                    distributionPhone.setRemanisPhone(remanisPhoneList.get(j).getRemanisPhone());
                    distributionPhone.setSky(true);
                    distributionPhoneList.add(distributionPhone);

                }
            }
            for (int j = 0; j < phoneSmartList.size(); j++) {
                if (assortmentList.get(i).getModelPhone().equals(phoneSmartList.get(j).getMatrix_T2()) && assortmentList.get(i).getShop().equals(shop)) {
                    DistributionPhone distributionPhone = new DistributionPhone();
                    distributionPhone.setShop(shop);
                    distributionPhone.setModelPhone(phoneSmartList.get(j).getModel());
                    distributionPhone.setSky(false);
                    for (int k = 0; k < remanisSimList.size(); k++) {
                        if (shop.equals(remanisSimList.get(k).getShop()) && phoneSmartList.get(j).getModel().equals(remanisSimList.get(k).getNameSimAndModem())) {
                            distributionPhone.setRemanisPhone(remanisSimList.get(k).getRemainsSimModem());
                        }
                    }
                    distributionPhoneList.add(distributionPhone);
                }
            }
        }
        System.out.println(assortmentList.size() + "<-->" + remanisPhoneList.size());
        return distributionPhoneList;

    }

    public Iterable<TableMatrixT2> matrixT2Phone() {
        List<TableMatrixT2> tableMatrixT2List = new ArrayList<>();
        List<String> distingMatrixT2 = new ArrayList<>();
        ArrayList<String> listWithDuplicate = new ArrayList<>();
        for (int i = 0; i < matrixT2List.size(); i++) {
            listWithDuplicate.add(matrixT2List.get(i).getDistributionModel());

        }

        distingMatrixT2 = (ArrayList) listWithDuplicate
                .stream()
                .distinct()
                .collect(Collectors.toList());

        for (int l = 0; l < authorization_ttList.size(); l++) {
            if (!authorization_ttList.get(l).getClusterT2().isEmpty()) {
                for (int k = 0; k < distingMatrixT2.size(); k++) {
                    String remanisSum ="0";
                for (int i = 0; i < phoneSmartList.size(); i++) {

                        for (int j = 0; j < remanisSimList.size(); j++) {

                            if (distingMatrixT2.get(k).equals(phoneSmartList.get(i).getMatrix_T2()) && authorization_ttList.get(l).getName().equals(remanisSimList.get(j).getShop()) && phoneSmartList.get(i).getModel().equals(remanisSimList.get(j).getNameSimAndModem())) {

                                remanisSum = String.valueOf(Integer.valueOf(remanisSum) + remanisSimList.get(j).getRemainsSimModem());


                            }

                        }



                    }
                for(int s=0;s<matrixT2List.size();s++){

                    if (matrixT2List.get(s).getDistributionModel().equals(distingMatrixT2.get(k))&& matrixT2List.get(s).getCluster().equals(String.valueOf(authorization_ttList.get(l).getClusterT2().charAt(0)))){

                        if(matrixT2List.get(s).getQuantity().equals("0") ){
                            remanisSum = "ЛОЖЬ";
                        }else  if (Integer.parseInt(matrixT2List.get(s).getQuantity())<=Integer.valueOf(remanisSum)){
                            remanisSum = "100%";
                        }else{
                            remanisSum = String.format("%.2f", Double.parseDouble(remanisSum) / Double.parseDouble(matrixT2List.get(s).getQuantity()));
                        }

                    }

                }
                    System.out.println(authorization_ttList.get(l).getName() +"<-->"+ distingMatrixT2.get(k) +"<-->"+remanisSum);
                    tableMatrixT2List.add(new TableMatrixT2(authorization_ttList.get(l).getName(),distingMatrixT2.get(k),remanisSum));


                }

            }
        }
        return tableMatrixT2List;
    }
}
