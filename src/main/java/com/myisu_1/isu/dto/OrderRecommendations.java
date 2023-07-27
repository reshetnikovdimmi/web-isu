package com.myisu_1.isu.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderRecommendations {
    String group;
    String view;
    Integer remainsCash1;
    Integer remainsCash2;
    Integer sale1;
    Integer sale6;
    Integer order;
}
