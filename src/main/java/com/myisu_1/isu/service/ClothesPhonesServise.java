package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.distribution.AnalysisDistribution;
import com.myisu_1.isu.repo.ClothesForPhonesRepositoriy;
import com.myisu_1.isu.repo.ClothingMatchingTableRepositoriy;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.PostRepositoriy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Data
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
        remainsNomenclature.forEach(System.out::println);
        warehouse = shop.getShopList();
        sale1Nomenclature = clothingMatchingTableRepositoriy.getSale1ShopClothingGroupAll();
        sale6Nomenclature = clothingMatchingTableRepositoriy.getSale6ShopClothingGroupAll();
        phoneSmarts = clothingMatchingTableRepositoriy.phoneSmar();

        remainsCashGroup(clothingMatchingTableRepositoriy.getGroupViewSim());
        remainsNomenclatureSach(clothingMatchingTableRepositoriy.getNameRainbows());
        indicatorsPhoneShopGroup(clothingMatchingTableRepositoriy.getDistributionModelDISTINCT(), null);

     //   distributionPhone(simAndRtkTableRepositoriy.getGroupViewSim());
        return or;
    }







    public OrderRecommendations remainsGroupShop() {


        return or;
    }


    public List<OrderRecommendations> remainsCash(String models) {

        return null;
    }

    public void addAll(List<String> shop, String models) {

        for (String s : shop) {
            for (int i = 0; i < groupSaleRemains.size(); i++) {
                if (s.equals(groupSaleRemains.get(i).getShop()) && groupSaleRemains.get(i).getAll() == null) {
                    List<OrderRecommendations> remShop = new ArrayList<>();
                    List<String> n = clothingMatchingTableRepositoriy.getClothingList(groupSaleRemains.get(i).getGroup(), groupSaleRemains.get(i).getView());
                    for (String nom : n) {
                        if (nom != null) {
                            remShop.add(new OrderRecommendations(s, groupSaleRemains.get(i).getGroup(), nom, groupSaleRemains.get(i).getView(), remainsCashN1(cash.get(0),groupSaleRemains.get(i).getGroup(),nom,groupSaleRemains.get(i).getView(),remainsShopClothingGroupAll), remainsCashN(cash.get(1),groupSaleRemains.get(i).getGroup(),nom,groupSaleRemains.get(i).getView(),remainsShopClothingGroupAll), remainsCashN(s,groupSaleRemains.get(i).getGroup(),nom,groupSaleRemains.get(i).getView(),remainsShopClothingGroupAll), null, null, remainsCashN(s,groupSaleRemains.get(i).getGroup(),nom,groupSaleRemains.get(i).getView(),sale1ShopClothingGroupAll), remainsCashN(s,groupSaleRemains.get(i).getGroup(),nom,groupSaleRemains.get(i).getView(),sale6ShopClothingGroupAll), null, null));
                        }
                    }
          //          groupSaleRemains.set(i, new OrderRecommendations(groupSaleRemains.get(i).getShop(), groupSaleRemains.get(i).getGroup(),null, groupSaleRemains.get(i).getView(), groupSaleRemains.get(i).getRemainsCash1(), groupSaleRemains.get(i).getRemainsCash2(),  groupSaleRemains.get(i).getRemainsShop(), null, groupSaleRemains.get(i).getRemainsPhone(), groupSaleRemains.get(i).getSale1(), groupSaleRemains.get(i).getSale6(), groupSaleRemains.get(i).getOrder(), remShop, null));
                }
            }
        }
    }

    private Integer remainsCashN1(String s, String group, String nom, String view, List<OrderRecommendations> remainsShopClothingGroupAll) {
        for (OrderRecommendations o:groupSaleRemains){
            if(cash.get(0).equals(o.getShop())&&group.equals(o.getGroup())&&view.equals(o.getView())&&o.getAll()!=null){
                for (OrderRecommendations a:o.getAll()){
                    if (nom.equals(a.getNomenclature())){
                        return a.getRemainsCash1();
                    }
                }
            }
        }
        return remainsCashN(s,group,nom,view,remainsShopClothingGroupAll);
    }

    private Integer remainsCashN(String s,String group, String nom, String view,List<OrderRecommendations> sale) {
        Integer rem = null;
        for (OrderRecommendations o : sale) {
            if (s.equals(o.getShop()) && group.equals(o.getGroup()) && view.equals(o.getView())&&nom.equals(o.getNomenclature())) {
                rem = Math.toIntExact(o.getRemainsShop());
            }
        }
        return rem;
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


    public OrderRecommendations updatingAllTables(String shop, String models, String nomenkl, Integer kol) {
        String view = clothingMatchingTableRepositoriy.getView(nomenkl);
        regulationCashBalances(models,nomenkl,view,kol,shop);

        OrderRecommendations rem = null;
        for (int i = 0; i < groupSaleRemains.size(); i++) {
            if (shop.equals(groupSaleRemains.get(i).getShop()) && groupSaleRemains.get(i).getGroup().equals(models)&& view.equals(groupSaleRemains.get(i).getView())&&groupSaleRemains.get(i).getAll()!=null){
                List<OrderRecommendations> remShop = new ArrayList<>();
                for (int j = 0;j<groupSaleRemains.get(i).getAll().size();j++){
                        remShop.add(groupSaleRemains.get(i).getAll().get(j));
               }
             //   rem = new OrderRecommendations(groupSaleRemains.get(i).getShop(), groupSaleRemains.get(i).getGroup(),null, groupSaleRemains.get(i).getView(),groupSaleRemains.get(i).getRemainsCash1(), groupSaleRemains.get(i).getRemainsCash2(),  groupSaleRemains.get(i).getRemainsShop()==null?kol:groupSaleRemains.get(i).getRemainsShop()+kol, null, groupSaleRemains.get(i).getRemainsPhone(), groupSaleRemains.get(i).getSale1(), groupSaleRemains.get(i).getSale6(), groupSaleRemains.get(i).getOrder()==null?kol:groupSaleRemains.get(i).getOrder()+kol, remShop, null);

                groupSaleRemains.set(i, rem);
            }
        }

        return rem;
    }



    private Integer regulationCashBalances(String group, String nomenkl, String view, int in, String shop) {
        for (int i =0;i<groupSaleRemains.size();i++){
            if(view!=null && group.equals(groupSaleRemains.get(i).getGroup())&&view.equals(groupSaleRemains.get(i).getView())){
                List<OrderRecommendations> all = groupSaleRemains.get(i).getAll();
                if (groupSaleRemains.get(i).getAll()!=null){
                    for (int j =0;j<all.size();j++){
                        if (groupSaleRemains.get(i).getAll().get(j).getNomenclature().equals(nomenkl)){

                            all.set(j,new OrderRecommendations(all.get(j).getShop(), all.get(j).getGroup(), all.get(j).getNomenclature(), all.get(j).getView(), all.get(j).getRemainsCash1()-in, all.get(j).getRemainsCash2(), all.get(j).getRemainsShop(), null, null, all.get(j).getSale1(), all.get(j).getSale6(), settingOrder(all.get(j),shop,in), null));
                        }

                    }
                }
         //       groupSaleRemains.set(i,new OrderRecommendations(groupSaleRemains.get(i).getShop(), groupSaleRemains.get(i).getGroup(),groupSaleRemains.get(i).getNomenclature(), groupSaleRemains.get(i).getView(), groupSaleRemains.get(i).getRemainsCash1()==null?in:groupSaleRemains.get(i).getRemainsCash1()-in, groupSaleRemains.get(i).getRemainsCash2(),  groupSaleRemains.get(i).getRemainsShop(), null, groupSaleRemains.get(i).getRemainsPhone(), groupSaleRemains.get(i).getSale1(), groupSaleRemains.get(i).getSale6(), groupSaleRemains.get(i).getOrder(), all, null));
            }
        }

        return in;
    }

    private Integer settingOrder(OrderRecommendations o, String shop, int in) {
        Integer order = null;
        if(o.getShop().equals(shop)){

            if (o.getOrder()==null){
                order = in;
            }else {
                order +=in;
            }
        }else {
            order = o.getOrder();
        }

        return order;
    }

    public List<OrderRecommendations> exselFileExporteClotingPhone() {

        return groupSaleRemains;
    }
}

