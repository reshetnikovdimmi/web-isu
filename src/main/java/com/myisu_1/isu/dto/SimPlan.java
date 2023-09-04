package com.myisu_1.isu.dto;

import lombok.*;

@Data

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SimPlan {
    private int id;
    private String nameSim;
    private String view;
    private Integer sale6;
    private Integer sale1;
    private Integer remains;
    private Integer recommendation;
    private Integer plan;

    public SimPlan(int id, String nameSim, Integer sale6, Integer sale1, Integer remains, Integer plan ) {
        this.id = id;
        this.nameSim = nameSim;
        this.sale6 = sale6;
        this.sale1 = sale1;
        this.remains = remains;
        this.plan = plan;
    }

    public SimPlan(int id, String nameSim, Integer sale6, Integer sale1, Integer remains, Integer recommendation, Integer plan) {
        this.id = id;
        this.nameSim = nameSim;
        this.sale6 = sale6;
        this.sale1 = sale1;
        this.remains = remains;
        this.recommendation = recommendation;
        this.plan = plan;
    }
}
