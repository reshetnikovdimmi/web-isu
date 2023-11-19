package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.SaleSim_1m;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.Sales;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleSimModemRepository_6m extends CrudRepository<SaleSim_6m, Integer> {

    List<SaleSim_6m> findByShop(String shop);
}
