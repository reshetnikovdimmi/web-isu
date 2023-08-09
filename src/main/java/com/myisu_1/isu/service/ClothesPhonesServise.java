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
    private PostRepositoriy shop;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;


    List<OrderRecommendations> remainsCash;
    List<String> cash;
    List<String> shops;
    List<OrderRecommendations> groupSaleRemains;
    List<OrderRecommendations> d;
    List<OrderRecommendations> remainsGroupShop;
    OrderRecommendations orderRecommendations = null;

    public Object remainsGroupCash() {
        groupSaleRemains = new ArrayList<>();
        List<OrderRecommendations> remainsShopClothingGroup = clothingMatchingTableRepositoriy.getRemainsShopClothingGroup();
        List<OrderRecommendations> sale1ShopClothingGroup = clothingMatchingTableRepositoriy.getSale1ShopClothingGroup();
        List<OrderRecommendations> sale6ShopClothingGroup = clothingMatchingTableRepositoriy.getSale6ShopClothingGroup();
        List<OrderRecommendations> remainsShopPhoneGroup = phoneRepositoriy.getRemainsShopPhoneGroup();

        List<OrderRecommendations> remainsShopClothingGroupAll = clothingMatchingTableRepositoriy.getRemainsShopClothingGroupAll();
        List<OrderRecommendations> sale1ShopClothingGroupAll = clothingMatchingTableRepositoriy.getSale1ShopClothingGroupAll();
        List<OrderRecommendations> sale6ShopClothingGroupAll = clothingMatchingTableRepositoriy.getSale6ShopClothingGroupAll();
       // List<OrderRecommendations> remainsShopPhoneGroupAll = phoneRepositoriy.getRemainsShopPhoneGroup();

        List<String> phone = phoneRepositoriy.getBrendDisting();
        shops = shop.getShopList();
        List<String> view = new ArrayList<>();
        view.add("Glass");
        view.add("Case");
        view.add("CoverBook");
        for (String s : shops) {
            for (String p : phone) {
                for (String v : view) {
                    groupSaleRemains.add(new OrderRecommendations(s, p, v, searchRemainsShopL(s, p, v, remainsShopClothingGroup), searchRemainsShopL(s, p, v, remainsShopPhoneGroup), searchRemainsShopL(s, p, v, sale1ShopClothingGroup), searchRemainsShopL(s, p, v, sale6ShopClothingGroup), 555));
                }
            }
        }

        List<OrderRecommendations> groupRemainsCash = new ArrayList<>();
        cash = shop.getWarehouseList();
        for (OrderRecommendations o : groupSaleRemains) {
            OrderRecommendations remainsGroupCash = new OrderRecommendations();
            remainsGroupCash.setGroup(o.getGroup());
            if (cash.get(0).equals(o.getShop())) {
                remainsGroupCash.setRemainsCash1(o.getRemainsShop());
                remainsGroupCash.setView(o.getView());
            }
            if (cash.get(1).equals(o.getShop())) {
                remainsGroupCash.setRemainsCash2(o.getRemainsShop());
                remainsGroupCash.setView(o.getView());
            }
            groupRemainsCash.add(remainsGroupCash);
        }
        return groupRemainsCash;
    }

    private Integer searchRemainsShopL(String s, String p, String v, List<OrderRecommendations> remains) {
        Integer rem = null;
        for (OrderRecommendations o : remains) {
            if (s.equals(o.getShop()) && p.equals(o.getGroup()) && v.equals(o.getView())) {
                rem = Math.toIntExact(o.getRemainsShopL());
            }
            if (s.equals(o.getShop()) && p.equals(o.getGroup()) && o.getView() == null) {
                rem = Math.toIntExact(o.getRemainsShopL());
            }
        }
        return rem;
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
            if (p.equals(o.getShop())) {
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

        remainsGroupShop = new ArrayList<>();
        for (int i=0;i<groupSaleRemains.size();i++) {
            if (models.equals(groupSaleRemains.get(i).getGroup())) {
                remainsGroupShop.add(groupSaleRemains.get(i));
            }
        }
        return remainsGroupShop;
    }


    public List<OrderRecommendations> remainsCash(String models) {
        addAll(cash, models);
        List<OrderRecommendations> remCash = new ArrayList<>();
      for (OrderRecommendations o: groupSaleRemains) {

            if (o.getAll()!=null&&o.getGroup().equals(models)) {
                OrderRecommendations remainsGroupCash = new OrderRecommendations();
                for (OrderRecommendations all:o.getAll()){

                    if (cash.get(0).equals(all.getShop())) {
                        remainsGroupCash.setNomenclature(all.getNomenclature());
                        remainsGroupCash.setView(all.getView());
                        remainsGroupCash.setRemainsCash1(all.getRemainsShop());
                    }
                    if (cash.get(1).equals(all.getShop())) {
                        remainsGroupCash.setNomenclature(all.getNomenclature());
                        remainsGroupCash.setView(all.getView());
                        remainsGroupCash.setRemainsCash2(all.getRemainsShop());
                    }
                    remCash.add(remainsGroupCash);
                }
            }
        }
        return remCash;
    }

    public  void addAll(List<String> shop, String models){

        for (String s:shop){
            for (int i=0;i<groupSaleRemains.size();i++) {
                if (s.equals(groupSaleRemains.get(i).getShop())&&groupSaleRemains.get(i).getAll()==null) {
                    List<OrderRecommendations> remShop = new ArrayList<>();
                    List<String> n = clothingMatchingTableRepositoriy.getClothingList(groupSaleRemains.get(i).getGroup(),groupSaleRemains.get(i).getView());
                    for (String nom:n){
                        if (nom!=null){
                            remShop.add(new OrderRecommendations(s,groupSaleRemains.get(i).getGroup(),nom,groupSaleRemains.get(i).getView(),11,22,3,null,null,3,4,5,null));
                        }
                    }
                    groupSaleRemains.set(i,new OrderRecommendations(groupSaleRemains.get(i).getShop(),groupSaleRemains.get(i).getGroup(),null,groupSaleRemains.get(i).getView(),1,2,groupSaleRemains.get(i).getRemainsShop(),4L,groupSaleRemains.get(i).getRemainsPhone(),groupSaleRemains.get(i).getSale1(),groupSaleRemains.get(i).getSale6(),555,remShop));
                }
            }
        }
    }

    public Object remainsGroupShopAll(String models, String shop) {
        List<String> ss = Collections.singletonList(shop);
        addAll(ss, models);
        List<OrderRecommendations> order = new ArrayList<>();
        for (OrderRecommendations o : groupSaleRemains) {
            if (shop.equals(o.getShop())) {
                order.add(o);
            }
        }

        return order;
    }


    public List<OrderRecommendations> updatingAllTables(String shop, String nomenkl, Integer kol) {
        System.out.println(shop + "--" + nomenkl + "--" + kol);
        return null;
    }
}

