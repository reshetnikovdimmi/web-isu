package com.myisu_1.isu.dto;

import lombok.*;

import java.util.Date;

@Data

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportUploadPortal {
    public Long sale;
    private Long remanis;

    private String article;
    private String imei;
    private String nomenclature;
    private String Name;
    private String shareRubles;
    private String sharePercentage;

    public ReportUploadPortal(String Name, Long sale, Long remanis, String shareRubles, String sharePercentage) {
        this.Name = Name;
        this.sale = sale;
        this.remanis = remanis;
        this.shareRubles = shareRubles;
        this.sharePercentage = sharePercentage;
    }

    public ReportUploadPortal(String nomenclature, Long sale, Long remanis) {
        this.sale = sale;
        this.remanis = remanis;
        this.nomenclature = nomenclature;
    }

    public ReportUploadPortal(String article, String imei) {
        this.imei = imei;
        this.article = article;
    }

    public ReportUploadPortal(long sale, String nomenclature) {
        this.sale = sale;
        this.nomenclature = nomenclature;
    }

    public ReportUploadPortal(Long sale) {
        this.sale = sale;
    }

    public ReportUploadPortal(String article, String name, Long sale, Long remanis) {
        this.sale = sale;
        this.remanis = remanis;
        this.article = article;
        Name = name;
    }
}
