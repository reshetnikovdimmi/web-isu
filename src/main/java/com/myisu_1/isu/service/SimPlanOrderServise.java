package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.SimPlan;
import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.models.SIM.ShopPlanSim;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimPlanOrderServise {
    @Autowired
    private ShopPlanSimRepository shopPlanSimRepository;
    @Autowired
    private PostRepositoriy authorization;
    @Autowired
    private SimAndRtkTableRepositoriy simAndRtkTableRepositoriy;
@Autowired
private MappingUtils mappingUtils;
@Autowired
private RemanisSimRepository remanisSim;
@Autowired
private SaleSimModemRepository_6m saleSimModemRepository_6m;


    List<SimPlan> list;


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
       list = new ArrayList<>();
        list.add(new SimPlan(0,"Дубликат SIM 0/0/0",null,5555,null,null,null));
        list.add(new SimPlan());
        list.add(new SimPlan());





        return makeMoney(findAll(shop));
    }


    //для листа продуктов мы использовали стрим
    public List<SimPlan> findAll(String shop) {

        return remanisSim.findByShop(shop).stream()//создали из листа стирим
                .map(mappingUtils::mapShopPlanSim) //оператором из streamAPI map, использовали для каждого элемента метод mapToProductDto из класса MappingUtils
                .collect(Collectors.toList()); //превратили стрим обратно в коллекцию, а точнее в лист

    }
    public List<SimPlan> makeMoney(List<SimPlan> list) {
        list.forEach(simPlan ->
                simPlan.setSale1(555)
        );

        return list;
    }
}
