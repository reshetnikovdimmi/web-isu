package com.myisu_1.isu.models.ClothesForPhones.Glass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.myisu_1.isu.models.Authorization_tt;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data

@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Glass extends ClothingMatching {
    String Brend;
    long remanis;
    long sale6;
    long sale1;
    long remanisCloters;
    int remanisCloterse;

    public Glass(String brend, int remanisCloterse) {
        Brend = brend;
        this.remanisCloterse = remanisCloterse;
    }

    public Glass(String brend, long remanis, long remanisCloters) {
        Brend = brend;
        this.remanis = remanis;
        this.remanisCloters = remanisCloters;
    }


    public Glass(String brend, long remanis,long remanisCloters, long sale6, long sale1 ) {
        Brend = brend;
        this.remanis = remanis;
        this.sale6 = sale6;
        this.sale1 = sale1;
        this.remanisCloters = remanisCloters;
    }

    public Glass(String brend, long remanis) {
        Brend = brend;
        this.remanis = remanis;

    }

    public List<List<String>> brendDistingList;

    public Iterable<Authorization_tt> print(){

        return authorization_ttList;
    }
}
