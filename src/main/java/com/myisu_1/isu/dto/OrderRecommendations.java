package com.myisu_1.isu.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderRecommendations {
    String group;
    String nomenclature;
    String view;
    Integer remainsCash1;
    Integer remainsCash2;
    Integer remainsShop;
    Integer remainsPhone;
    Integer sale1;
    Integer sale6;
    Integer order;


    public OrderRecommendations(String group,  String view, Integer remainsCash1, Integer remainsCash2, Integer remainsShop, Integer remainsPhone, Integer sale1, Integer sale6, Integer order) {
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
}
