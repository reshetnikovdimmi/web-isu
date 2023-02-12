package com.myisu_1.isu.repo;

import com.myisu_1.isu.dto.BrendRemanis;
import com.myisu_1.isu.models.Phone_Smart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhoneRepositoriy extends JpaRepository<Phone_Smart, Integer> {
    @Query("SELECT DISTINCT Brend FROM Phone_Smart")
    List<String> getBrendDisting();
/*SELECT `brend`,
SUM(`remains_sim_and_modem`)
FROM `phone_smart`
INNER JOIN `remanis_sim` ON `name_sim_and_modem` = `model`
GROUP BY `brend`
*/

  @Query("SELECT  new com.myisu_1.isu.dto.BrendRemanis(p.Brend, SUM (r.remainsSimAndModem)) FROM Phone_Smart p " +

          "JOIN p.remanisSims r GROUP BY p.Brend")
         // "LEFT JOIN p.clothingMatchingTableList c WHERE c.viewClothes = 'Glass' GROUP BY p.Brend"*)
   List<BrendRemanis> getBrendRemanis();

}
