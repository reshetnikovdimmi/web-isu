package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.repo.PromoRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonusesServise {
    @Autowired
    private PromoRepositoriy promoRepositoriy;
    public List<Bonuses> bonusesShowAll(Bonuses bonuses) {
        List<Bonuses> modelGb = promoRepositoriy.getPrormoAll(bonuses.getModels(),bonuses.getStartDate(),bonuses.getEndDate());
        System.out.println(modelGb);

return null;
    }
}
