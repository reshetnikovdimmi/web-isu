package com.myisu_1.isu.repo;

import com.myisu_1.isu.dto.ReportUploadPortal;
import com.myisu_1.isu.models.Sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface SalesRepositoriy extends JpaRepository<Sales, Integer> {
    @Modifying
    @Transactional
    @Query("update Sales u set u.nomenclature = ?1 where u.nomenclature = ?2 ")
    void updatModelSale(String Model, String models);

    @Modifying
    @Transactional
    @Query("update Sales u set u.shop = ?1 where u.shop = ?2 ")
    void updateNameShop(String namerainbow, String name);

    @Query("SELECT  MONTH(dateSales), COUNT(nomenclature) FROM Sales where dateSales > ?1 GROUP BY MONTH(dateSales)")
    List<Object> getMap(Date d);

    @Query("SELECT DISTINCT shop, MONTH(dateSales), COUNT(nomenclature) FROM Sales where dateSales > ?1 GROUP BY shop, MONTH(dateSales)")
    List<Object> getShopMap(Date yearStartDate);

    @Query("SELECT DISTINCT MONTH(dateSales) FROM Sales where dateSales > ?1  ORDER BY dateSales ASC")
    List<String> getDistinct(Date yearStartDate);

    @Query("SELECT new com.myisu_1.isu.models.Sales(imeis, shop, nomenclature, dateSales) FROM Sales where  (?1 is null or dateSales >= ?1) AND (?2 is null or dateSales <= ?2) AND (?3 is null or shop = ?3) ")
    List<Sales> getSaleAll(Date startDate, Date endDate, String shop, String phone);

    @Query("SELECT new com.myisu_1.isu.models.Sales(imeis, shop, nomenclature, dateSales) FROM Sales where (?4 is null or nomenclature LIKE %?4%) AND (?1 is null or dateSales >= ?1) AND (?2 is null or dateSales <= ?2) AND (?3 is null or shop = ?3) ")
    List<Sales> getSaleAllPhone(Date startDate, Date endDate, String shop, String phone);

    @Query("SELECT new com.myisu_1.isu.models.Sales(imeis, shop, nomenclature, dateSales) FROM Sales where (?1 is null or dateSales >= ?1) AND (?2 is null or dateSales <= ?2) AND  shop IN ?3")
    List<Sales> getSaleNoT2(Date startDate, Date endDate, List<String> shopMult);

    @Query("SELECT new com.myisu_1.isu.models.Sales(imeis, shop, nomenclature, dateSales) FROM Sales where (?1 is null or dateSales >= ?1) AND (?2 is null or dateSales <= ?2) AND  nomenclature IN ?3")
    List<Sales> getSaleXiaomi(Date dateString, Date dateString1, List<String> modelMarvelPromo);

    @Query("SELECT new com.myisu_1.isu.dto.ReportUploadPortal    (COUNT (nomenclature), nomenclature)  FROM Sales where dateSales>=?1 AND dateSales<=?2 AND imeis IN ?3 AND nomenclature IN ?4 GROUP BY nomenclature")
    List<ReportUploadPortal> getSaleClassiferProvider(Date start, Date stop, List<String> марвел_кт_ооо, List<String> rainbowNomenclature);

    @Query("SELECT new com.myisu_1.isu.models.Sales    (imeis, nomenclature)  FROM Sales where dateSales>=?1 AND dateSales<=?2 AND imeis IN ?3 AND nomenclature IN ?4 ")
    List<Sales> getSaleClassiferProviderImei(Date date, Date date1, List<String> marvelImei, List<String> rainbowNomenclature);

    @Query("SELECT new com.myisu_1.isu.dto.ReportUploadPortal    (COUNT (nomenclature), nomenclature)  FROM Sales where nomenclature LIKE '%Poco%' AND  dateSales>=?1 AND dateSales<=?2 GROUP BY nomenclature")
    List<ReportUploadPortal> getSaleClassiferRoma(java.sql.Date date, java.sql.Date date1, String poco);

    @Query("SELECT new com.myisu_1.isu.dto.ReportUploadPortal    (COUNT (nomenclature), nomenclature)  FROM Sales where nomenclature NOT LIKE '%Poco%' AND  dateSales>=?1 AND dateSales<=?2 AND nomenclature LIKE %?3% GROUP BY nomenclature")
    List<ReportUploadPortal> getSaleClassiferRomaNoPoco(java.sql.Date date, java.sql.Date date1, String xiaomi);

    @Query("SELECT nomenclature FROM Sales where dateSales >= ?2 AND dateSales <= ?3 AND nomenclature IN ?1 ")
    List<String> getModelPhoneData(List<String> modelListPhone, java.sql.Date date, java.sql.Date date1);
}
