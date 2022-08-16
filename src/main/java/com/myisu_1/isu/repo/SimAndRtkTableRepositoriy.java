package com.myisu_1.isu.repo;
import com.myisu_1.isu.models.SIM.SimAndRtkTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimAndRtkTableRepositoriy extends JpaRepository<SimAndRtkTable, Integer> {
}