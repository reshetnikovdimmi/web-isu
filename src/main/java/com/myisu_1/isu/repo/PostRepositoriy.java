package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.authorization_tt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepositoriy extends CrudRepository <authorization_tt, Integer> {
    @Query("SELECT name  FROM authorization_tt")
    List<String> getShopList();
}
