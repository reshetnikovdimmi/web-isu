package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import com.myisu_1.isu.models.Phone_Smart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhoneRepositoriy extends JpaRepository<Phone_Smart, Integer> {
    @Query("SELECT DISTINCT Brend FROM Phone_Smart")
    List<String> getBrendDisting();

    @Query("SELECT DISTINCT Matrix_T2 FROM Phone_Smart")
    List<String> getMatrixT2Disting();

    @Query("SELECT Model  FROM Phone_Smart WHERE Matrix_T2 = ?1")
    List<String> getModelMatrixT2List(String matrix);

    @Query("SELECT DISTINCT Model_GB  FROM Phone_Smart WHERE Phone = ?1")
    List<String> getModel_GBList(String phone);

    @Query("SELECT DISTINCT Phone  FROM Phone_Smart ORDER BY Phone ASC")
    List<String> getPhoneList();

    @Query("SELECT Model  FROM Phone_Smart")
    List<String> getModelList();

    @Query("SELECT Model  FROM Phone_Smart WHERE Phone = 'Poco' OR Phone = 'Xiaomi'")
    List<String> getModelListXiaomi();

  @Query("SELECT  new com.myisu_1.isu.models.ClothesForPhones.Glass.Glass(p.Brend, SUM (r.remainsSimAndModem)) FROM Phone_Smart p " +
          "JOIN p.remanisSims r GROUP BY p.Brend")
   List<Glass> getBrendRemanis();

    @Query("SELECT  new com.myisu_1.isu.models.ClothesForPhones.Glass.Glass(r.shop, SUM (r.remainsSimAndModem)) FROM Phone_Smart p " +
            "JOIN p.remanisSims r WHERE p.Brend = ?1 GROUP BY r.shop")

    List<Glass> getBrendShopRemanis(String s);

    @Query("SELECT  new com.myisu_1.isu.models.ClothesForPhones.Glass.Glass(p.Brend, SUM (r.remainsSimAndModem)) FROM Phone_Smart p " +
            "JOIN p.remanisSims r WHERE r.shop = ?1  GROUP BY p.Brend")
    List<Glass> getPhoneRemanisShop(String shop);

    @Query("SELECT  SUM (r.remainsSimAndModem) FROM Phone_Smart p " +
            "INNER JOIN p.remanisSims r WHERE p.Matrix_T2 = ?1 ")
    Integer getPhoneRemanisSum(String matrixT2);

    @Query("SELECT  SUM (r.remainsSimModem) FROM Phone_Smart p " +
            "JOIN p.saleSim_1m r WHERE p.Matrix_T2 = ?1 ")
    Integer getPhoneSale1Sum(String matrixT2);

    @Query("SELECT  SUM (r.remainsSimModem) FROM Phone_Smart p " +
            "JOIN p.saleSim_6m r WHERE p.Matrix_T2 = ?1 ")
    Integer getPhoneSale6Sum(String matrixT2);

    @Query("SELECT  SUM (r.remainsSimAndModem) FROM Phone_Smart p " +
            "JOIN p.remanisSims r WHERE p.Matrix_T2 = ?1 AND r.shop =?2 OR p.Matrix_T2 = ?1 AND r.shop =?3")
    Integer getPhoneRemanSachAll(String matrix, String name, String name1);

    @Query("SELECT  SUM (r.remainsSimAndModem) FROM Phone_Smart p " +
            "JOIN p.remanisSims r WHERE p.Matrix_T2 = ?1 AND r.shop =?2 ")
    Integer getPhoneRemanMatrix(String matrix, String shop);

    @Query("SELECT  SUM (r.remainsSimModem) FROM Phone_Smart p " +
            "JOIN p.saleSim_1m r WHERE p.Matrix_T2 = ?1 AND r.shop =?2")
    Integer getPhoneSale1Matrix(String matrix, String shop);

    @Query("SELECT  SUM (r.remainsSimModem) FROM Phone_Smart p " +
            "JOIN p.saleSim_6m r WHERE p.Matrix_T2 = ?1 AND r.shop =?2")
    Integer getPhoneSale6Matrix(String matrix, String shop);

    @Query("SELECT Model_GB  FROM Phone_Smart WHERE Model = ?1")
    List<String> getPhonaModelGb(String model);

    @Query("SELECT  SUM (r.remainsSimAndModem) FROM Phone_Smart p " +
            "JOIN p.remanisSims r WHERE p.Model_GB IN ?1 AND r.shop =?2 ")
    Integer getRemanisModelGbShop(List<String> modelGb, String name);

    @Query("SELECT  SUM (r.remainsSimModem) FROM Phone_Smart p " +
            "JOIN p.saleSim_1m r WHERE p.Model_GB IN ?1 AND r.shop =?2 ")
    Integer getSale1DistrModelGb(List<String> modelGb, String name);

    @Query("SELECT  SUM (r.remainsSimModem) FROM Phone_Smart p " +
            "JOIN p.saleSim_6m r WHERE p.Model_GB IN ?1 AND r.shop =?2 ")
    Integer getSale6DistrModelGb(List<String> modelGb, String name);

    @Modifying
    @Transactional
    @Query("update Phone_Smart u set u.Model_GB = ?1 where u.Model_GB = ?2")

    void updateModelsGbPhoneSmart(String s, String s1);
    @Query("SELECT  Model FROM Phone_Smart   WHERE (?1 is null or Model_GB=?1)")
    List<String> getListModels(String models);
    @Query("SELECT new com.myisu_1.isu.models.Phone_Smart (Model_GB, Model) FROM Phone_Smart WHERE Model IN ?1")
    List<Phone_Smart> getSaleModelList(List<String> model);
}

