package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.accessories.SettingAccessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SettingAccessoriesRepositoriy extends JpaRepository<SettingAccessories, Integer> {
    @Query("SELECT minRemanis  FROM SettingAccessories WHERE group = ?1 AND priceMin = ?2 AND priceMax = ?3")
    Integer getMinRemanis(String group, Integer priceMin, Integer priceMax);


}

