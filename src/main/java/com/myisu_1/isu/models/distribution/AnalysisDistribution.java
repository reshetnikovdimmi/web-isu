package com.myisu_1.isu.models.distribution;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.dto.RemainsGroupCash;

import java.util.ArrayList;
import java.util.List;

public abstract class AnalysisDistribution {

    public List<OrderRecommendations> remains;
    public List<String> warehouse;
    public List<OrderRecommendations> remainsCashList;
    public List<OrderRecommendations> remainsCashGroup(List<RemainsGroupCash> all) {

       remainsCashList = new ArrayList<>();
        for (RemainsGroupCash o : all) {

            if (o.getGroup() != null) {
                OrderRecommendations dto = new OrderRecommendations();
                dto.setGroup(o.getGroup());
                dto.setView(o.getView());
                OrderRecommendations rem;
                OrderRecommendations rem1;
                if(o.getView()==null){
                    rem = remains.stream().filter(r -> r.getShop().equals(warehouse.get(0)) && r.getGroup().equals(o.getGroup())).findAny().orElse(null);
                    rem1 = remains.stream().filter(r -> r.getShop().equals(warehouse.get(1)) && r.getGroup().equals(o.getGroup())).findAny().orElse(null);
                }else {
                    rem = remains.stream().filter(r -> r.getShop().equals(warehouse.get(0)) && r.getGroup().equals(o.getGroup())&&r.getView().equals(o.getView())).findAny().orElse(null);
                    rem1 = remains.stream().filter(r -> r.getShop().equals(warehouse.get(1)) && r.getGroup().equals(o.getGroup())&&r.getView().equals(o.getView())).findAny().orElse(null);
                }
                if (rem != null) {
                    dto.setRemainsCash1(rem.getRemainsShopL() == null ? null : Math.toIntExact(rem.getRemainsShopL()));
                } else {
                    dto.setRemainsCash1(null);
                }
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


}
