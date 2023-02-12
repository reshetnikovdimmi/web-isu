package com.myisu_1.isu.repo;

import com.myisu_1.isu.dto.BrendRemanis;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClothingMatchingTableRepositoriy extends JpaRepository<ClothingMatchingTable, Integer>{


    @Query("SELECT DISTINCT  new com.myisu_1.isu.dto.BrendRemanis(c.nameClothes,SUM (p.saleClothes)) FROM ClothingMatchingTable c " +
            "LEFT JOIN c.clothersSale6 p WHERE c.viewClothes = ?1 GROUP BY c.nameClothes")
    List<BrendRemanis> getSal6Clothes(String vtew);

    @Query("SELECT DISTINCT  new com.myisu_1.isu.dto.BrendRemanis(c.nameClothes,SUM (p.saleClothes)) FROM ClothingMatchingTable c " +
            "LEFT JOIN c.clothersSale1 p WHERE c.viewClothes = ?1 GROUP BY c.nameClothes")
    List<BrendRemanis> getSal1Clothes(String vtew);

    @Query("SELECT DISTINCT  new com.myisu_1.isu.dto.BrendRemanis(c.nameClothes,SUM (r.remanisClothes)) FROM ClothingMatchingTable c " +
            "LEFT JOIN c.clothersPhone r WHERE c.viewClothes = ?1 GROUP BY c.nameClothes")
    List<BrendRemanis> getRemanisClothes(String vtew);

   /* @Query("INSERT INTO ClothingMatchingTable (nameClothes, phoneClothes, viewClothes) ('cawcacsasa', 'as sa', 'as s')")
   void getSave();*/

}
