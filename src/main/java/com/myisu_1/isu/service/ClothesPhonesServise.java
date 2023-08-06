package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.repo.ClothingMatchingTableRepositoriy;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.PostRepositoriy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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


    List<OrderRecommendations> remainsCash;
    List<OrderRecommendations> remainsGroupCashClothesList;
    List<OrderRecommendations> d;
    List<OrderRecommendations> c;

    public Object remainsGroupCash() {

        List<String> phone = phoneRepositoriy.getBrendDisting();
        remainsGroupCashClothesList = new ArrayList<>();
        List<String> listCash = cash.getWarehouseList();
        List<OrderRecommendations> remainsGroupCash1ClothesList = clothingMatchingTableRepositoriy.getRemainsCash(listCash, phone);
        List<OrderRecommendations> orderRecommendationsClothesList = clothingMatchingTableRepositoriy.getRemainsNomenclatureCash(listCash);
        OrderRecommendations orderRecommendations = null;

        for (OrderRecommendations o : remainsGroupCash1ClothesList) {
            orderRecommendations = new OrderRecommendations();
            remainsCash = new ArrayList<>();
            for (OrderRecommendations or : orderRecommendationsClothesList) {

                if (o.getGroup().equals(or.getGroup()) && o.getView().equals(or.getView())) {


                    OrderRecommendations remainsGroupCash = new OrderRecommendations();

                    if (listCash.get(0).equals(or.getShop())) {
                        remainsGroupCash.setShop(or.getShop());
                        remainsGroupCash.setGroup(or.getGroup());
                        remainsGroupCash.setNomenclature(or.getNomenclature());
                        remainsGroupCash.setRemainsCash1(or.getRemainsShop());
                        remainsGroupCash.setView(or.getView());
                    }
                    if (listCash.get(1).equals(or.getShop())) {
                        remainsGroupCash.setShop(or.getShop());
                        remainsGroupCash.setGroup(or.getGroup());
                        remainsGroupCash.setNomenclature(or.getNomenclature());
                        remainsGroupCash.setRemainsCash2(or.getRemainsShop());
                        remainsGroupCash.setView(or.getView());
                    }
                    remainsCash.add(remainsGroupCash);
                }

            }


            orderRecommendations.setAll(remainsCash);

            if (o.getShop().equals(listCash.get(0))) {
                orderRecommendations.setGroup(o.getGroup());
                orderRecommendations.setView(o.getView());
                orderRecommendations.setRemainsCash1(Math.toIntExact(o.getRemainsShopL()));

            }
            if (o.getShop().equals(listCash.get(1))) {
                orderRecommendations.setGroup(o.getGroup());
                orderRecommendations.setView(o.getView());
                orderRecommendations.setRemainsCash2(Math.toIntExact(o.getRemainsShopL()));

            }
            remainsGroupCashClothesList.add(orderRecommendations);
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
                a.add(new OrderRecommendations(p, v, serchRemainsAll(p, v, orderRecommendationsClothesList), serchRemainsAll(p, orderRecommendationsClothesList7), serchRemainsAll(p, v, orderRecommendationsClothesList1), serchRemainsAll(p, v, orderRecommendationsClothesList6), 5));
            }
        }
        List<OrderRecommendations> sortedEmployeeList = a.stream()
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

        c = new ArrayList<>();
        for (OrderRecommendations o : d) {
            if (models.equals(o.getGroup())) {
                c.add(o);
            }
        }
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

    public List<OrderRecommendations> remainsCash(String models) {
        List<OrderRecommendations> remCash = new ArrayList<>();
        for (OrderRecommendations o : remainsGroupCashClothesList) {
            if (o.getGroup().equals(models)) {

                remCash.addAll(o.getAll());
            }
        }
        return remCash;
    }

    public Object remainsGroupShopAll(String models, String shop) {
        List<OrderRecommendations> order = new ArrayList<>();
        for (OrderRecommendations or : d) {
            if (or.getShop().equals(shop)) {

                order.add(new OrderRecommendations(shop, or.getGroup(), null, or.getView(), serchRemainsCash1(or.getGroup(), or.getView(), remainsGroupCashClothesList), serchRemainsCash2(or.getGroup(), or.getView(), remainsGroupCashClothesList), or.getRemainsShop(), null, or.getRemainsPhone(), or.getSale1(), or.getSale6(), null, remaisAll(or.getGroup(), shop, or.getView())));
            }
        }


        return order;
    }

    private Integer serchRemainsCash2(String group, String view, List<OrderRecommendations> remainsGroupCashClothesList) {
        Integer sum = null;
        for (OrderRecommendations o : remainsGroupCashClothesList) {

            if (group.equals(o.getGroup()) && view.equals(o.getView())) {

                sum = o.getRemainsCash2();
            }
        }
        return sum;
    }

    private Integer serchRemainsCash1(String group, String view, List<OrderRecommendations> remainsGroupCashClothesList) {
        Integer sum = null;
        for (OrderRecommendations o : remainsGroupCashClothesList) {

            if (group.equals(o.getGroup()) && view.equals(o.getView())) {

                sum = o.getRemainsCash1();
            }
        }
        return sum;
    }

    private List<OrderRecommendations> remaisAll(String models, String shop, String view) {
        List<OrderRecommendations> rem = new ArrayList<>();
        List<String> nomenklatureGroup = clothingMatchingTableRepositoriy.getNomenklatureGroup(models, view);
        List<OrderRecommendations> remainsAll = clothingMatchingTableRepositoriy.getRemainsNomenclatureSohpAll(shop, models, view);
        List<OrderRecommendations> sale1All = clothingMatchingTableRepositoriy.getSale1NomenclatureSohpAll(shop, models, view);
        List<OrderRecommendations> sale6All = clothingMatchingTableRepositoriy.getSale6NomenclatureSohpAll(shop, models, view);
        for (String n : nomenklatureGroup) {

            rem.add(new OrderRecommendations(null, null, n, null, remainsCash1All(n,models), remainsCash2All(n,models), serchRemains(n, remainsAll), null, null, serchRemains(n, sale1All), serchRemains(n, sale6All), null, null));
        }

        return rem;
    }

    private Integer remainsCash1All(String n, String models) {
        Integer sum = null;

        for (OrderRecommendations o : remainsGroupCashClothesList) {
            if (o.getGroup().equals(models)) {
                for (OrderRecommendations or:o.getAll()){
                    if(or.getNomenclature().equals(n)){
                        sum = or.getRemainsCash1();
                    }
                }

            }
        }

        return sum;
    }

    private Integer remainsCash2All(String n, String models) {
        Integer sum = null;
        for (OrderRecommendations o : remainsGroupCashClothesList) {
            if (o.getGroup().equals(models)) {
                for (OrderRecommendations or:o.getAll()){
                    if(or.getNomenclature().equals(n)){
                        sum = or.getRemainsCash2();
                    }
                }

            }
        }
        return sum;
    }


    private Integer serchRemains(String n, List<OrderRecommendations> remainsAll) {
        Integer sum = null;
        for (OrderRecommendations o : remainsAll) {

            if (n.equals(o.getNomenclature())) {

                sum = o.getRemainsShop();
            }
        }
        return sum;
    }
}

