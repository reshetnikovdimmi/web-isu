package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.price_promo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PromoRepositoriy extends PagingAndSortingRepository<price_promo, Integer> {

    @Modifying
    @Transactional
    @Query("update price_promo u set u.models = ?1 where u.models = ?2")

    void updateModelsPricePromo(String s, String s1);
    @Modifying
    @Transactional
    @Query("update price_promo u set u.brend = ?1 where u.brend = ?2 AND u.models = ?3")
    void updateBrendPricePromo(String brend, String brend1, String model_GB);
}
