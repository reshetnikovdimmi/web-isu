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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    List<RemainsGroupCash> remainsGroupCashClothesList;
    public Object remainsGroupCash() {


        List<String> phone = phoneRepositoriy.getBrendDisting();
        remainsGroupCashClothesList = new ArrayList<>();
        List<String> listCash = cash.getWarehouseList();
        List<RemainsGroupCash> remainsGroupCash1ClothesList = clothingMatchingTableRepositoriy.getRemainsCash(listCash.get(0));
        List<RemainsGroupCash> remainsGroupCash2ClothesList = clothingMatchingTableRepositoriy.getRemainsCash(listCash.get(1));
        for (String p:phone){
            RemainsGroupCash remainsGroupCashClothes = new RemainsGroupCash();
            remainsGroupCashClothes.setGroup(p);
            for (RemainsGroupCash cash1:remainsGroupCash1ClothesList){
                if (p.equals(cash1.getGroup())){
                    remainsGroupCashClothes.setCash1(cash1.getCash1());
                    remainsGroupCashClothes.setView(cash1.getView());
                }
            }
            for (RemainsGroupCash cash2:remainsGroupCash2ClothesList){
                if (p.equals(cash2.getGroup())){
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

        for (String s:shop) {
             System.out.println(s+"--"+indicatorsSaleRenains(s,phone));
        }

        for (String p:phone) {
            orderRecommendationsClothesList.add(new OrderRecommendations(p, "Glass", 5,59, 6, 5,64, 4, 5));
            orderRecommendationsClothesList.add(new OrderRecommendations(p, "Case", 75, 67,5, 53,65465, 74, 57));
            orderRecommendationsClothesList.add(new OrderRecommendations(p, "CoverBook", 85, 66, 66,25, 7,47, 35));
        }
        return orderRecommendationsClothesList;
    }

    private List<OrderRecommendations> indicatorsSaleRenains(String s, List<String> p) {
        Map<String,List<OrderRecommendations> > view = new HashMap<>();
        Map<String,Map<String,List<OrderRecommendations>>> remainsPhone = new HashMap<>();
        Map<String,Map<String,Map<String,List<OrderRecommendations>>>> remainsShop = new HashMap<>();

        List<OrderRecommendations> indicatorsRemains = clothingMatchingTableRepositoriy.getRemainsShopPhone(s,p);
        List<OrderRecommendations> indicatorsSale1 = clothingMatchingTableRepositoriy.getSale1ShopPhone(s,p);
        List<OrderRecommendations> indicatorsSale6 = clothingMatchingTableRepositoriy.getSale6ShopPhone(s,p);
        List<OrderRecommendations> remainsPhones = clothingMatchingTableRepositoriy.getSale6ShopPhone(s,p);

        for (String phone:p) {
            for (OrderRecommendations orderRecommendations:indicatorsRemains) {
                if (orderRecommendations.getView().equals("Glass")&&orderRecommendations.getGroup().equals(phone)){

                }
            }
            for (OrderRecommendations orderRecommendations:indicatorsSale1) {
                if (orderRecommendations.getView().equals("Glass")&&orderRecommendations.getGroup().equals(phone)){

                }
            }
            for (OrderRecommendations orderRecommendations:indicatorsSale6) {
                if (orderRecommendations.getView().equals("Glass")&&orderRecommendations.getGroup().equals(phone)){

                }
            }
        }


        return indicatorsSale6;
    }
}
