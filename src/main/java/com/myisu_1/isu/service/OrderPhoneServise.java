package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Phone.OrderPhone;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderPhoneServise extends MovementsPhoneServise{
    List<OrderPhone> orderPhoneList;
    public List<OrderPhone> orderFromT2Warehouse() {
        orderPhoneList = new ArrayList<>();
        for (int i = 0; i < authorization_ttList.size(); i++) {
            if (checkingForT2(authorization_ttList.get(i).getName()) ){
                distributionsPhoneList(authorization_ttList.get(i).getName());
            }

        }
        for (int i = 0; i < distributionPhoneList.size(); i++) {


        }

        distinct();

        return orderPhoneList;
    }

    private List<String> distinct() {
        List<String> distingMatrixT2 = new ArrayList<>();
        ArrayList<String> listWithDuplicate = new ArrayList<>();
        for (int i = 0; i < phoneSmartList.size(); i++) {
            listWithDuplicate.add(phoneSmartList.get(i).getModel_GB());

        }

        distingMatrixT2 = (ArrayList) listWithDuplicate
                .stream()
                .distinct()
                .collect(Collectors.toList());

        return distingMatrixT2;
    }
}

