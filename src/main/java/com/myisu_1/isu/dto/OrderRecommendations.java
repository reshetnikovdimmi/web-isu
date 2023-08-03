package com.myisu_1.isu.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderRecommendations {
    String shop;
    String group;
    String nomenclature;
    String view;
    Integer remainsCash1;
    Integer remainsCash2;
    Integer remainsShop;
    Long remainsShopL;
    Integer remainsPhone;
    Integer sale1;
    Integer sale6;
    Integer order;
    List<OrderRecommendations> all;


    public OrderRecommendations(String shop, String group, String nomenclature, String view, Integer remainsCash1, Integer remainsCash2, Long remainsShopL, Integer remainsPhone, Integer sale1, List<OrderRecommendations> all) {
        this.shop = shop;
        this.group = group;
        this.nomenclature = nomenclature;
        this.view = view;
        this.remainsCash1 = remainsCash1;
        this.remainsCash2 = remainsCash2;
        this.remainsShop = remainsShop;
        this.remainsShopL = remainsShopL;
        this.remainsPhone = remainsPhone;
        this.sale1 = sale1;
        this.sale6 = sale6;
        this.order = order;
        this.all = all;
    }



    public OrderRecommendations(String shop, String group, String view, Long remainsShopL) {
        this.shop = shop;
        this.group = group;
        this.view = view;
        this.remainsShopL = remainsShopL;

    }

    public OrderRecommendations(String shop, String group, String nomenclature, String view, Integer remainsShop) {
        this.shop = shop;
        this.group = group;
        this.nomenclature = nomenclature;
        this.view = view;
        this.remainsShop = remainsShop;
    }

    public OrderRecommendations(String group, String view, Long remainsShopL) {
        this.group = group;
        this.view = view;
        this.remainsShopL = remainsShopL;
    }

    public OrderRecommendations(String group, Long remainsShopL) {
        this.group = group;
        this.remainsShopL = remainsShopL;
    }

    public OrderRecommendations(String group, String view, Integer remainsCash1, Integer remainsCash2, Integer remainsShop, Integer remainsPhone, Integer sale1, Integer sale6, Integer order) {
        this.group = group;
        this.view = view;
        this.remainsCash1 = remainsCash1;
        this.remainsCash2 = remainsCash2;
        this.remainsShop = remainsShop;
        this.remainsPhone = remainsPhone;
        this.sale1 = sale1;
        this.sale6 = sale6;
        this.order = order;
    }

    public OrderRecommendations(String shop, String group, String view, Integer remainsShop, Integer remainsPhone, Integer sale1, Integer sale6, Integer order) {
        this.shop = shop;
        this.group = group;
        this.view = view;
        this.remainsShop = remainsShop;
        this.remainsPhone = remainsPhone;
        this.sale1 = sale1;
        this.sale6 = sale6;
        this.order = order;
    }

    public OrderRecommendations(String nomenclature, String view, Integer remainsShop) {
        this.nomenclature = nomenclature;
        this.view = view;
        this.remainsShop = remainsShop;
    }

    public OrderRecommendations(String group, String nomenclature, String view, Integer remainsShop) {

        this.group = group;
        this.nomenclature = nomenclature;
        this.view = view;
        this.remainsShop = remainsShop;
    }
    public OrderRecommendations(String shop, String group, String nomenclature, String view, Integer remainsShop, Integer sale1) {
        this.shop = shop;
        this.group = group;
        this.nomenclature = nomenclature;
        this.view = view;
        this.remainsShop = remainsShop;
        this.sale1 = sale1;

    }
    public OrderRecommendations( String group, String nomenclature, String view, Integer remainsShop, Integer sale1, Integer sale6) {

        this.group = group;
        this.nomenclature = nomenclature;
        this.view = view;
        this.remainsShop = remainsShop;
        this.sale1 = sale1;
        this.sale6 = sale6;
    }
}
