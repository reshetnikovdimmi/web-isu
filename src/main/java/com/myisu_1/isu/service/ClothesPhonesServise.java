package com.myisu_1.isu.service;


import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.distribution.AnalysisDistribution;
import com.myisu_1.isu.repo.ClothesForPhonesRepositoriy;
import com.myisu_1.isu.repo.ClothingMatchingTableRepositoriy;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.PostRepositoriy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class ClothesPhonesServise extends AnalysisDistribution {
    @Autowired
    private ClothingMatchingTableRepositoriy clothingMatchingTableRepositoriy;
    @Autowired
    private PostRepositoriy shop;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private ClothesForPhonesRepositoriy clothesForPhonesRepositoriy;

    List<OrderRecommendations> remainsCashs;
    List<String> cash;
    List<String> shops;
    List<OrderRecommendations> groupSaleRemains;
    List<OrderRecommendations> groupRemainsCash;
    List<OrderRecommendations> d;
    List<OrderRecommendations> remainsGroupShop;
    OrderRecommendations orderRecommendations = null;
    List<OrderRecommendations> remainsShopClothingGroupAll;
    List<OrderRecommendations> sale1ShopClothingGroupAll;
    List<OrderRecommendations> sale6ShopClothingGroupAll;

    public Object remainsGroupCash() {
        or = new OrderRecommendations();
        remainsNomenclature = clothingMatchingTableRepositoriy.remainsSim();
        warehouse = shop.getShopList();
        sale1Nomenclature = clothingMatchingTableRepositoriy.getSale1ShopClothingGroupAll();
        sale6Nomenclature = clothingMatchingTableRepositoriy.getSale6ShopClothingGroupAll();
        phoneSmarts = clothingMatchingTableRepositoriy.phoneSmar();
        remainsCashGroup(clothingMatchingTableRepositoriy.getGroupViewSim());
        remainsNomenclatureSach(clothingMatchingTableRepositoriy.getNameRainbows());
        indicatorsPhoneShopGroup(clothingMatchingTableRepositoriy.getDistributionModelDISTINCT(), null);
        distributionPhone(clothingMatchingTableRepositoriy.getGroupViewSim());
        return or;
    }

    @Override
    public void remainsNomenclatureSach(List<String> all) {
        List<OrderRecommendations> remain = new ArrayList<>();
        List<OrderRecommendations> rem = null;
        for (String o : all) {

         rem = remainsNomenclature.stream().filter(r -> r.getShop().equals(warehouse.get(0)) || r.getShop().equals(warehouse.get(1)) && r.getNomenclature().equals(o)).collect(Collectors.toList());
        }
        assert rem != null;

        for (OrderRecommendations o:rem){
            OrderRecommendations dto = new OrderRecommendations();
               if (o.getShop().equals(warehouse.get(0))) {
                   dto.setNomenclature(o.getNomenclature());
                   dto.setRemainsCash1(o.getRemainsShop());
                   dto.setGroup(o.getGroup());
                   dto.setView(o.getView());
               }
               if (o.getShop().equals(warehouse.get(1))) {
                   dto.setNomenclature(o.getNomenclature());
                   dto.setRemainsCash2(o.getRemainsShop());
                   dto.setGroup(o.getGroup());
                   dto.setView(o.getView());
               }
               remain.add(dto);
           }



        or.setIndicatorPhoneSach(remain.stream().filter(r -> r.getGroup() != null).collect(Collectors.toList()));
    }

    public OrderRecommendations remainsGroupShop() {


        return or;
    }

    public List<OrderRecommendations> remainsGroupShopAll(String shop) {

        return or.getDistributionPhone().stream().filter(r -> r.getShop().equals(shop)).collect(Collectors.toList());
    }


    public OrderRecommendations updatingAllTables(String shop, String models, String nomenkl, Integer kol) {

        return null;
    }


    public OrderRecommendations exselFileExporteClotingPhone() {
        return or;
    }

    public OrderRecommendations distribution(OrderRecommendations order) {

        distributions(order,shop.getShopList(),null);
        return or;
    }
}

