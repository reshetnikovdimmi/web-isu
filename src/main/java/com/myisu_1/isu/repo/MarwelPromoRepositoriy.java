package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.Marwel.MarvelPromo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarwelPromoRepositoriy extends JpaRepository<MarvelPromo, Integer> {

    @Query("SELECT DISTINCT new com.myisu_1.isu.models.Marwel.MarvelPromo(PromoCode,StartPromo, EndPromo, Status) FROM MarvelPromo ORDER BY PromoCode")
    List<MarvelPromo> getDistingMarvelPromo();
}
