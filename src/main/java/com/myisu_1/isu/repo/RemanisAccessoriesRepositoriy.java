package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.accessories.RemanisAccessories;
import com.myisu_1.isu.models.authorization_tt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RemanisAccessoriesRepositoriy extends CrudRepository<RemanisAccessories, Integer> {
    @Query("SELECT SUM(remainsAccessories) FROM RemanisAccessories WHERE nameAccessories IN ?1")
    String getRemainsAccessories(List<String> strings);


    @Query("SELECT SUM(remainsAccessories) FROM RemanisAccessories WHERE shop = ?1 AND nameAccessories IN ?2")
    String getRemainsAccessoriesShop(String authorization, List<String> nomeklature);
}
