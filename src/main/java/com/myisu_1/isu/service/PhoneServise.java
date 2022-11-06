package com.myisu_1.isu.service;


import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.PostRepositoriy;
import com.myisu_1.isu.repo.SalesRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneServise {
    @Autowired
    private SalesRepositoriy salesRepositoriy;
    @Autowired
    private PostRepositoriy authorization_tt;
    List<Sales> sales;
    List<authorization_tt> authorization_ttList;

    public void LoadAuthorization_ttList() {
        authorization_ttList = (List<authorization_tt>) authorization_tt.findAll();
        sales = (List<Sales>) salesRepositoriy.findAll();
    }

    public List<authorization_tt> shopT2() {
        List<authorization_tt> shopT2 = new ArrayList<>();
        for (int i = 0; i < authorization_ttList.size(); i++) {
            if (!authorization_ttList.get(i).getClusterT2().isEmpty()) {
                authorization_tt authorizationTt = new authorization_tt();
                authorizationTt.setName(authorization_ttList.get(i).getName());
                shopT2.add(authorizationTt);
            }

        }

        return shopT2;
    }
}
