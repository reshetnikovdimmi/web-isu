package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.SaleSim_1m;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SaleSimModemRepository_1m extends CrudRepository<SaleSim_1m, Integer> {
    @Query("SELECT SUM(remainsSimModem)  FROM SaleSim_1m WHERE nameSimAndModem = ?1")

    String getSale1Sim(String nameAccessories);
}
