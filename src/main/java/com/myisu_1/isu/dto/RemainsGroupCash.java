package com.myisu_1.isu.dto;

import lombok.*;

@Data
@NoArgsConstructor

@Setter
@Getter
public class RemainsGroupCash {
    String shop;
    String group;
    String view;
    Long cash;
    Long cash1;

    public RemainsGroupCash(String shop, String group, String view, Long cash) {
        this.shop = shop;
        this.group = group;
        this.view = view;
        this.cash = cash;
    }

    public RemainsGroupCash(String group, Long cash, Long cash1) {
        this.group = group;
        this.cash = cash;
        this.cash1 = cash1;
    }

    public RemainsGroupCash(String shop, String group, String view, Long cash, Long cash1) {
        this.shop = shop;
        this.group = group;
        this.view = view;
        this.cash = cash;
        this.cash1 = cash1;
    }
}
