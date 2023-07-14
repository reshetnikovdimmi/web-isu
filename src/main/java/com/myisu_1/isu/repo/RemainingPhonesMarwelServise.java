package com.myisu_1.isu.repo;

import com.myisu_1.isu.dto.ReportUploadPortal;
import com.myisu_1.isu.models.Marwel.RemainingPhonesMarwel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RemainingPhonesMarwelServise extends CrudRepository<RemainingPhonesMarwel, Integer> {


   @Query("SELECT new com.myisu_1.isu.dto.ReportUploadPortal    (COUNT (model), model)  FROM RemainingPhonesMarwel where   characteristic IN ?1 AND model IN ?2  GROUP BY model")
   List<ReportUploadPortal> getRemaisImeiMarvel(List<String> marvelImei, List<String> rainbowNomenclature);
   @Query("SELECT new com.myisu_1.isu.dto.ReportUploadPortal    (COUNT (model), model)  FROM RemainingPhonesMarwel where model LIKE '%Poco%' GROUP BY model")
   List<ReportUploadPortal> getRemaisRoma(String poco);
    @Query("SELECT new com.myisu_1.isu.dto.ReportUploadPortal    (COUNT (model), model)  FROM RemainingPhonesMarwel where model NOT LIKE '%Poco%' AND model LIKE %?1%   GROUP BY model")
    List<ReportUploadPortal> getRemaisRomaNoPoco(String xiaomi);
}
