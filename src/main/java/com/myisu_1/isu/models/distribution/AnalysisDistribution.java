package com.myisu_1.isu.models.distribution;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.SimAndRtkTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AnalysisDistribution {

    public List<OrderRecommendations> remains;
    public List<String> remainsCash;
    List<OrderRecommendations> remainsCashList;
    public List<OrderRecommendations> remainsCashGroup(List<SimAndRtkTable> all) {
       remainsCashList = new ArrayList<>();

        for (SimAndRtkTable o : all) {
            if (o.getDistributionModel() != null) {
                OrderRecommendations dto = new OrderRecommendations();
                dto.setGroup(o.getDistributionModel());
                dto.setView(o.getView());
                OrderRecommendations rem = remains.stream().filter(r -> r.getShop().equals(remainsCash.get(0)) && r.getGroup().equals(o.getDistributionModel())).findAny().orElse(null);
                if (rem != null) {
                    dto.setRemainsCash1(rem.getRemainsShopL() == null ? null : Math.toIntExact(rem.getRemainsShopL()));
                } else {
                    dto.setRemainsCash1(null);
                }
                OrderRecommendations rem1 = remains.stream().filter(r -> r.getShop().equals(remainsCash.get(1)) && r.getGroup().equals(o.getDistributionModel())).findAny().orElse(null);
                if (rem1 != null) {

                    dto.setRemainsCash2(rem1.getRemainsShopL() == null ? null : Math.toIntExact(rem1.getRemainsShopL()));
                } else {
                    dto.setRemainsCash2(null);
                }

                remainsCashList.add(dto);
            }
        }

        return remainsCashList;
    }

    public List<OrderRecommendations> remainsCashGroupPhone(List<Phone_Smart> all) {
        remainsCashList = new ArrayList<>();

        for (Phone_Smart o : all) {
            if (o.getBrend() != null) {
                OrderRecommendations dto = new OrderRecommendations();
                dto.setGroup(o.getBrend() );
             //   dto.setView(o.getView());
                OrderRecommendations rem = remains.stream().filter(r -> r.getShop().equals(remainsCash.get(0)) && r.getGroup().equals(o.getBrend() )).findAny().orElse(null);
                if (rem != null) {
                    dto.setRemainsCash1(rem.getRemainsShopL() == null ? null : Math.toIntExact(rem.getRemainsShopL()));
                } else {
                    dto.setRemainsCash1(null);
                }
                OrderRecommendations rem1 = remains.stream().filter(r -> r.getShop().equals(remainsCash.get(1)) && r.getGroup().equals(o.getBrend() )).findAny().orElse(null);
                if (rem1 != null) {

                    dto.setRemainsCash2(rem1.getRemainsShopL() == null ? null : Math.toIntExact(rem1.getRemainsShopL()));
                } else {
                    dto.setRemainsCash2(null);
                }

                remainsCashList.add(dto);
            }
        }
        remainsCashList.forEach(r->System.out.println(r));
        return remainsCashList;
    }


}
