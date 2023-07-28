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
    Integer sale1;
    Integer sale6;
    Integer order;

    public OrderRecommendations(String nomenclature, String view, Integer remainsCash1, Integer remainsCash2, Integer remainsShop, Integer sale1, Integer sale6, Integer order) {
        this.nomenclature = nomenclature;
        this.view = view;
        this.remainsCash1 = remainsCash1;
        this.remainsCash2 = remainsCash2;
        this.remainsShop = remainsShop;
        this.sale1 = sale1;
        this.sale6 = sale6;
        this.order = order;
    }
}
