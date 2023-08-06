package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.Phone.Buttons;
import com.myisu_1.isu.models.Phone.ButtonsPhone;
import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.SaleSim_1m;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ButtonsPhoneRepositoriy extends JpaRepository<ButtonsPhone, Integer> {

    @Query("SELECT new com.myisu_1.isu.models.Phone.Buttons(p.id, p.model, p.brend, r.price) FROM ButtonsPhone p " +
            "LEFT JOIN p.prices r ORDER BY p.id ASC")
    List<Buttons> getButtonPhonePrice();


    @Query("SELECT new com.myisu_1.isu.models.Phone.Buttons(p.id, p.model, p.brend, r.price) FROM ButtonsPhone p " +
            "LEFT JOIN p.prices r ORDER BY p.id ASC")
    Object getGraduationPhone();

    @Query("SELECT DISTINCT brend FROM ButtonsPhone")
    List<String> getModelsGraduation();

    @Query("SELECT DISTINCT model FROM ButtonsPhone")
    List<String> getModelsButton();

    @Query("SELECT DISTINCT SUM(remainsSimAndModem) FROM RemanisSim WHERE nameSimAndModem IN :value")
    String getRemanisButton(List<String> value);

    @Query("SELECT DISTINCT SUM(remainsSimModem)/3 FROM SaleSim_6m WHERE nameSimAndModem IN :value")
    String getSale6mButton(List<String> value);

    @Query("SELECT DISTINCT SUM(remainsSimModem) FROM SaleSim_1m WHERE nameSimAndModem IN :value")
    String getSale1mButton(List<String> value);

   @Query("SELECT s FROM SaleSim_1m s WHERE s.nameSimAndModem IN :strings")
    List<SaleSim_1m> getShopRemanisSele1m(List<String> strings);

    @Query("SELECT s FROM SaleSim_6m s WHERE s.nameSimAndModem IN :strings")
    List<SaleSim_6m> getShopRemanisSele6m(List<String> strings);

    @Query("SELECT s FROM RemanisSim s WHERE s.nameSimAndModem IN :strings")
    List<RemanisSim> getShopRemanis(List<String> strings);

    @Query("SELECT s FROM RemanisSim s WHERE s.nameSimAndModem IN :strings")
    List<RemanisSim> getBrend (List<String> strings);

    @Query("SELECT remainsSimAndModem FROM RemanisSim WHERE shop = ?1 AND nameSimAndModem = ?2")

    String getShopRemanisModel(String authorizationList, String lis);


    @Query("SELECT remainsSimModem FROM SaleSim_6m WHERE shop = ?1 AND nameSimAndModem = ?2")
    String getShopRemanisSele6mModel(String authorizationList, String lis);


    @Query("SELECT remainsSimModem FROM SaleSim_1m WHERE shop = ?1 AND nameSimAndModem = ?2")
    String getShopRemanisSele1mModel(String authorizationList, String lis);
}
