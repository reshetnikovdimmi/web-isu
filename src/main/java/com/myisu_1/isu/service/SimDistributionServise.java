package com.myisu_1.isu.service;

import com.myisu_1.isu.models.SIM.SimAndRtkTable;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.PostRepositoriy;
import com.myisu_1.isu.repo.SaleSimModemRepository_1m;
import com.myisu_1.isu.repo.SaleSimModemRepository_6m;
import com.myisu_1.isu.repo.SimAndRtkTableRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class SimDistributionServise {
    @Autowired
    private SimAndRtkTableRepositoriy rtkTableRepositoriy;
    @Autowired
    private SaleSimModemRepository_6m saleSimModemRepository6m;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository1m;
    @Autowired
    private PostRepositoriy authorization_ttRepositoriy;
    Map<String,Map<String,Map<String,String>>> shopSimRTK;
    Map<String,Map<String,String>> shopsSimRTK;
    Map<String,String> shopssSimRTK;
    public Object needSim() {



        shopsSimRTK = new HashMap<>();
        shopSimRTK = new HashMap<>();


            for (SimAndRtkTable simAndRtkTable : rtkTableRepositoriy.findAll()) {
                shopssSimRTK = new TreeMap<>();
                shopssSimRTK.put("view",simAndRtkTable.getView());
                String sale6 = saleSimModemRepository6m.getSale6Sim(simAndRtkTable.getNameRainbow());
                if (sale6!=null){
                    shopssSimRTK.put("sale6",String.valueOf(Integer.parseInt(sale6)/6));
                }else{
                    shopssSimRTK.put("sale6", null);
                }
                shopssSimRTK.put("sale1",saleSimModemRepository1m.getSale1Sim(simAndRtkTable.getNameRainbow()));
                shopsSimRTK.put(simAndRtkTable.getNameRainbow(),shopssSimRTK);
            }

        return shopsSimRTK;
    }
}
