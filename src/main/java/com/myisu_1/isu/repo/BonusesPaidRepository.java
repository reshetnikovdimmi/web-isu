package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.bonuses.BonusesPaid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface BonusesPaidRepository extends JpaRepository<BonusesPaid,Integer> {


    @Query("SELECT new com.myisu_1.isu.models.bonuses.BonusesPaid (id, model, startPromo,endPromo,amount,  suppliers) FROM BonusesPaid WHERE (?2 is null or model=?2) AND (?3 is null or startPromo>=?3) AND (?4 is null or endPromo<=?4) AND (?1 is null or suppliers =?1)")
    List<BonusesPaid> searchAmount(String suppliers, String model, Date date, Date date1);
}
