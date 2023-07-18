package com.myisu_1.isu.repo;

import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.models.price_promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface PromoRepositoriy extends JpaRepository<price_promo, Integer> {

    @Modifying
    @Transactional
    @Query("update price_promo u set u.models = ?1 where u.models = ?2")

    void updateModelsPricePromo(String s, String s1);
    @Modifying
    @Transactional
    @Query("update price_promo u set u.brend = ?1 where u.brend = ?2 AND u.models = ?3")
    void updateBrendPricePromo(String brend, String brend1, String model_GB);

    @Query("SELECT new com.myisu_1.isu.models.price_promo (models,  startPromo, endPromo,tfn, vvp, merlion, marwel) FROM price_promo WHERE(?1 is null or models=?1)")
    List<price_promo> getPrormoAll(String models);
    @Query("SELECT new com.myisu_1.isu.models.price_promo (brend, models,price,price_promo,  startPromo, endPromo,tfn, vvp, merlion, marwel) FROM price_promo WHERE startPromo <=?1 AND endPromo >=?1")
    List<price_promo> currentPromo(Date date);
    @Query("SELECT new com.myisu_1.isu.models.price_promo (brend, models,price,price_promo,  startPromo, endPromo,tfn, vvp, merlion, marwel) FROM price_promo WHERE startPromo =?1")
    List<price_promo>startPromo(Date date);
    @Query("SELECT new com.myisu_1.isu.models.price_promo (brend, models,price,price_promo,  startPromo, endPromo,tfn, vvp, merlion, marwel) FROM price_promo WHERE endPromo =?1")
    List<price_promo> endPromo(Date date);
}
