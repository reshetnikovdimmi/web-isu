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

    public Object remainsGroupCash() {


        List<String> phone = phoneRepositoriy.getBrendDisting();
        remainsGroupCashClothesList = new ArrayList<>();
        List<String> listCash = cash.getWarehouseList();
        List<RemainsGroupCash> remainsGroupCash1ClothesList = clothingMatchingTableRepositoriy.getRemainsCash(listCash.get(0));
        List<RemainsGroupCash> remainsGroupCash2ClothesList = clothingMatchingTableRepositoriy.getRemainsCash(listCash.get(1));
        for (String p : phone) {
            RemainsGroupCash remainsGroupCashClothes = new RemainsGroupCash();
            remainsGroupCashClothes.setGroup(p);
            for (RemainsGroupCash cash1 : remainsGroupCash1ClothesList) {
                if (p.equals(cash1.getGroup())) {
                    remainsGroupCashClothes.setCash1(cash1.getCash1());
                    remainsGroupCashClothes.setView(cash1.getView());
                }
            }
            for (RemainsGroupCash cash2 : remainsGroupCash2ClothesList) {
                if (p.equals(cash2.getGroup())) {
                    remainsGroupCashClothes.setCash1(cash2.getCash1());
                    remainsGroupCashClothes.setView(cash2.getView());
                }
            }
            remainsGroupCashClothesList.add(remainsGroupCashClothes);
        }

        return remainsGroupCashClothesList;
    }

    public Object orderRecommendations() {

        List<OrderRecommendations> orderRecommendationsClothesList = new ArrayList<>();
        List<String> phone = phoneRepositoriy.getBrendDisting();
        List<String> shop = cash.getShopList();
        long start = System.currentTimeMillis();
        for (String s : shop) {

         // System.out.println(s + "--" + indicatorsSaleRenains(s, phone));
        //  indicatorsSaleRenains(s, phone);
          // System.out.println(remainsShop.get(s).get("Glass"));
        }
      indicatorsSaleRenains("1-Основной склад", phone);
        for (String p : phone) {
            orderRecommendationsClothesList.add(new OrderRecommendations(p, "Glass", 5, 59, 6, 5, 64, 4, 5));
            orderRecommendationsClothesList.add(new OrderRecommendations(p, "Case", 75, 67, 5, 53, 65465, 74, 57));
            orderRecommendationsClothesList.add(new OrderRecommendations(p, "CoverBook", 85, 66, 66, 25, 7, 47, 35));
        }
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        System.out.println(df.format(new Date(timeWorkCode)));
        return orderRecommendationsClothesList;
    }

    private Map<String, Map<String, Map<String, List<OrderRecommendations>>>> indicatorsSaleRenains(String s, List<String> p) {
        Map<String, Map<String, List<OrderRecommendations>>> view = new HashMap<>();
        List<OrderRecommendations> indicatorsRemainsGlass = clothingMatchingTableRepositoriy.getRemainsShopPhone(s, p, "Glass");

        List<OrderRecommendations> indicatorsSale1Glass = clothingMatchingTableRepositoriy.getSale1ShopPhone(s, p, "Glass");
        List<OrderRecommendations> indicatorsSale6Glass = clothingMatchingTableRepositoriy.getSale6ShopPhone(s, p, "Glass");
        view.put("Glass", remainsPhone(p,indicatorsRemainsGlass,indicatorsSale1Glass,indicatorsSale6Glass,"Glass",s));
        List<OrderRecommendations> indicatorsRemainsCase = clothingMatchingTableRepositoriy.getRemainsShopPhone(s, p, "Case");
        List<OrderRecommendations> indicatorsSale1Case = clothingMatchingTableRepositoriy.getSale1ShopPhone(s, p, "Case");
        List<OrderRecommendations> indicatorsSale6Case = clothingMatchingTableRepositoriy.getSale6ShopPhone(s, p, "Case");
        view.put("Case", remainsPhone(p,indicatorsRemainsCase,indicatorsSale1Case,indicatorsSale6Case,"Case",s));
       List<OrderRecommendations> indicatorsRemainsCoverBook = clothingMatchingTableRepositoriy.getRemainsShopPhone(s, p, "CoverBook");
        List<OrderRecommendations> indicatorsSale1CoverBook = clothingMatchingTableRepositoriy.getSale1ShopPhone(s, p, "CoverBook");
        List<OrderRecommendations> indicatorsSale6CoverBook = clothingMatchingTableRepositoriy.getSale6ShopPhone(s, p, "CoverBook");
        view.put("CoverBook", remainsPhone(p,indicatorsRemainsCoverBook,indicatorsSale1CoverBook,indicatorsSale6CoverBook,"CoverBook",s));
        remainsShop.put(s, view);
        return remainsShop;
    }

    private Map<String, List<OrderRecommendations>> remainsPhone(List<String> p, List<OrderRecommendations> indicatorsRemains, List<OrderRecommendations> indicatorsSale1, List<OrderRecommendations> indicatorsSale6, String c,String s) {
        Map<String, List<OrderRecommendations>> remainsPhone = new HashMap<>();

        for (String phone : p) {
            List<String> phoneClothes = clothingMatchingTableRepositoriy.getClothingList(phone,c);
            List<OrderRecommendations> a = new ArrayList<>();
            OrderRecommendations orderRecommendations = new OrderRecommendations();
            for (String clothes : phoneClothes) {

                for (OrderRecommendations remains : indicatorsRemains) {
                    if(clothes.equals(remains.getNomenclature())){

                        orderRecommendations.setGroup(phone);
                        orderRecommendations.setNomenclature(clothes);
                        orderRecommendations.setRemainsShop(remains.getRemainsShop());
                    }
                }
               for (OrderRecommendations sale1 : indicatorsSale1) {
                    if(clothes.equals(sale1.getNomenclature())){
                        orderRecommendations.setGroup(phone);
                        orderRecommendations.setNomenclature(clothes);
                        orderRecommendations.setSale1(sale1.getRemainsShop());
                    }
                }
                for (OrderRecommendations sale6 : indicatorsSale6) {
                    if(clothes.equals(sale6.getNomenclature())){
                        orderRecommendations.setGroup(phone);
                        orderRecommendations.setNomenclature(clothes);
                        orderRecommendations.setSale6(sale6.getRemainsShop());
                    }
                }
                a.add(orderRecommendations);
            }





            remainsPhone.put(phone, a);
            orderRecommendations = new OrderRecommendations();
            orderRecommendations.setRemainsShop(clothingMatchingTableRepositoriy.getRemainsShopPhoneTotal(s, phone, c));
            orderRecommendations.setSale1(clothingMatchingTableRepositoriy.getSale1ShopPhoneTotal(s, phone, c));
            orderRecommendations.setSale1(clothingMatchingTableRepositoriy.getSale6ShopPhoneTotal(s, phone, c));

            remainsPhone.put("total"+c, a);
        }

        return remainsPhone;
    }
}
