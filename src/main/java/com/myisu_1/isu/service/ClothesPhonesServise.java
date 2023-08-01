package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.dto.RemainsGroupCash;
import com.myisu_1.isu.repo.ClothingMatchingTableRepositoriy;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.PostRepositoriy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class ClothesPhonesServise {
    @Autowired
    private ClothingMatchingTableRepositoriy clothingMatchingTableRepositoriy;
    @Autowired
    private PostRepositoriy cash;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;

    Map<String, Map<String, Map<String, List<OrderRecommendations>>>> remainsShop = new HashMap<>();

    List<RemainsGroupCash> remainsGroupCashClothesList;
    List<OrderRecommendations> d;

    public Object remainsGroupCash() {

        List<String> phone = phoneRepositoriy.getBrendDisting();
        remainsGroupCashClothesList = new ArrayList<>();
        List<String> listCash = cash.getWarehouseList();

        List<String> view = new ArrayList<>();
        view.add("Glass");
        view.add("Case");
        view.add("CoverBook");
        for (String p : phone) {
            List<RemainsGroupCash> remainsGroupCash1ClothesList = clothingMatchingTableRepositoriy.getRemainsCash(listCash, phone);
            for (String v : view) {
                RemainsGroupCash remainsGroupCashClothes = new RemainsGroupCash();
                remainsGroupCashClothes.setGroup(p);
                remainsGroupCashClothes.setView(v);
                for (RemainsGroupCash r : remainsGroupCash1ClothesList) {
                    if (r.getGroup().equals(p) && v.equals(r.getView())) {
                        remainsGroupCashClothes.setCash(r.getCash());
                    }
                }
                remainsGroupCashClothesList.add(remainsGroupCashClothes);
            }
        }
        return remainsGroupCashClothesList;
    }

    public Object orderRecommendations() {

        List<OrderRecommendations> orderRecommendationsClothesList = clothingMatchingTableRepositoriy.getRemainsShopPhoneAll();
        List<OrderRecommendations> orderRecommendationsClothesList1 = clothingMatchingTableRepositoriy.getSale1ShopPhoneAll();
        List<OrderRecommendations> orderRecommendationsClothesList6 = clothingMatchingTableRepositoriy.getSale6ShopPhoneAll();
        List<OrderRecommendations> orderRecommendationsClothesList7 = phoneRepositoriy.getRemainsPhoneAll();

        List<OrderRecommendations> a = new ArrayList<>();
        List<String> phone = phoneRepositoriy.getBrendDisting();
        List<String> view = new ArrayList<>();
        view.add("Glass");
        view.add("Case");
        view.add("CoverBook");
        for (String p : phone) {
            for (String v : view) {
                a.add(new OrderRecommendations(p, v, 5, 59, serchRemainsAll(p, v, orderRecommendationsClothesList), serchRemainsAll(p, orderRecommendationsClothesList7), serchRemainsAll(p, v, orderRecommendationsClothesList1), serchRemainsAll(p, v, orderRecommendationsClothesList6), 5));
            }
        }
        List<OrderRecommendations>  sortedEmployeeList = a.stream()
                .sorted(Comparator.comparing(OrderRecommendations::getRemainsShop,
                        Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
        return sortedEmployeeList;
    }

    private Integer serchRemainsAll(String p, List<OrderRecommendations> orderRecommendationsClothesList7) {
        for (OrderRecommendations o : orderRecommendationsClothesList7) {
            if (p.equals(o.getGroup())) {
                return Math.toIntExact(o.getRemainsShopL());
            }
        }
        return null;
    }

    private Integer serchRemainsAll(String p, String v, List<OrderRecommendations> orderRecommendationsClothesList) {
        for (OrderRecommendations o : orderRecommendationsClothesList) {
            if (p.equals(o.getGroup()) && v.equals(o.getView())) {
                return Math.toIntExact(o.getRemainsShopL());
            }
        }
        return null;
    }

    public Object remainsGroupShop(String models) {

        long start = System.currentTimeMillis();
        if (d == null) {
            List<String> phone = phoneRepositoriy.getBrendDisting();
            List<String> shop = cash.getShopList();

            List<OrderRecommendations> orderRecommendationsClothesList = clothingMatchingTableRepositoriy.getRemainsShopPhoneTotal();
            List<OrderRecommendations> orderRecommendationsClothesList1 = clothingMatchingTableRepositoriy.getSale1ShopPhoneTotal();
            List<OrderRecommendations> orderRecommendationsClothesList6 = clothingMatchingTableRepositoriy.getSale6ShopPhoneTotal();
            List<OrderRecommendations> orderRecommendationsClothesList7 = phoneRepositoriy.getRemainsPhoneTotal();

            d = new ArrayList<>();
            List<String> view = new ArrayList<>();
            view.add("Glass");
            view.add("Case");
            view.add("CoverBook");
            for (String s : shop) {
                for (String p : phone) {
                    for (String v : view) {
                        d.add(new OrderRecommendations(s, p, v, serchRemains(s, p, v, orderRecommendationsClothesList), serchRemains(s, p, orderRecommendationsClothesList7), serchRemains(s, p, v, orderRecommendationsClothesList1), serchRemains(s, p, v, orderRecommendationsClothesList6), 455));
                    }
                }
            }
        }
        List<OrderRecommendations> c = new ArrayList<>();
        for (OrderRecommendations o : d) {
            if (models.equals(o.getGroup())) {
                c.add(o);
            }
        }

        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        System.out.println(df.format(new Date(timeWorkCode)));
        return c;
    }

    private Integer serchRemains(String s, String p, List<OrderRecommendations> orderRecommendationsClothesList7) {
        for (OrderRecommendations o : orderRecommendationsClothesList7) {
            if (p.equals(o.getView()) && s.equals(o.getGroup())) {
                return Math.toIntExact(o.getRemainsShopL());
            }
        }
        return null;
    }

    private Integer serchRemains(String s, String p, String v, List<OrderRecommendations> orderRecommendationsClothesList) {
        for (OrderRecommendations o : orderRecommendationsClothesList) {
            if (s.equals(o.getShop()) && p.equals(o.getGroup()) && v.equals(o.getView())) {
                return Math.toIntExact(o.getRemainsShopL());
            }
        }
        return null;
    }

    public Object remainsCash(String models) {
        List<String> listCash = cash.getWarehouseList();
        List<OrderRecommendations> orderRecommendationsClothesList = clothingMatchingTableRepositoriy.getRemainsNomenclatureSohp(listCash, models);
        List<String> nomenclature = clothingMatchingTableRepositoriy.getRemainsNomenclatureModels(models);
        List<OrderRecommendations> remainsCash = new ArrayList<>();
        List<String> view = new ArrayList<>();
        view.add("Glass");
        view.add("Case");
        view.add("CoverBook");
        for (String n : nomenclature) {

            for (String v : view) {
                for (OrderRecommendations o : orderRecommendationsClothesList) {
                    if (o.getNomenclature().equals(n)&&v.equals(o.getView())) {
                        OrderRecommendations remainsGroupCash = new OrderRecommendations();

                        if (listCash.get(0).equals(o.getGroup())){
                            remainsGroupCash.setNomenclature(o.getNomenclature());
                            remainsGroupCash.setRemainsCash1(o.getRemainsShop());
                            remainsGroupCash.setView(o.getView());
                        }
                        if (listCash.get(1).equals(o.getGroup())){
                            remainsGroupCash.setNomenclature(o.getNomenclature());
                            remainsGroupCash.setRemainsCash2(o.getRemainsShop());
                            remainsGroupCash.setView(o.getView());
                        }
                        remainsCash.add(remainsGroupCash);
                    }
                }
            }
        }

        return remainsCash;
    }

    public Object remainsGroupShopAll(String models, String shop) {
        List<OrderRecommendations> order = new ArrayList<>();
        List<OrderRecommendations> all= new ArrayList<>();

        all.add(new OrderRecommendations("esgeew","wfqwf","Glass",454,54,564,4,1));
        all.add(new OrderRecommendations("esgzvcew","wfqwf","Glass",454,54,564,4,1));
        all.add(new OrderRecommendations("esgaew","wfqwf","Glass",454,54,564,4,1));
        all.add(new OrderRecommendations("esgdsvew","wfqwf","Glass",454,54,564,4,1));
        order.add(new OrderRecommendations("total","wfqwf","Glass","Glass",454,54,564L,4,1,all));
        all.add(new OrderRecommendations("esgeew","wfqwf","Glass",454,54,564,4,1));
        all.add(new OrderRecommendations("esgzvcew","wfqwf","Glass",454,54,564,4,1));
        all.add(new OrderRecommendations("esgaew","wfqwf","Glass",454,54,564,4,1));
        all.add(new OrderRecommendations("esgdsvew","wfqwf","Glass",454,54,564,4,1));
        order.add(new OrderRecommendations("total","wfqwf","Glass","Glass",454,54,564L,4,2,all));
        all.add(new OrderRecommendations("esgeew","wfqwf","Glass",454,54,564,4,1));
        all.add(new OrderRecommendations("esgzvcew","wfqwf","Glass",454,54,564,4,1));
        all.add(new OrderRecommendations("esgaew","wfqwf","Glass",454,54,564,4,1));
        all.add(new OrderRecommendations("esgdsvew","wfqwf","Glass",454,54,564,4,1));
        order.add(new OrderRecommendations("total","wfqwf","Glass","Glass",454,54,564L,4,3,all));
        all.add(new OrderRecommendations("esgeew","wfqwf","Glass",454,54,564,4,1));
        all.add(new OrderRecommendations("esgzvcew","wfqwf","Glass",454,54,564,4,1));
        all.add(new OrderRecommendations("esgaew","wfqwf","Glass",454,54,564,4,1));
        all.add(new OrderRecommendations("esgdsvew","wfqwf","Glass",454,54,564,4,1));
        order.add(new OrderRecommendations("total","wfqwf","Glass","Glass",454,54,564L,4,4,all));
        return order;
    }
}

