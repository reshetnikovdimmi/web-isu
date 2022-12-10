package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RemanisSimRepository extends CrudRepository<RemanisSim, Integer> {


}
