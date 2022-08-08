package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.Sales;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleSimModemRepository extends CrudRepository<SaleSim_6m, Integer> {
}
