package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.SimPlan;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.SIM.ShopPlanSim;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    //из entity в dto
    public SimPlan mapShopPlanSim(RemanisSim entity){
        SimPlan dto = new SimPlan();
        dto.setNameSim(entity.getNameSimAndModem());
        dto.setRemains(entity.getRemainsSimModem());
        return dto;
    }
    //из dto в entity
    public SimPlan mapToProductEntity(SaleSim_6m entity){
        SimPlan dto = new SimPlan();
        dto.setNameSim(entity.getNameSimAndModem());
        dto.setRemains(entity.getRemainsSimModem());
        return dto;
    }
}
