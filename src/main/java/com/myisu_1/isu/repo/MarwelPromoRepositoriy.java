package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.Marwel.MarvelPromo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface MarwelPromoRepositoriy extends JpaRepository<MarvelPromo, Integer> {



    @Query("SELECT DISTINCT new com.myisu_1.isu.models.Marwel.MarvelPromo(PromoCode,StartPromo, EndPromo, Status) FROM MarvelPromo ORDER BY PromoCode")
    List<MarvelPromo> getDistingMarvelPromo();

    @Query("SELECT ArticleNumber FROM MarvelPromo where PromoCode =?1 AND StartPromo = ?2 AND EndPromo = ?3")
    List<String> getListModelPromoCode(String promoCode, Date dateString, Date dateString1);
}
