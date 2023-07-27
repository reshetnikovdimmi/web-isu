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
import java.util.List;

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


    OrderRecommendations orderRecommendationsClothes;
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
        orderRecommendationsClothes = new OrderRecommendations();
        List<OrderRecommendations> orderRecommendationsClothesList = new ArrayList<>();
        List<String> phone = phoneRepositoriy.getBrendDisting();
        for (String p:phone) {
            orderRecommendationsClothesList.add(new OrderRecommendations(p, "Glass", 5, 6, 5, 4, 5));


            orderRecommendationsClothesList.add(new OrderRecommendations(p, "Case", 75, 67, 53, 74, 57));


            orderRecommendationsClothesList.add(new OrderRecommendations(p, "CoverBook", 85, 66, 25, 47, 35));
        }
        return orderRecommendationsClothesList;
    }
}
