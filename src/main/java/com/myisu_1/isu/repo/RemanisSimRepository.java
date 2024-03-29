package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.RemanisSim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RemanisSimRepository extends JpaRepository<RemanisSim, Integer> {
    @Query("SELECT SUM(remainsSimAndModem)  FROM RemanisSim WHERE nameSimAndModem = ?1")

    Integer getSumRemanisSim(String nameAccessories);

    @Query("SELECT remainsSimAndModem  FROM RemanisSim WHERE nameSimAndModem = ?1 AND shop = ?2")

    Integer getRemanisSimShop(String nameAccessories, String shop);

    @Query("SELECT SUM(remainsSimAndModem)  FROM RemanisSim WHERE nameSimAndModem IN ?1 AND shop = ?2")
    Integer totalSimRTK(List<String> nameRainbow, String shop);

    @Query("SELECT SUM(remainsSimAndModem)  FROM RemanisSim WHERE nameSimAndModem IN ?1 AND shop IN ?2")
    Integer getRemanisRTKCash(List<String> nameRainbow, List<String> warehouseList);

    @Query("SELECT SUM(remainsSimAndModem)  FROM RemanisSim WHERE nameSimAndModem IN ?1 AND shop = ?2")
    Integer getRemanisRTKGropShop(List<String> nameRainbow, String shop);


    List<RemanisSim> findByShop(String shop);


    @Query("SELECT DISTINCT nameSimAndModem  FROM RemanisSim")
    List<String> getRemainsSimAndModem();

}
