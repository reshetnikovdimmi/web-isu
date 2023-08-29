package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.models.SIM.ShopPlanSim;
import com.myisu_1.isu.repo.PostRepositoriy;
import com.myisu_1.isu.repo.ShopPlanSimRepository;
import com.myisu_1.isu.repo.SimAndRtkTableRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimPlanOrderServise {
    @Autowired
    private ShopPlanSimRepository shopPlanSimRepository;
    @Autowired
    private PostRepositoriy authorization;
    @Autowired
    private SimAndRtkTableRepositoriy simAndRtkTableRepositoriy;

    public Object simPlanOrder(String shop) {
        Authorization_tt shops = authorization.findByName(shop);
        List<String> simPlanNull;
        List<String> sim = new ArrayList<>();
        sim.add(shops.getSimT2());
        sim.add(shops.getSimMts());
        sim.add(shops.getSimMf());
        List<String> simShopPlanRem = shopPlanSimRepository.simShopPlanRem(shop);

        if (!simShopPlanRem.isEmpty()) {
            simPlanNull = simAndRtkTableRepositoriy.getNull(sim, simShopPlanRem);
        }else {
            simPlanNull = simAndRtkTableRepositoriy.getNull(sim);
        }


        List<ShopPlanSim> shopPlanSims = new ArrayList<>();
        for (String s : simPlanNull) {
            shopPlanSims.add(new ShopPlanSim(shop, s));
        }
        if (simPlanNull.size() != 0) shopPlanSimRepository.saveAll(shopPlanSims);


        return simAndRtkTableRepositoriy.simPlanOrder(shop, sim);
    }
}
