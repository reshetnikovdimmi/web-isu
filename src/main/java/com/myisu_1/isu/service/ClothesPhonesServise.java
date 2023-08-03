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
import org.springframework.http.ResponseEntity;
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

    List<OrderRecommendations> remainsCash;

    List<RemainsGroupCash> remainsGroupCashClothesList;
    List<OrderRecommendations> d;
    List<OrderRecommendations> c;

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
        c = new ArrayList<>();
        for (OrderRecommendations o : d) {
            if (models.equals(o.getGroup())) {
                c.add(o);
            }
        }

        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));

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
        List<OrderRecommendations> orderRecommendationsClothesList;

        remainsCash = new ArrayList<>();
        List<String> listCash = cash.getWarehouseList();
        orderRecommendationsClothesList = clothingMatchingTableRepositoriy.getRemainsNomenclatureSohp(listCash);

        for (OrderRecommendations o : orderRecommendationsClothesList) {

            OrderRecommendations remainsGroupCash = new OrderRecommendations();

            if (listCash.get(0).equals(o.getShop())) {
                remainsGroupCash.setShop(o.getShop());
                remainsGroupCash.setGroup(o.getGroup());
                remainsGroupCash.setNomenclature(o.getNomenclature());
                remainsGroupCash.setRemainsCash1(o.getRemainsShop());
                remainsGroupCash.setView(o.getView());
            }
            if (listCash.get(1).equals(o.getShop())) {
                remainsGroupCash.setShop(o.getShop());
                remainsGroupCash.setGroup(o.getGroup());
                remainsGroupCash.setNomenclature(o.getNomenclature());
                remainsGroupCash.setRemainsCash2(o.getRemainsShop());
                remainsGroupCash.setView(o.getView());
            }
            remainsCash.add(remainsGroupCash);
        }


        List<OrderRecommendations> remainsCashModels = new ArrayList<>();
        for (OrderRecommendations o : remainsCash) {

            if (models.equals(o.getGroup())) {

                remainsCashModels.add(o);
            }
        }
        return remainsCashModels;
    }

    public Object remainsGroupShopAll(String models, String shop) {
        List<OrderRecommendations> order = new ArrayList<>();
        for (OrderRecommendations or : d) {
            if (or.getShop().equals(shop)) {
                order.add(new OrderRecommendations(null, or.getGroup(), null, or.getView(), searchRemainsGroupCash(or.getGroup(),or.getView()), null, or.getRemainsShop(), null, or.getRemainsPhone(), or.getSale1(), or.getSale6(), null, remaisAll(or.getGroup(), shop, or.getView())));
            }
        }
        return order;
    }

    private Integer searchRemainsGroupCash(String models, String view) {
        Integer remCash1 = null;
        for (RemainsGroupCash r : remainsGroupCashClothesList) {
            if (r.getCash()!=null && r.getGroup().equals(models)&&r.getView().equals(view)) {
                remCash1= Math.toIntExact(r.getCash());
            }
        }
        return remCash1;
    }

    private List<OrderRecommendations> remaisAll(String models, String shop, String view) {
        List<OrderRecommendations> rem = new ArrayList<>();
        List<String> nomenklatureGroup = clothingMatchingTableRepositoriy.getNomenklatureGroup(models, view);
        List<OrderRecommendations> remainsAll = clothingMatchingTableRepositoriy.getRemainsNomenclatureSohpAll(shop, models, view);
        List<OrderRecommendations> sale1All = clothingMatchingTableRepositoriy.getSale1NomenclatureSohpAll(shop, models, view);
        List<OrderRecommendations> sale6All = clothingMatchingTableRepositoriy.getSale6NomenclatureSohpAll(shop, models, view);
        for (String n : nomenklatureGroup) {

            rem.add(new OrderRecommendations(null, null, n, null, searchRemCashAll(n,view), null, serchRemainAll(n, remainsAll), null, null, serchRemainAll(n, sale1All), serchRemainAll(n, sale6All), null, null));
        }

        return rem;
    }

    private Integer searchRemCashAll(String n, String view) {
        Integer renCashNom = null;

      for ( OrderRecommendations o:remainsCash){
          if (o.getRemainsCash1()!=null && n.equals(o.getNomenclature())&& view.equals(o.getView())){
              renCashNom = o.getRemainsCash1();
          }
      }
        return renCashNom;
    }

    private Integer serchRemainAll(String n, List<OrderRecommendations> remainsAll) {
        Integer sum = null;
        for (OrderRecommendations o : remainsAll) {

            if (n.equals(o.getNomenclature())) {

                sum = o.getRemainsShop();
            }
        }
        return sum;
    }

    public List<OrderRecommendations> updatingAllTables(String shop, String nomenkl, Integer kol) {
        List<OrderRecommendations> sale1All = new ArrayList<>();
        sale1All.add(new OrderRecommendations(null,null,null,null,null));

        return sale1All;
    }
}

