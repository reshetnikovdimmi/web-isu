package com.myisu_1.isu.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data

@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BrendRemanis {
    String Brend;
    long remanis;
    long sale6;
    long sale1;
    long remanisCloters;

    public BrendRemanis(String brend, long remanis,long remanisCloters, long sale6, long sale1 ) {
        Brend = brend;
        this.remanis = remanis;
        this.sale6 = sale6;
        this.sale1 = sale1;
        this.remanisCloters = remanisCloters;
    }

    public BrendRemanis(String brend, long remanis) {
        Brend = brend;
        this.remanis = remanis;

    }


}
