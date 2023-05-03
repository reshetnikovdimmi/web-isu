package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.Sales;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleSimModemRepository_6m extends CrudRepository<SaleSim_6m, Integer> {
    @Query("SELECT SUM(remainsSimModem)  FROM SaleSim_6m WHERE nameSimAndModem = ?1")

    String getSale6Sim(String nameAccessories);
}
