package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepositoriy extends JpaRepository<Suppliers, Integer> {
}
