package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.dto.RemainsGroupCash;
import com.myisu_1.isu.repo.ClothingMatchingTableRepositoriy;
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

    RemainsGroupCash remainsGroupCashClothes;
    OrderRecommendations orderRecommendationsClothes;

    public Object remainsGroupCash() {

        remainsGroupCashClothes = new RemainsGroupCash();
        List<RemainsGroupCash> remainsGroupCashClothesList = new ArrayList<>();
        List<String> listCash = cash.getWarehouseList();
        List<RemainsGroupCash> remainsGroupCash1ClothesList = clothingMatchingTableRepositoriy.getRemainsCash(listCash.get(0));
        remainsGroupCashClothesList.add(new RemainsGroupCash("esdge", "Glass", 5L, 6L));
        remainsGroupCashClothesList.add(new RemainsGroupCash("esыаdge", "Case", 7L, 8L));
        remainsGroupCashClothesList.add(new RemainsGroupCash("esфафыпdge", "CoverBook", 51L, 69L));
        remainsGroupCashClothesList.add(new RemainsGroupCash("esdge", "Glass", 5L, 6L));
        remainsGroupCashClothesList.add(new RemainsGroupCash("esыаdge", "Case", 7L, 8L));
        remainsGroupCashClothesList.add(new RemainsGroupCash("esфафыпdge", "CoverBook", 51L, 69L));
        remainsGroupCashClothesList.add(new RemainsGroupCash("esdge", "Glass", 5L, 6L));
        remainsGroupCashClothesList.add(new RemainsGroupCash("esыаdge", "Case", 7L, 8L));
        remainsGroupCashClothesList.add(new RemainsGroupCash("esфафыпdge", "CoverBook", 51L, 69L));
        return remainsGroupCash1ClothesList;
    }

    public Object orderRecommendations() {
        orderRecommendationsClothes = new OrderRecommendations();
        List<OrderRecommendations> orderRecommendationsClothesList = new ArrayList<>();
        orderRecommendationsClothesList.add(new OrderRecommendations("esdge", "Glass", 5, 6, 5, 4, 5));
        orderRecommendationsClothesList.add(new OrderRecommendations("esыаdge", "Case", 5, 6, 5, 4, 5));
        orderRecommendationsClothesList.add(new OrderRecommendations("esфафыпdge", "CoverBook", 5, 6, 5, 4, 5));
        orderRecommendationsClothesList.add(new OrderRecommendations("esdge", "Glass", 5, 6, 5, 4, 5));
        orderRecommendationsClothesList.add(new OrderRecommendations("esыаdge", "Case", 5, 6, 5, 4, 5));
        orderRecommendationsClothesList.add(new OrderRecommendations("esфафыпdge", "CoverBook", 5, 6, 5, 4, 5));
        orderRecommendationsClothesList.add(new OrderRecommendations("esdge", "Glass", 5, 6, 5, 4, 5));
        orderRecommendationsClothesList.add(new OrderRecommendations("esыаdge", "Case", 5, 6, 5, 4, 5));
        orderRecommendationsClothesList.add(new OrderRecommendations("esфафыпdge", "CoverBook", 5, 6, 5, 4, 5));
        return orderRecommendationsClothesList;
    }
}
