package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.Authorization_tt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepositoriy extends CrudRepository <Authorization_tt, Integer> {
    @Query("SELECT name  FROM Authorization_tt")
    List<String> getShopList();

    @Query("SELECT name  FROM Authorization_tt WHERE login LIKE '%Warehouse%'")
    List<String> getWarehouseList();

    @Query("SELECT name  FROM Authorization_tt WHERE cluster_rtk != ' '")
    List<String> getShopRTK();

    @Query("SELECT name  FROM Authorization_tt WHERE simT2 = 't2'")
    List<String> getShopT2();

    @Query("SELECT name  FROM Authorization_tt WHERE clusterT2 != ' '")
    List<String> getShopMatrixT2();

    @Query("SELECT name  FROM Authorization_tt WHERE clusterT2 = ' '")
    List<String> getShopMult();

    @Query("SELECT clusterRtk  FROM Authorization_tt WHERE name = ?1")
    String getClusterRTK(String s);

    @Query("SELECT clusterT2  FROM Authorization_tt WHERE name = ?1")
    String getClusterT2(String s);

    @Query("SELECT new com.myisu_1.isu.models.Authorization_tt(name, clusterT2 ) FROM Authorization_tt WHERE  clusterT2 != ' '")
    List<Authorization_tt> getClusterT2List();

    Authorization_tt findByName(String shop);
}
