package com.myisu_1.isu.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Bonuses {
    private String shop;
    private String models;
    private String provider;
    private String phone;
    private String imei;
    private Date startDate;
    private Date endDate;
    private String dataSale;
    private String dataPeriod;
    private Double compensation;
    private String pricePromo;

    public Bonuses(String models, String imei, String dataSale, String dataPeriod, Double compensation) {
        this.models = models;
        this.imei = imei;
        this.dataSale = dataSale;
        this.dataPeriod = dataPeriod;
        this.compensation = compensation;
    }

    public Bonuses(String shop, String models, String provider, String phone, Date startDate, Date endDate) {
        this.shop = shop;
        this.models = models;
        this.provider = provider;
        this.phone = phone;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Bonuses(String models, String pricePromo) {
        this.models = models;
        this.pricePromo = pricePromo;
    }
}
