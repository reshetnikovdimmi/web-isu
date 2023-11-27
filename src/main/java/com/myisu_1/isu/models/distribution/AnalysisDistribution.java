package com.myisu_1.isu.models.distribution;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.dto.RemainsGroupCash;
import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.models.Phone_Smart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public abstract class AnalysisDistribution {

    public List<OrderRecommendations> remainsNomenclature;
    public List<OrderRecommendations> sale1Nomenclature;
    public List<OrderRecommendations> sale6Nomenclature;
    public List<Phone_Smart> phoneSmarts;
    public List<String> warehouse;
    public OrderRecommendations or;

    public void remainsCashGroup(List<RemainsGroupCash> all) {
        List<OrderRecommendations> remainsCashList = new ArrayList<>();
        remainsNomenclature = remainsNomenclature.stream().filter(r -> r.getShop() != null).collect(Collectors.toList());

        for (RemainsGroupCash o : all) {

            OrderRecommendations dto = new OrderRecommendations();
            dto.setGroup(o.getGroup());
            dto.setView(o.getView());
            if (o.getView()==null){
                dto.setRemainsCash1(remainsNomenclature.stream().filter(r ->r.getGroup()!=null && r.getShop().equals(warehouse.get(0)) && r.getGroup().equals(o.getGroup())).mapToInt(OrderRecommendations::getRemainsShop).sum());
                dto.setRemainsCash2(remainsNomenclature.stream().filter(r ->r.getGroup()!=null && r.getShop().equals(warehouse.get(1)) && r.getGroup().equals(o.getGroup())).mapToInt(OrderRecommendations::getRemainsShop).sum());
                remainsCashList.add(dto);
            }else {

                dto.setRemainsCash1(remainsNomenclature.stream().filter(r ->r.getGroup()!=null && r.getShop().equals(warehouse.get(0)) && o.getView().equals(r.getView()) && r.getGroup().equals(o.getGroup())).mapToInt(OrderRecommendations::getRemainsShop).sum());
                dto.setRemainsCash2(remainsNomenclature.stream().filter(r ->r.getGroup()!=null && r.getShop().equals(warehouse.get(1)) && o.getView().equals(r.getView())  && r.getGroup().equals(o.getGroup())).mapToInt(OrderRecommendations::getRemainsShop).sum());
                remainsCashList.add(dto);
            }

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


        for (String o : group) {

            for (String w : warehouse) {

                OrderRecommendations dto = new OrderRecommendations();
                dto.setRemainsShop(remainsNomenclature.stream().filter(r ->r.getGroup()!=null && r.getGroup().equals(o) && r.getShop().equals(w)).mapToInt(OrderRecommendations::getRemainsShop).sum());
                dto.setSale1(sale1Nomenclature.stream().filter(r ->r.getGroup()!=null && r.getGroup().equals(o) && r.getShop().equals(w)).mapToInt(OrderRecommendations::getRemainsShop).sum());
                dto.setSale6(sale6Nomenclature.stream().filter(r ->r.getGroup()!=null && r.getGroup().equals(o) && r.getShop().equals(w)).mapToInt(OrderRecommendations::getRemainsShop).sum());
                dto.setShop(w);
                dto.setGroup(o);
                OrderRecommendations view = remainsNomenclature.stream().filter(r ->r.getGroup()!=null && r.getGroup().equals(o) && r.getShop().equals(w)).findAny().orElse(null);
                dto.setView(view==null?null:view.getView());
                remainsSaleShopAll.add(dto);
            }
        }

       or.setRemainsGroupShop(remainsSaleShopAll);
    }

    public void distributionPhone(List<RemainsGroupCash> all) {
        List<OrderRecommendations> distributionPhone = new ArrayList<>();
        remainsNomenclature = remainsNomenclature.stream().filter(r -> r.getGroup() != null).collect(Collectors.toList());
        sale1Nomenclature = sale1Nomenclature.stream().filter(r -> r.getGroup() != null).collect(Collectors.toList());
        sale6Nomenclature = sale6Nomenclature.stream().filter(r -> r.getGroup() != null).collect(Collectors.toList());
        for (String w : warehouse) {
            for (RemainsGroupCash o : all) {
                OrderRecommendations dto = new OrderRecommendations();
                dto.setShop(w);
                dto.setGroup(o.getGroup());
                dto.setRemainsShop(remainsNomenclature.stream().filter(r -> r.getShop().equals(w) && r.getGroup().equals(o.getGroup())).mapToInt(OrderRecommendations::getRemainsShop).sum());
                dto.setSale1(sale1Nomenclature.stream().filter(r -> r.getShop().equals(w) && r.getGroup().equals(o.getGroup())).mapToInt(OrderRecommendations::getRemainsShop).sum());
                dto.setSale6(sale6Nomenclature.stream().filter(r -> r.getShop().equals(w) && r.getGroup().equals(o.getGroup())).mapToInt(OrderRecommendations::getRemainsShop).sum());
                dto.setRemainsCash1(or.getIndicatorPhoneShop().stream().filter(r -> r.getGroup().equals(o.getGroup())).mapToInt(OrderRecommendations::getRemainsCash1).sum());
                dto.setRemainsCash2(or.getIndicatorPhoneShop().stream().filter(r -> r.getGroup().equals(o.getGroup())).mapToInt(OrderRecommendations::getRemainsCash2).sum());
                dto.setAll(alls(o.getGroup(), w));
               // OrderRecommendations view = remainsNomenclature.stream().filter(r -> r.getShop().equals(w) && r.getGroup().equals(o.getGroup())).findAny().orElse(null);
                dto.setView(o.getView());
                distributionPhone.add(dto);
            }
        }
        or.setDistributionPhone(distributionPhone);
    }

    private List<OrderRecommendations> alls(String group, String shop) {

        List<Phone_Smart> pS = phoneSmarts.stream().filter(r ->r.getMatrix_T2()!=null && r.getMatrix_T2().equals(group)).collect(Collectors.toList());

        List<OrderRecommendations> alls = new ArrayList<>();
        for (Phone_Smart p : pS) {
            OrderRecommendations dto = new OrderRecommendations();
            dto.setRemainsShop(remainsNomenclature.stream().filter(r -> r.getShop().equals(shop) && r.getNomenclature().equals(p.getModel())).mapToInt(OrderRecommendations::getRemainsShop).sum());
            dto.setSale1(sale1Nomenclature.stream().filter(r -> r.getShop().equals(shop) && r.getNomenclature().equals(p.getModel())).mapToInt(OrderRecommendations::getRemainsShop).sum());
            dto.setSale6(sale6Nomenclature.stream().filter(r -> r.getShop().equals(shop) && r.getNomenclature().equals(p.getModel())).mapToInt(OrderRecommendations::getRemainsShop).sum());
            dto.setRemainsCash1(or.getIndicatorPhoneSach().stream().filter(r ->r.getRemainsCash1() != null && r.getNomenclature().equals(p.getModel())).mapToInt(OrderRecommendations::getRemainsCash1).sum());
            dto.setRemainsCash2(or.getIndicatorPhoneSach().stream().filter(r ->r.getRemainsCash2() != null && r.getNomenclature().equals(p.getModel())).mapToInt(OrderRecommendations::getRemainsCash2).sum());
            dto.setNomenclature(p.getModel());
            alls.add(dto);
        }
        return alls;
    }

    public List<OrderRecommendations> remainsSaleShopAll(List<String> shop, List<MatrixSpark> remainMatrixList) {
        List<OrderRecommendations> remainsSaleShopAll = new ArrayList<>();
        for (String s : shop) {
            OrderRecommendations dto = new OrderRecommendations();
            dto.setShop(s);
            dto.setRemainsShop(remainsNomenclature.stream().filter(r -> r.getShop().equals(s)).mapToInt(OrderRecommendations::getRemainsShop).sum());
            int sale_1 = sale1Nomenclature.stream().filter(r -> r.getShop().equals(s)).mapToInt(OrderRecommendations::getRemainsShop).sum();
            int sale_6 = sale6Nomenclature.stream().filter(r -> r.getShop().equals(s)).mapToInt(OrderRecommendations::getRemainsShop).sum();
            int rems = remainMatrixList == null ? 0 : remainMatrixList.stream().filter(r -> r.getShop().equals(s)).mapToInt(o -> o.getMatrix()).sum();

            double max = DoubleStream.of(rems, sale_1, (double) sale_6 / 3)
                    .max()
                    .getAsDouble();
            dto.setSale1(sale_1);
            dto.setSale6(sale_6);
            dto.setOrder((int) max);
            remainsSaleShopAll.add(dto);
        }
        return remainsSaleShopAll;
    }
    public OrderRecommendations distributions(OrderRecommendations order, List<String> shopMult, List<OrderRecommendations> remainMatrixList) {
        System.out.println(order);
if(remainMatrixList!=null){


        if (remainMatrixList.stream().filter(r -> r.getShop().equals(order.getShop()) && r.getGroup().equals(order.getGroup())).findAny().orElse(null) == null) {
            OrderRecommendations ordr = new OrderRecommendations();
            ordr.setShop(order.getShop());
            ordr.setGroup(order.getGroup());
            ordr.setRemainsShopL(Long.valueOf(order.getOrder()));
            remainMatrixList.add(ordr);
        } else {
            for (OrderRecommendations o : remainMatrixList) {
                if (o.getShop().equals(order.getShop()) && o.getGroup().equals(order.getGroup())) {
                    o.setRemainsShopL(o.getRemainsShopL() + order.getOrder());
                }

            }
        }
    }

        for (int i = 0; i < or.getIndicatorPhoneShop().size(); i++) {
            if (or.getIndicatorPhoneShop().get(i).getGroup().equals(order.getGroup())) {
                if (shopMult.contains(order.getShop())) {
                    if (or.getIndicatorPhoneShop().get(i).getRemainsCash1() > 0) {
                        or.getIndicatorPhoneShop().get(i).setRemainsCash1(or.getIndicatorPhoneShop().get(i).getRemainsCash1() - order.getOrder());
                    } else {
                        or.getIndicatorPhoneShop().get(i).setRemainsCash2(or.getIndicatorPhoneShop().get(i).getRemainsCash2() - order.getOrder());
                    }
                } else {
                    if (or.getIndicatorPhoneShop().get(i).getRemainsCash2() > 0) {
                        or.getIndicatorPhoneShop().get(i).setRemainsCash2(or.getIndicatorPhoneShop().get(i).getRemainsCash2() - order.getOrder());
                    } else {
                        or.getIndicatorPhoneShop().get(i).setRemainsCash1(or.getIndicatorPhoneShop().get(i).getRemainsCash1() - order.getOrder());
                    }
                }
            }
        }
        if(or.getRemanisPhoneShopT2()!=null){
            for (int i = 0; i < or.getRemanisPhoneShopT2().size(); i++) {
                if (or.getRemanisPhoneShopT2().get(i).getShop().equals(order.getShop())) {
                    or.getRemanisPhoneShopT2().get(i).setRemainsShop(or.getRemanisPhoneShopT2().get(i).getRemainsShop() + order.getOrder());
                }
            }
        }
        if(or.getRemanisPhoneShopMult()!=null) {
            for (int i = 0; i < or.getRemanisPhoneShopMult().size(); i++) {
                if (or.getRemanisPhoneShopMult().get(i).getShop().equals(order.getShop())) {
                    or.getRemanisPhoneShopMult().get(i).setRemainsShop(or.getRemanisPhoneShopMult().get(i).getRemainsShop() + order.getOrder());
                }
            }
        }
        for (int i = 0; i < or.getIndicatorPhoneSach().size(); i++) {
            if (or.getIndicatorPhoneSach().get(i).getNomenclature().equals(order.getNomenclature())) {
                if (shopMult.contains(order.getShop())) {
                    if (or.getIndicatorPhoneSach().get(i).getRemainsCash1() != null && or.getIndicatorPhoneSach().get(i).getRemainsCash1() > 0) {
                        or.getIndicatorPhoneSach().get(i).setRemainsCash1(or.getIndicatorPhoneSach().get(i).getRemainsCash1() - order.getOrder());
                    } else {
                        or.getIndicatorPhoneSach().get(i).setRemainsCash2(or.getIndicatorPhoneSach().get(i).getRemainsCash2() - order.getOrder());
                    }

                } else {
                    if (or.getIndicatorPhoneSach().get(i).getRemainsCash2() != null && or.getIndicatorPhoneSach().get(i).getRemainsCash2() > 0) {
                        or.getIndicatorPhoneSach().get(i).setRemainsCash2(or.getIndicatorPhoneSach().get(i).getRemainsCash2() - order.getOrder());
                    } else {
                        or.getIndicatorPhoneSach().get(i).setRemainsCash1(or.getIndicatorPhoneSach().get(i).getRemainsCash1() - order.getOrder());
                    }
                }
            }
        }
        for (int i = 0; i < or.getRemainsGroupShop().size(); i++) {
            if (or.getRemainsGroupShop().get(i).getGroup()!=null && or.getRemainsGroupShop().get(i).getShop().equals(order.getShop()) && or.getRemainsGroupShop().get(i).getGroup().equals(order.getGroup())) {
                or.getRemainsGroupShop().get(i).setRemainsShop(or.getRemainsGroupShop().get(i).getRemainsShop() + order.getOrder());
            }
        }
        for (int i = 0; i < or.getDistributionPhone().size(); i++) {
            if (or.getDistributionPhone().get(i).getGroup()!=null && or.getDistributionPhone().get(i).getShop().equals(order.getShop()) && or.getDistributionPhone().get(i).getGroup().equals(order.getGroup())) {
                or.getDistributionPhone().get(i).setOrder(or.getDistributionPhone().get(i).getOrder() == null ? order.getOrder() : or.getDistributionPhone().get(i).getOrder() + order.getOrder());
                for (int j = 0; j < or.getDistributionPhone().get(i).getAll().size(); j++) {
                    if (or.getDistributionPhone().get(i).getAll().get(j).getNomenclature().equals(order.getNomenclature())) {
                        or.getDistributionPhone().get(i).getAll().get(j).setOrder(or.getDistributionPhone().get(i).getAll().get(j).getOrder() == null ? order.getOrder() : or.getDistributionPhone().get(i).getAll().get(j).getOrder() + order.getOrder());
                    }
                }
            }
           if (or.getDistributionPhone().get(i).getGroup()!=null && or.getDistributionPhone().get(i).getGroup().equals(order.getGroup())) {
                or.getDistributionPhone().get(i).setRemainsCash1(or.getIndicatorPhoneShop().stream().filter(r -> r.getGroup().equals(order.getGroup())).mapToInt(OrderRecommendations::getRemainsCash1).sum());
                or.getDistributionPhone().get(i).setRemainsCash2(or.getIndicatorPhoneShop().stream().filter(r -> r.getGroup().equals(order.getGroup())).mapToInt(OrderRecommendations::getRemainsCash2).sum());
                for (int j = 0; j < or.getDistributionPhone().get(i).getAll().size(); j++) {
                    if (or.getDistributionPhone().get(i).getAll().get(j).getNomenclature().equals(order.getNomenclature())) {
                        or.getDistributionPhone().get(i).getAll().get(j).setRemainsCash1(or.getIndicatorPhoneSach().stream().filter(r -> r.getRemainsCash1() != null && r.getNomenclature().equals(order.getNomenclature())).mapToInt(OrderRecommendations::getRemainsCash1).sum());
                        or.getDistributionPhone().get(i).getAll().get(j).setRemainsCash2(or.getIndicatorPhoneSach().stream().filter(r -> r.getRemainsCash2() != null && r.getNomenclature().equals(order.getNomenclature())).mapToInt(OrderRecommendations::getRemainsCash2).sum());

                    }

                }
            }
        }
        return or;
    }
}
