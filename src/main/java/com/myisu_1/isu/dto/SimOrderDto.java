package com.myisu_1.isu.dto;

import lombok.Data;

@Data
public class SimOrderDto {
    private String nameRainbow;
    private String nameToOrder;
    private String view;
    private Integer order;

    public SimOrderDto(String nameRainbow, String nameToOrder, String view, Integer order) {
        this.nameRainbow = nameRainbow;
        this.nameToOrder = nameToOrder;
        this.view = view;
        this.order = order;
    }
}
