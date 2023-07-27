package com.myisu_1.isu.dto;

import lombok.*;

@Data
@NoArgsConstructor

@Setter
@Getter
public class RemainsGroupCash {
    String group;
    String view;
    Long cash1;
    Long cash2;


    public RemainsGroupCash(String group, String view, Long cash1) {
        this.group = group;
        this.view = view;
        this.cash1 = cash1;
    }

    public RemainsGroupCash(String group, String view, Long cash1, Long cash2) {
        this.group = group;
        this.view = view;
        this.cash1 = cash1;
        this.cash2 = cash2;
    }
}
