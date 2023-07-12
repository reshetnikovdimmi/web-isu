package com.myisu_1.isu.service;

import com.myisu_1.isu.repo.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class MarwelPromoServise {
    @Autowired
    private RemanisSimRepository remanisSimRepository;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private ButtonsPhoneRepositoriy buttonsPhoneRepositoriy;
    @Autowired
    private SimAndRtkTableRepositoriy simAndRtkTableRepositoriy;
    @Autowired
    private MarvelClassifierRepositoriy marvelClassifierRepositoriy;

    public List<String> noPhone() {
        List<String> a = remanisSimRepository.getRemainsSimAndModem();
        List<String> b = phoneRepositoriy.getModelList();
        b.addAll(buttonsPhoneRepositoriy.getModelsButton());
        b.addAll(simAndRtkTableRepositoriy.getNameRainbows());
        Collection<String> aMinusB = CollectionUtils.subtract(a, b);

        return (List<String>) aMinusB;
    }

    public List<String> NoClassifier() {
        List<String> a = phoneRepositoriy.getModelListXiaomi();
        List<String> b = marvelClassifierRepositoriy.getRainbowNomenclature();
        Collection<String> aMinusB = CollectionUtils.subtract(a, b);

        return (List<String>) aMinusB;
    }
}
