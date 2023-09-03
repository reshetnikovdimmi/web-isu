package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.SimOrderDto;
import com.myisu_1.isu.dto.SimPlan;
import com.myisu_1.isu.mapper.MappingUtils;
import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.models.SIM.*;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository_1m;


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
        } else {
            simPlanNull = simAndRtkTableRepositoriy.getNull(sim);
        }
        List<ShopPlanSim> shopPlanSims = new ArrayList<>();
        for (String s : simPlanNull) {
            shopPlanSims.add(new ShopPlanSim(shop, s));
        }
        if (simPlanNull.size() != 0) shopPlanSimRepository.saveAll(shopPlanSims);

        List<ShopPlanSim> shopPlanSimList = shopPlanSimRepository.findByShop(shop);
        List<SimPlan> simPlanList = new ArrayList<>();
        List<RemanisSim> reman = remanisSim.findByShop(shop);
        List<SaleSim_1m> sal1 = saleSimModemRepository_1m.findByShop(shop);
        List<SaleSim_6m> sal6 = saleSimModemRepository_6m.findByShop(shop);

        for (ShopPlanSim s : shopPlanSimList) {
            RemanisSim rem = reman.stream().filter(r -> r.getNameSimAndModem().equals(s.getNameSimModem())).findAny().orElse(null);
            SaleSim_1m sale1 = sal1.stream().filter(r -> r.getNameSimAndModem().equals(s.getNameSimModem())).findAny().orElse(null);
            SaleSim_6m sale6 = sal6.stream().filter(r -> r.getNameSimAndModem().equals(s.getNameSimModem())).findAny().orElse(null);
            simPlanList.add(mappingUtils.mapShopPlanSim(rem, sale1, sale6, s));
        }
        return simPlanList;
    }

    public Object orderSim() {
        List<ShopPlanSim> plan = shopPlanSimRepository.findAll();
        List<RemanisSim> reman = remanisSim.findAll();
        List<SimAndRtkTable> sod = simAndRtkTableRepositoriy.findAll();
        List<ShopPlanSim> orderDtoList = new ArrayList<>();
        for (ShopPlanSim s : plan) {
            RemanisSim rem = reman.stream().filter(r -> r.getNameSimAndModem().equals(s.getNameSimModem()) && r.getShop().equals(s.getShop())).findAny().orElse(null);
            int remans = rem == null ? 0 : rem.getRemainsSimAndModem();
            int plans = s.getPlan() == 0 ? 0 : s.getPlan();
            orderDtoList.add(new ShopPlanSim(s.getShop(), s.getNameSimModem(), remans < plans ? plans - remans : 0));
        }
        Map<String, Integer> phonesByCompany = orderDtoList.stream().collect(Collectors.groupingBy(ShopPlanSim::getNameSimModem, Collectors.summingInt(ShopPlanSim::getPlan)));
        List<SimOrderDto> dtos = new ArrayList<>();
        for (Map.Entry<String, Integer> item : phonesByCompany.entrySet()) {
            SimAndRtkTable simAndRtkTable = sod.stream().filter(sim -> sim.getNameRainbow().equals(item.getKey())).findAny().orElse(null);
            dtos.add(new SimOrderDto(item.getKey(), simAndRtkTable.getToOrder(), simAndRtkTable.getView(), item.getValue()));
        }
        return dtos;
    }
}

