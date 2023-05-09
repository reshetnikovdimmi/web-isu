package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.RemanisSimRarus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface  RemanisSimRarusrepository extends CrudRepository<RemanisSimRarus, Integer> {

}
