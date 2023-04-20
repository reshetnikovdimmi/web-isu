package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.accessories.SettingAccessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface SettingAccessoriesRepositoriy extends CrudRepository<SettingAccessories, Integer> {
}
