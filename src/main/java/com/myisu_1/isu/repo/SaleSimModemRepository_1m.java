package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.SaleSim_1m;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaleSimModemRepository_1m extends CrudRepository<SaleSim_1m, Integer> {
    @Query("SELECT SUM(remainsSimModem)  FROM SaleSim_1m WHERE nameSimAndModem = ?1")

    Integer getSale1Sim(String nameAccessories);

    @Query("SELECT SUM(remainsSimModem)  FROM SaleSim_1m WHERE shop IN ?1 AND nameSimAndModem = ?2")
    Integer getSale1AllShopSim(List<String> shopList, String nameRainbow);

    @Query("SELECT SUM(remainsSimModem)  FROM SaleSim_1m WHERE nameSimAndModem IN ?1 AND shop = ?2")
    Integer getSale1DistrModel(List<String> distrModel, String shop);

    @Query("SELECT remainsSimModem  FROM SaleSim_1m WHERE nameSimAndModem = ?1 AND shop = ?2")
    Integer getSale1SimShop(String nameSim, String name);

    @Query("SELECT remainsSimModem  FROM SaleSim_1m WHERE nameSimAndModem IN ?1")
    Integer getSale1RTK(List<String> nameRainbow);
}
