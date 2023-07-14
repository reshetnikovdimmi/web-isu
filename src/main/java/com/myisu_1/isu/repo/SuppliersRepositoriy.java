package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuppliersRepositoriy extends JpaRepository<Suppliers, Integer> {
    @Query("SELECT suppliers FROM Suppliers where imei = ?1")
    String imeiDistribution(String imei);

    @Query("SELECT DISTINCT suppliers FROM Suppliers")
    List<String> getProviderList();
    @Query("SELECT new com.myisu_1.isu.models.Suppliers (imei, suppliers) FROM Suppliers where imei IN ?1 AND (?2 is null or suppliers = ?2) ")
    List<Suppliers> getListSuppliers(List<String> imeiSale, String provider);
    @Query("SELECT imei FROM Suppliers where suppliers = ?1 ")
    List<String> getListSuppliersProvider(String марвел_кт_ооо);
}
