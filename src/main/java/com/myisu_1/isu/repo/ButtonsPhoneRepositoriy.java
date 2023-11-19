package com.myisu_1.isu.repo;


import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.dto.RemainsGroupCash;
import com.myisu_1.isu.models.Phone.Buttons;
import com.myisu_1.isu.models.Phone.ButtonsPhone;
import com.myisu_1.isu.models.Phone_Smart;
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


    @Query("SELECT model FROM ButtonsPhone ")
    List<String> getModelsGraduation();
    @Query("SELECT  group FROM ButtonsPhone ")
    List<String> getGroupShop();

    @Query("SELECT DISTINCT model FROM ButtonsPhone")
    List<String> getModelsButton();

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.shop,c.group, c.model, p.remainsSimAndModem) FROM ButtonsPhone c   " +
            "JOIN c.remanisSims p ")
    List<OrderRecommendations> getRemainsShopButton();

    @Query("SELECT DISTINCT new com.myisu_1.isu.dto.RemainsGroupCash (group) FROM ButtonsPhone ORDER BY group ASC")
    List<RemainsGroupCash> getGroupView();

    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.shop, c.group, c.model, p.remainsSimModem) FROM ButtonsPhone c   " +
            "JOIN c.saleSim_1m p   ")
    List<OrderRecommendations> getSale1Phone();
    @Query("SELECT new com.myisu_1.isu.dto.OrderRecommendations(p.shop, c.group, c.model, p.remainsSimModem) FROM ButtonsPhone c   " +
            "JOIN c.saleSim_6m p   ")
    List<OrderRecommendations> getSale6Phone();
    @Query("SELECT new com.myisu_1.isu.models.Phone_Smart (group, model,model,model,model) FROM ButtonsPhone")
    List<Phone_Smart> phoneSmar();
}
