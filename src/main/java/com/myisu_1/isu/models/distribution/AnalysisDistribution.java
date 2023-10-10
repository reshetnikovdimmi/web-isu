package com.myisu_1.isu.models.distribution;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.dto.RemainsGroupCash;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public abstract class AnalysisDistribution {

    public List<OrderRecommendations> remainsNomenclature;
    public List<OrderRecommendations> sale1Nomenclature;
    public List<OrderRecommendations> sale6Nomenclature;

    public List<String> warehouse;
    public OrderRecommendations or;

    public void remainsCashGroup(List<RemainsGroupCash> all) {

        List<OrderRecommendations>  remainsCashList = new ArrayList<>();

        remainsNomenclature = remainsNomenclature.stream().filter(r -> r.getShop() != null).collect(Collectors.toList());
        for (RemainsGroupCash o : all) {
            OrderRecommendations dto = new OrderRecommendations();
            dto.setGroup(o.getGroup());
            dto.setRemainsCash1(remainsNomenclature.stream().filter(r -> r.getShop().equals(warehouse.get(0)) && r.getGroup().equals(o.getGroup())).mapToInt(r -> r.getRemainsShop()).sum());
            dto.setRemainsCash2(remainsNomenclature.stream().filter(r -> r.getShop().equals(warehouse.get(1)) && r.getGroup().equals(o.getGroup())).mapToInt(r -> r.getRemainsShop()).sum());
            remainsCashList.add(dto);
        }
        or.setIndicatorPhoneShop(remainsCashList);
    }



    public void remainsNomenclatureSach(List<String> all) {
        List<OrderRecommendations> remain = new ArrayList<>();
        for (String o : all) {
            OrderRecommendations dto = new OrderRecommendations();
            OrderRecommendations rem = remainsNomenclature.stream().filter(r -> r.getShop().equals(warehouse.get(0)) && r.getNomenclature().equals(o)).findAny().orElse(null);
            OrderRecommendations rem1 = remainsNomenclature.stream().filter(r -> r.getShop().equals(warehouse.get(1)) && r.getNomenclature().equals(o)).findAny().orElse(null);
            if (rem != null) {
                dto.setNomenclature(rem.getNomenclature());
                dto.setRemainsCash1(rem.getRemainsShop());
                dto.setGroup(rem.getGroup());
            }
            if (rem1 != null) {
                dto.setNomenclature(rem1.getNomenclature());
                dto.setRemainsCash2(rem1.getRemainsShop());
                dto.setGroup(rem1.getGroup());
            }
            remain.add(dto);
        }
        or.setIndicatorPhoneSach(remain.stream().filter(r -> r.getGroup() != null).collect(Collectors.toList()));
    }

    public void indicatorsPhoneShopGroup(List<String> group, List<OrderRecommendations> remainMatrixList) {
        List<OrderRecommendations> remainsSaleShopAll = new ArrayList<>();

        for (String o:group){
            for (String w:warehouse){
                OrderRecommendations dto = new OrderRecommendations();
                dto.setRemainsShop(remainsNomenclature.stream().filter(r->r.getGroup().equals(o)&& r.getShop().equals(w)).mapToInt(r-> r.getRemainsShop()).sum());
                dto.setSale1(sale1Nomenclature.stream().filter(r->r.getGroup().equals(o)&& r.getShop().equals(w)).mapToInt(r-> r.getRemainsShop()).sum());
                dto.setSale6(sale6Nomenclature.stream().filter(r->r.getGroup().equals(o)&& r.getShop().equals(w)).mapToInt(r-> r.getRemainsShop()).sum());
                dto.setShop(w);
                dto.setGroup(o);
                remainsSaleShopAll.add(dto);
            }
        }
        or.setIndicatorPhoneShop(remainsSaleShopAll);
    }

    public void distributionPhone(List<RemainsGroupCash> all, String shop) {
        List<OrderRecommendations> distributionPhone = new ArrayList<>();

        for (RemainsGroupCash o : all) {
            OrderRecommendations dto = new OrderRecommendations();

            dto.setGroup(o.getGroup());
            dto.setRemainsShop(remainsNomenclature.stream().filter(r -> r.getShop().equals(shop) && r.getGroup().equals(o.getGroup())).mapToInt(r -> r.getRemainsShop()).sum());
            dto.setSale1(sale1Nomenclature.stream().filter(r -> r.getShop().equals(shop) && r.getGroup().equals(o.getGroup())).mapToInt(r -> r.getRemainsShop()).sum());
            dto.setSale6(sale6Nomenclature.stream().filter(r -> r.getShop().equals(shop) && r.getGroup().equals(o.getGroup())).mapToInt(r -> r.getRemainsShop()).sum());
            dto.setAll(alls(o.getGroup(), shop));
            distributionPhone.add(dto);
        }

        or.setDistributionPhone(distributionPhone);
    }

    private List<OrderRecommendations> alls(String group, String shop) {

        List<OrderRecommendations> alls = remainsNomenclature.stream().filter(r -> r.getShop().equals(shop) && r.getGroup().equals(group)).collect(Collectors.toList());


        return alls;
    }

    public List<OrderRecommendations> remainsSaleShopAll(List<String> shop, List<OrderRecommendations> remainMatrixList) {

        List<OrderRecommendations> remainsSaleShopAll = new ArrayList<>();
        for (String s : shop) {
            OrderRecommendations dto = new OrderRecommendations();
            dto.setShop(s);

            dto.setRemainsShop(remainsNomenclature.stream().filter(r -> r.getShop().equals(s)).mapToInt(OrderRecommendations::getRemainsShop).sum());
            int sale_1 = sale1Nomenclature.stream().filter(r -> r.getShop().equals(s)).mapToInt(OrderRecommendations::getRemainsShop).sum();
            int sale_6 = sale6Nomenclature.stream().filter(r -> r.getShop().equals(s)).mapToInt(OrderRecommendations::getRemainsShop).sum();
            int rems = remainMatrixList == null ? 0 : remainMatrixList.stream().filter(r -> r.getShop().equals(s)).mapToInt(o -> Math.toIntExact(o.getRemainsShopL())).sum();
            double max = DoubleStream.of(rems, sale_1, sale_6 / 3)
                    .max()
                    .getAsDouble();

            dto.setSale1(sale_1);
            dto.setSale6(sale_6);
            dto.setOrder((int) max);

            remainsSaleShopAll.add(dto);
        }

        return remainsSaleShopAll;
    }
}
