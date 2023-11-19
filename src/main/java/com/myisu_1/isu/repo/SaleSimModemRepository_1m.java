package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.SaleSim_1m;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaleSimModemRepository_1m extends CrudRepository<SaleSim_1m, Integer> {


    List<SaleSim_1m> findByShop(String shop);
}
