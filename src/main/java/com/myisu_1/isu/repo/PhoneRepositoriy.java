package com.myisu_1.isu.repo;


import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.dto.RemainsGroupCash;
import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.models.Phone_Smart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    @Query("SELECT DISTINCT Brend  FROM Phone_Smart WHERE Phone = ?1")
    List<String> getModelBrendList(String phone);


    @Query("SELECT DISTINCT Model_GB  FROM Phone_Smart WHERE Brend = ?1")
    List<String> getModeModel_GBList(String phone);

    @Query("SELECT DISTINCT Phone  FROM Phone_Smart WHERE Phone != ' ' ORDER BY Phone ASC")
    List<String> getPhoneList();

    @Query("SELECT Model  FROM Phone_Smart WHERE Phone != ' '")
    List<String> getModelList();

    @Query("SELECT Model  FROM Phone_Smart WHERE Phone = 'Poco' OR Phone = 'Xiaomi'")
    List<String> getModelListXiaomi();



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

    @Query("SELECT new com.myisu_1.isu.models.Phone_Smart (Model_GB, Model) FROM Phone_Smart WHERE Model IN ?1")
    List<Phone_Smart> getSaleModelList(List<String> model);
    @Query("SELECT DISTINCT Model  FROM Phone_Smart WHERE Phone = ?1 AND Phone != ' '")
    List<String> getModelListPhone(String p);


    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(c.Brend, SUM (p.remainsSimAndModem)) FROM Phone_Smart c   " +
            "JOIN c.remanisSims p   GROUP BY c.Brend")
    List<OrderRecommendations> getRemainsPhoneAll();
    @Modifying
    @Transactional
    @Query("update Phone_Smart u set u.Brend = ?1 where u.Brend = ?2")
    void updateBrendPhoneSmart(String brend, String brend1);
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.shop,c.Brend, SUM (p.remainsSimAndModem)) FROM Phone_Smart c   " +
            "JOIN c.remanisSims p   GROUP BY p.shop, c.Brend")
    List<OrderRecommendations> getRemainsShopPhoneGroup();
    @Query("SELECT DISTINCT new com.myisu_1.isu.dto.RemainsGroupCash (Matrix_T2) FROM Phone_Smart")
    List<RemainsGroupCash> getGroupView();
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.shop,c.Matrix_T2, SUM (p.remainsSimAndModem)) FROM Phone_Smart c   " +
            "JOIN c.remanisSims p   GROUP BY p.shop, c.Matrix_T2")
    List<OrderRecommendations> getRemainsShopPhoneMatrixT2();
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.shop,c.Matrix_T2, SUM (p.remainsSimAndModem)) FROM Phone_Smart c   " +
            "JOIN c.remanisSims p WHERE c.Matrix_T2 IN ?1 AND p.shop IN ?2  GROUP BY p.shop, c.Matrix_T2")
    List<OrderRecommendations> getRemainsShopPhoneMatrix(List<String> matrix, List<String> shopT2);

    @Query("SELECT DISTINCT new com.myisu_1.isu.dto.RemainsGroupCash(c.Brend, p.viewClothes) FROM Phone_Smart c   " +
            "LEFT JOIN c.сlothingMatching p   ")
    List<RemainsGroupCash> getGroupViewClothing();
    @Query("SELECT new com.myisu_1.isu.models.Phone.MatrixSpark(p.shop,c.Matrix_T2, SUM (p.remainsSimModem)) FROM Phone_Smart c   " +
            "JOIN c.saleSim_6m p WHERE c.Matrix_T2 IN ?1  GROUP BY c.Matrix_T2 ")
    List<MatrixSpark> getSaleAllMatrix(List<String> distingMatrix);
    @Query("SELECT new com.myisu_1.isu.models.Phone.MatrixSpark(p.shop,c.Matrix_T2, SUM (p.remainsSimModem)) FROM Phone_Smart c   " +
            "JOIN c.saleSim_6m p WHERE p.shop = ?1  GROUP BY c.Matrix_T2 ")
    List<MatrixSpark> getSale6Shop(String shop);

    @Query("SELECT new com.myisu_1.isu.models.Phone.MatrixSpark(p.shop,c.Matrix_T2, SUM (p.remainsSimModem)) FROM Phone_Smart c   " +
            "JOIN c.saleSim_1m p WHERE p.shop = ?1  GROUP BY c.Matrix_T2 ")
    List<MatrixSpark> getSale1Shop(String shop);
}

