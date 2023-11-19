package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.SIM.RemanisSim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RemanisSimRepository extends JpaRepository<RemanisSim, Integer> {

    List<RemanisSim> findByShop(String shop);

    @Query("SELECT DISTINCT nameSimAndModem  FROM RemanisSim")
    List<String> getRemainsSimAndModem();

}
