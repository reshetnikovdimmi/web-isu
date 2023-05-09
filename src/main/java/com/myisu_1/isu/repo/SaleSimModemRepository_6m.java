package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.Sales;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleSimModemRepository_6m extends CrudRepository<SaleSim_6m, Integer> {
    @Query("SELECT SUM(remainsSimModem)  FROM SaleSim_6m WHERE nameSimAndModem = ?1")
    Integer getSale6Sim(String nameAccessories);

    @Query("SELECT SUM(remainsSimModem)  FROM SaleSim_6m WHERE shop IN ?1 AND nameSimAndModem = ?2")
    Integer getSale6AllShopSim(List<String> shopList, String nameRainbow);

    @Query("SELECT remainsSimModem  FROM SaleSim_6m WHERE nameSimAndModem = ?1 AND shop = ?2")
    Integer getSale6SimShop(String nameAccessories, String shop);

    @Query("SELECT SUM(remainsSimModem)  FROM SaleSim_6m WHERE nameSimAndModem IN ?1 AND shop = ?2")
    Integer getSale6DistrModel(List<String> nameRainbow, String shop);
}
