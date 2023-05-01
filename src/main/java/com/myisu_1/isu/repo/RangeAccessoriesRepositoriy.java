package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.Phone.Buttons;
import com.myisu_1.isu.models.accessories.RangeAccessories;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RangeAccessoriesRepositoriy extends CrudRepository<RangeAccessories, Integer> {
    @Query("SELECT p.accessoriesName FROM RangeAccessories p " +
            "LEFT JOIN p.prices WHERE p.accessoriesCategory = ?1 AND p.prices = ?2")

    List<String> getAccessoriesNamePrice(String s, String i);

    @Query("SELECT p.accessoriesName  FROM RangeAccessories p " +
            "LEFT JOIN p.prices r  WHERE p.accessoriesCategory = ?1 AND r.priceInt > ?2 AND r.priceInt <= ?3 ")
    List<String> getButtonPhonePrice(String s, int s1, int priceMax);
    @Transactional
    @Modifying
    @Query("update RangeAccessories u set u.accessoriesCategory = ?1 where u.accessoriesCategory = ?2")

    void updateRangeAccessories(String s, String s1);

    @Query("SELECT tele2 FROM RangeAccessories WHERE accessoriesName = ?1")
    boolean findByTele2(String lastname);

    @Query("SELECT DISTINCT accessoriesCategory FROM RangeAccessories")
    List<String> getAccessoriesCategoryDist();
}
