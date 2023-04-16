package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.Phone.Buttons;
import com.myisu_1.isu.models.accessories.RangeAccessories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RangeAccessoriesRepositoriy extends CrudRepository<RangeAccessories, Integer> {
    @Query("SELECT p.accessoriesName FROM RangeAccessories p " +
            "LEFT JOIN p.prices WHERE p.accessoriesCategory = ?1 AND p.prices = ?2")

    List<String> getAccessoriesNamePrice(String s, String i);

    @Query("SELECT p.accessoriesName  FROM RangeAccessories p " +
            "LEFT JOIN p.prices r  WHERE p.accessoriesCategory = ?1 AND r.priceInt = ?2")
    List<String> getButtonPhonePrice(String s, int s1);
}
