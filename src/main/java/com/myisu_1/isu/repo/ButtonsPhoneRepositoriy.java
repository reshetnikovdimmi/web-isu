package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.Phone.Buttons;
import com.myisu_1.isu.models.Phone.ButtonsPhone;
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

    @Query("SELECT DISTINCT SUM(remainsSimAndModem) FROM RemanisSim WHERE nameSimAndModem IN :value")
    String getRemanisButton(List<String> value);

    @Query("SELECT DISTINCT SUM(remainsSimModem)/3 FROM SaleSim_6m WHERE nameSimAndModem IN :value")
    String getSale6mButton(List<String> value);

    @Query("SELECT DISTINCT SUM(remainsSimModem) FROM SaleSim_1m WHERE nameSimAndModem IN :value")
    String getSale1mButton(List<String> value);
}
