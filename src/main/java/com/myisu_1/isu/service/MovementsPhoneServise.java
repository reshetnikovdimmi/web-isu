package com.myisu_1.isu.service;


import com.myisu_1.isu.models.Phone.DistributionPhone;
import com.myisu_1.isu.models.Phone.From_whereTo_where;
import com.myisu_1.isu.models.Phone.TableMatrixT2;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.authorization_tt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class MovementsPhoneServise extends PhoneServise {
    String klusterT2 = null;
    List<String> listPhone = null;
    List<From_whereTo_where> from_whereTo_whereList;

    public List<From_whereTo_where> allMovements() {
        from_whereTo_whereList = new ArrayList<>();
        for (int i = 0; i < authorization_ttList.size(); i++) {
            distributionsPhoneList(authorization_ttList.get(i).getName());
        }
        for (int i = 0; i < authorization_ttList.size(); i++) {

            redistributingPhones(authorization_ttList.get(i).getName());
        }
        for (int i = 0; i < authorization_ttList.size(); i++) {

            redistributingPhonesтNeT2(authorization_ttList.get(i).getName());
        }
        return from_whereTo_whereList;
    }

    private void redistributingPhonesтNeT2(String name) {
        int requirement = 0;

        if (!checkingForT2(name)) {
            for (int i = 0; i < distributionPhoneList.size(); i++) {
                if (name.equals(distributionPhoneList.get(i).getShop()) && distributionPhoneList.get(i).isSky() && distributionPhoneList.get(i).getRemanisPhone() < distributionPhoneList.get(i).getSkyPhone() && distributionPhoneList.get(i).getRemanisPhone() > 0) {
                    if (klusterT2 != null) {
                        //  System.out.println(name + "--" + klusterT2 + "--" + listPhone);
                        searchPhoneNeT2(requirement, klusterT2, listPhone, name);
                    }
                    klusterT2 = distributionPhoneList.get(i).getModelPhone();
                    requirement = distributionPhoneList.get(i).getSkyPhone() - distributionPhoneList.get(i).getRemanisPhone();
                    listPhone = new ArrayList<>();
                }
                for (int j = 0; j < phoneSmartList.size(); j++) {
                    if (klusterT2 != null && klusterT2.equals(phoneSmartList.get(j).getMatrix_T2()) && name.equals(distributionPhoneList.get(i).getShop()) && phoneSmartList.get(j).getModel().equals(distributionPhoneList.get(i).getModelPhone()) && distributionPhoneList.get(i).getRemanisPhone() == 0) {
                        listPhone.add(distributionPhoneList.get(i).getModelPhone());
                    }
                }
            }
        }
    }

    private void searchPhoneNeT2(int requirement, String klusterT2, List<String> listPhone, String name) {

        Collections.shuffle(listPhone);

        int i1 = requirement;
        int vv = 0;

        for (int i = 0; i < authorization_ttList.size(); i++) {
            boolean requirements = false;

            if (!checkingForT2(authorization_ttList.get(i).getName())) {
                for (int j = 0; j < distributionPhoneList.size(); j++) {
                    if (i1 > 0 && authorization_ttList.get(i).getName().equals(distributionPhoneList.get(j).getShop()) && klusterT2.equals(distributionPhoneList.get(j).getModelPhone()) && distributionPhoneList.get(j).getRemanisPhone() > distributionPhoneList.get(j).getSkyPhone()) {
                        vv = distributionPhoneList.get(j).getRemanisPhone() - distributionPhoneList.get(j).getSkyPhone();
                        requirements = true;
                        //  System.out.println(authorization_ttList.get(i).getName() + "--" + klusterT2 + "--" + i1 + "--" + vv);
                    }
                    if (requirements) {
                        for (int k = 0; k < listPhone.size(); k++) {

                            if (vv > 0 && i1 > 0 && authorization_ttList.get(i).getName().equals(distributionPhoneList.get(j).getShop()) && distributionPhoneList.get(j).getModelPhone().equals(listPhone.get(k)) && distributionPhoneList.get(j).getRemanisPhone() > 0) {
                                vv = vv - 1;
                                i1 = i1 - 1;
                                from_whereTo_whereList.add(new From_whereTo_where(name, listPhone.get(k), distributionPhoneList.get(j).getShop()));

                                distributionPhoneList.set(j, new DistributionPhone(distributionPhoneList.get(j).getShop(), listPhone.get(k), distributionPhoneList.get(j).getSkyPhone(), distributionPhoneList.get(j).getRemanisPhone() - 1, false));
                                updateDistributionPhoneList(name, distributionPhoneList.get(j).getShop(), klusterT2, listPhone.get(k));
                                listPhone.remove(k);
                            }

                        }
                    }
                }

            }
        }

    }

    private void redistributingPhones(String name) {

        int requirement = 0;

        if (checkingForT2(name)) {
            for (int i = 0; i < distributionPhoneList.size(); i++) {
                if (name.equals(distributionPhoneList.get(i).getShop()) && distributionPhoneList.get(i).isSky() && distributionPhoneList.get(i).getRemanisPhone() < distributionPhoneList.get(i).getSkyPhone() && distributionPhoneList.get(i).getRemanisPhone() > 0) {
                    if (klusterT2 != null) {
                         // System.out.println(name + "--" + klusterT2 + "--" + listPhone);
                        searchPhone(requirement, klusterT2, listPhone, name);
                    }
                    klusterT2 = distributionPhoneList.get(i).getModelPhone();
                    requirement = distributionPhoneList.get(i).getSkyPhone() - distributionPhoneList.get(i).getRemanisPhone();
                    listPhone = new ArrayList<>();
                }
                for (int j = 0; j < phoneSmartList.size(); j++) {
                    if (klusterT2 != null && klusterT2.equals(phoneSmartList.get(j).getMatrix_T2()) && name.equals(distributionPhoneList.get(i).getShop()) && phoneSmartList.get(j).getModel().equals(distributionPhoneList.get(i).getModelPhone()) && distributionPhoneList.get(i).getRemanisPhone() == 0) {
                        listPhone.add(distributionPhoneList.get(i).getModelPhone());
                    }
                }
            }
        }
    }

    private void searchPhone(int requirement, String klusterT2, List<String> listPhone, String name) {

        Collections.shuffle(listPhone);


        int i1 = requirement;
        int vv = 0;
        for (int i = 0; i < authorization_ttList.size(); i++) {
            boolean requirements = false;

            if (checkingForT2(authorization_ttList.get(i).getName())) {
                for (int j = 0; j < distributionPhoneList.size(); j++) {
                    if (i1 > 0 && authorization_ttList.get(i).getName().equals(distributionPhoneList.get(j).getShop()) && klusterT2.equals(distributionPhoneList.get(j).getModelPhone()) && distributionPhoneList.get(j).getRemanisPhone() > distributionPhoneList.get(j).getSkyPhone()) {
                        vv = distributionPhoneList.get(j).getRemanisPhone() - distributionPhoneList.get(j).getSkyPhone();
                        requirements = true;
                         // System.out.println(authorization_ttList.get(i).getName() + "--" + klusterT2 + "--" + i1 + "--" + vv);
                    }
                    if (requirements) {
                        for (int k = 0; k < listPhone.size(); k++) {
                            if (vv > 0 && i1 > 0 && authorization_ttList.get(i).getName().equals(distributionPhoneList.get(j).getShop()) && distributionPhoneList.get(j).getModelPhone().equals(listPhone.get(k)) && distributionPhoneList.get(j).getRemanisPhone() > 0) {
                                vv = vv - 1;
                                i1 = i1 - 1;
                                from_whereTo_whereList.add(new From_whereTo_where(name, listPhone.get(k), distributionPhoneList.get(j).getShop()));
                                distributionPhoneList.set(j, new DistributionPhone(distributionPhoneList.get(j).getShop(), listPhone.get(k), distributionPhoneList.get(j).getSkyPhone(), distributionPhoneList.get(j).getRemanisPhone() - 1, false));
                                updateDistributionPhoneList(name, distributionPhoneList.get(j).getShop(), klusterT2, listPhone.get(k));
                                listPhone.remove(k);
                            }

                        }
                    }
                }

            }
        }
        for (int i = 0; i < authorization_ttList.size(); i++) {
            boolean requirements = false;

            if (!checkingForT2(authorization_ttList.get(i).getName())) {
                for (int j = 0; j < distributionPhoneList.size(); j++) {
                    if (i1 > 0 && authorization_ttList.get(i).getName().equals(distributionPhoneList.get(j).getShop()) && klusterT2.equals(distributionPhoneList.get(j).getModelPhone()) && distributionPhoneList.get(j).getRemanisPhone() > distributionPhoneList.get(j).getSkyPhone()) {
                        vv = distributionPhoneList.get(j).getRemanisPhone() - distributionPhoneList.get(j).getSkyPhone();
                        requirements = true;
                        //  System.out.println(authorization_ttList.get(i).getName() + "--" + klusterT2 + "--" + i1 + "--" + vv);
                    }
                    if (requirements) {
                        for (int k = 0; k < listPhone.size(); k++) {
                            if (vv > 0 && i1 > 0 && authorization_ttList.get(i).getName().equals(distributionPhoneList.get(j).getShop()) && distributionPhoneList.get(j).getModelPhone().equals(listPhone.get(k)) && distributionPhoneList.get(j).getRemanisPhone() > 0) {
                                vv = vv - 1;
                                i1 = i1 - 1;
                                from_whereTo_whereList.add(new From_whereTo_where(name, listPhone.get(k), distributionPhoneList.get(j).getShop()));
                                distributionPhoneList.set(j, new DistributionPhone(distributionPhoneList.get(j).getShop(), listPhone.get(k), distributionPhoneList.get(j).getSkyPhone(), distributionPhoneList.get(j).getRemanisPhone() - 1, false));
                                updateDistributionPhoneList(name, distributionPhoneList.get(j).getShop(), klusterT2, listPhone.get(k));
                                listPhone.remove(k);
                            }

                        }
                    }
                }

            }
        }

    }

    private void updateDistributionPhoneList(String name, String shop, String klusterT2, String s) {
        for (int i = 0; i < distributionPhoneList.size(); i++) {
            if (name.equals(distributionPhoneList.get(i).getShop()) && distributionPhoneList.get(i).getModelPhone().equals(klusterT2)) {
                //  System.out.println("ok+");
                distributionPhoneList.set(i, new DistributionPhone(name, klusterT2, distributionPhoneList.get(i).getSkyPhone(), distributionPhoneList.get(i).getRemanisPhone() + 1, true));
            }
            if (shop.equals(distributionPhoneList.get(i).getShop()) && distributionPhoneList.get(i).getModelPhone().equals(klusterT2)) {
                //System.out.println("ok-");
                distributionPhoneList.set(i, new DistributionPhone(shop, klusterT2, distributionPhoneList.get(i).getSkyPhone(), distributionPhoneList.get(i).getRemanisPhone() - 1, true));
            }
        }

       if (checkingRemanis(name, s)) {

            for (int i = 0; i < remanisSimList.size(); i++) {
                if (name.equals(remanisSimList.get(i).getShop()) && remanisSimList.get(i).getNameSimAndModem().equals(s)){
                    remanisSimList.set(i, new RemanisSim(remanisSimList.get(i).getId(),name,s,remanisSimList.get(i).getRemainsSimModem()+1));
                    
                }


            }
        } else {

                    remanisSimList.add( new RemanisSim(remanisSimList.size()+1,name,s,1));
                  //  System.out.println(name +"--"+ s);

        }

        for (int i = 0; i < remanisSimList.size(); i++) {
            if (shop.equals(remanisSimList.get(i).getShop()) && remanisSimList.get(i).getNameSimAndModem().equals(s)){
                remanisSimList.set(i, new RemanisSim(remanisSimList.get(i).getId(),shop,s,remanisSimList.get(i).getRemainsSimModem()-1));

            }

        }

    }

    private boolean checkingRemanis(String name, String s) {
        boolean reanis = false;
        RemanisSim james = remanisSimList.stream()
                .filter(customer -> name.equals(customer.getShop()) && customer.getNameSimAndModem().equals(s))
                .findAny()
                .orElse(null);
        if (james != null) {
            reanis = true;
        } else {
            reanis = false;
        }
        return reanis;
    }


    public boolean checkingForT2(String name) {
        boolean t2 = false;
        authorization_tt james = authorization_ttList
                .stream()
                .filter(customer -> name.equals(customer.getName()) && !customer.getClusterT2().isEmpty())
                .findAny()
                .orElse(null);
        if (james != null) {
            t2 = true;
        } else {
            t2 = false;
        }
        return t2;
    }

    public List<From_whereTo_where> shopMovements(String shop) {
        from_whereTo_whereList = new ArrayList<>();

        for (int i = 0; i < authorization_ttList.size(); i++) {
            distributionsPhoneList(authorization_ttList.get(i).getName());
        }
            redistributingPhones(shop);
            redistributingPhonesтNeT2(shop);

        return from_whereTo_whereList;

    }

    public Iterable<TableMatrixT2> requirementMatrixT2Phone() {


        return matrixT2Phone();
    }
}
