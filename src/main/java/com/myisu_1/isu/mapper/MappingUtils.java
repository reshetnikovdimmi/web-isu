package com.myisu_1.isu.mapper;

import com.myisu_1.isu.dto.SimPlan;
import com.myisu_1.isu.models.SIM.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappingUtils {


    public SimPlan mapShopPlanSim(RemanisSim rem, SaleSim_1m sale1, SaleSim_6m sale6, ShopPlanSim s, SimAndRtkTable rtks) {
        SimPlan dto = new SimPlan();
        dto.setId(s.getId());
        dto.setNameSim(s.getNameSimModem());
        dto.setRemains(rem==null?null:rem.getRemainsSimAndModem());
        dto.setSale1(sale1==null?null:sale1.getRemainsSimModem());
        dto.setSale6(sale6==null?null:sale6.getRemainsSimModem()/6);
        dto.setPlan(s==null?null:s.getPlan());
        dto.setView(rtks==null?null:rtks.getView());
        return dto;

    }
}
