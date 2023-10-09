package com.myisu_1.isu.models.distribution;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.dto.RemainsGroupCash;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public abstract class AnalysisDistribution {

    public List<OrderRecommendations> remainsGroup;
    public List<OrderRecommendations> remainsNomenclature;
    public List<String> warehouse;
    public List<OrderRecommendations> remainsCashList;
    public List<OrderRecommendations> indicatorPhoneShop;
    public List<OrderRecommendations> remainsAll;

    public List<OrderRecommendations> sale1;
    public List<OrderRecommendations> sale6;
    public OrderRecommendations or;

    public void remainsCashGroup(List<RemainsGroupCash> all) {

        remainsCashList = new ArrayList<>();

        remainsGroup = remainsGroup.stream().filter(r -> r.getShop() != null).collect(Collectors.toList());
        for (RemainsGroupCash o : all) {
            OrderRecommendations dto = new OrderRecommendations();
            dto.setGroup(o.getGroup());
            dto.setRemainsCash1(remainsGroup.stream().filter(r -> r.getShop().equals(warehouse.get(0)) && r.getGroup().equals(o.getGroup())).mapToInt(r -> Math.toIntExact(r.getRemainsShop())).sum());
            dto.setRemainsCash2(remainsGroup.stream().filter(r -> r.getShop().equals(warehouse.get(1)) && r.getGroup().equals(o.getGroup())).mapToInt(r -> Math.toIntExact(r.getRemainsShop())).sum());
            //  String view = rem.stream().filter(r -> r.getShop().equals(warehouse.get(1)) && r.getGroup().equals(o.getGroup())).map(r->r.getView()).findFirst().orElse(null);
            remainsCashList.add(dto);
        }
        or.setIndicatorPhoneShop(remainsCashList);
    }



    public void remainsNomenclatureSach(List<String> all) {
        List<OrderRecommendations> remain = new ArrayList<>();
        for (String o : all) {
            OrderRecommendations dto = new OrderRecommendations();
            OrderRecommendations rem = remainsGroup.stream().filter(r -> r.getShop().equals(warehouse.get(0)) && r.getNomenclature().equals(o)).findAny().orElse(null);
            OrderRecommendations rem1 = remainsGroup.stream().filter(r -> r.getShop().equals(warehouse.get(1)) && r.getNomenclature().equals(o)).findAny().orElse(null);
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



    public void indicatorsPhoneShopGroup(List<String> shop, List<OrderRecommendations> remainMatrixList) {
        remainsSaleShopAll(shop, remainMatrixList);
        or.setIndicatorPhoneShop(remainsSaleShopAll(shop, remainMatrixList));

    }

    public void distributionPhone(List<RemainsGroupCash> all, String shop) {
        List<OrderRecommendations> distributionPhone = new ArrayList<>();

        for (RemainsGroupCash o : all) {
            OrderRecommendations dto = new OrderRecommendations();
            dto.setGroup(o.getGroup());
            int rem = remainsGroup.stream().filter(r -> r.getShop().equals(shop) && r.getGroup().equals(o.getGroup())).mapToInt(r -> Math.toIntExact(r.getRemainsShop())).sum();
         //   int sale_1 = sale1.stream().filter(r -> r.getShop().equals(shop) && r.getGroup().equals(o.getGroup())).mapToInt(r -> Math.toIntExact(r.getRemainsShop())).sum();
        //    int sale_6 = sale6.stream().filter(r -> r.getShop().equals(shop) && r.getGroup().equals(o.getGroup())).mapToInt(r -> Math.toIntExact(r.getRemainsShop())).sum();
            dto.setRemainsShop(rem == 0 ? null : rem);
          //  dto.setSale1(sale_1 == 0 ? null : sale_1);
          //  dto.setSale6(sale_6 == 0 ? null : sale_6);
            dto.setAll(alls(o.getGroup(), shop));
            distributionPhone.add(dto);

        }

        or.setDistributionPhone(distributionPhone);
    }

    private List<OrderRecommendations> alls(String group, String shop) {

        List<OrderRecommendations> alls = remainsGroup.stream().filter(r -> r.getShop().equals(shop) && r.getGroup().equals(group)).collect(Collectors.toList());


        return alls;
    }

    public List<OrderRecommendations> remainsSaleShopAll(List<String> shop, List<OrderRecommendations> remainMatrixList) {

        List<OrderRecommendations> remainsSaleShopAll = new ArrayList<>();
        for (String s : shop) {
            OrderRecommendations dto = new OrderRecommendations();
            dto.setShop(s);

            OrderRecommendations rem = indicatorPhoneShop.stream().filter(r -> r.getGroup().equals(s)).findAny().orElse(null);
            OrderRecommendations sale_1 = sale1.stream().filter(r -> r.getGroup().equals(s)).findAny().orElse(null);
            OrderRecommendations sale_6 = sale6.stream().filter(r -> r.getGroup().equals(s)).findAny().orElse(null);
            int rems = remainMatrixList == null ? 0 : remainMatrixList.stream().filter(r -> r.getShop().equals(s)).mapToInt(o -> Math.toIntExact(o.getRemainsShopL())).sum();
            double max = DoubleStream.of(rems, sale_1 == null ? 0 : Math.toIntExact(sale_1.getRemainsShopL()), sale_6 == null ? 0 : Math.toIntExact(sale_6.getRemainsShopL() / 3))
                    .max()
                    .getAsDouble();
            dto.setRemainsShop(rem == null ? null : Math.toIntExact(rem.getRemainsShopL()));
            dto.setSale1(sale_1 == null ? null : Math.toIntExact(sale_1.getRemainsShopL()));
            dto.setSale6(sale_6 == null ? null : Math.toIntExact(sale_6.getRemainsShopL()));
            dto.setOrder((int) max);

            remainsSaleShopAll.add(dto);
        }

        return remainsSaleShopAll;
    }
}
