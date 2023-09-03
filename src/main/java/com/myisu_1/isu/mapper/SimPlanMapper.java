package com.myisu_1.isu.mapper;

import com.myisu_1.isu.dto.SimPlan;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.SaleSim_1m;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SimPlanMapper {
    @Mapping(source = "remanisSim.nameSimAndModem", target = "nameSim")
    @Mapping(source = "saleSim1m.remainsSimModem", target = "sale1")
    @Mapping(source = "remanisSim.remainsSimAndModem", target = "remains")

    SimPlan simPlanDTO (RemanisSim remanisSim, SaleSim_1m saleSim1m);
}
