package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.ReportUploadPortal;
import com.myisu_1.isu.models.Marwel.MarvelClassifier;
import com.myisu_1.isu.models.Marwel.RemainingPhonesMarwel;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.repo.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private SuppliersRepositoriy suppliersRepositoriy;
    @Autowired
    private SalesRepositoriy salesRepositoriy;
    @Autowired
    private RemainingPhonesMarwelServise remainingPhonesMarwelServise;
    List<ReportUploadPortal> portals;
    List<ReportUploadPortal> remanismarvel;
    List<String> marvelImei;
    List<String> rainbowNomenclature;
    List<MarvelClassifier> marvelClassifierList;
    List<ReportUploadPortal> portals1;
    List<ReportUploadPortal> remanismarvel1;
    List<ReportUploadPortal> portals11;
    List<ReportUploadPortal> remanismarvel11;

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

    public List<ReportUploadPortal> reportUploadPortal(Date start, Date stop) {
        List<ReportUploadPortal> reportUploadPortals = new ArrayList<>();
        marvelImei = suppliersRepositoriy.getListSuppliersProvider("МАРВЕЛ КТ ООО");
        rainbowNomenclature = marvelClassifierRepositoriy.getRainbowNomenclature();
        remanismarvel = remainingPhonesMarwelServise.getRemaisImeiMarvel(marvelImei, rainbowNomenclature);
        marvelClassifierList = marvelClassifierRepositoriy.findAll();
        portals = salesRepositoriy.getSaleClassiferProvider(new java.sql.Date(start.getTime()), new java.sql.Date(stop.getTime()), marvelImei, rainbowNomenclature);

        for (MarvelClassifier m : marvelClassifierList) {
            Long remanis = searchRemanis(m.getRainbowNomenclature());
            Long sale = searchSale(m.getRainbowNomenclature());

            if (remanis != null && sale != null) {
                reportUploadPortals.add(new ReportUploadPortal(m.getManufacturersArticle(), m.getName(), sale, remanis));
            }
            if (remanis == null && sale != null) {
                reportUploadPortals.add(new ReportUploadPortal(m.getManufacturersArticle(), m.getName(), sale, 0L));
            }
            if (remanis != null && sale == null) {
                reportUploadPortals.add(new ReportUploadPortal(m.getManufacturersArticle(), m.getName(), 0L, remanis));
            }

        }

        return reportUploadPortals;
    }

    private Long searchSale(String rainbowNomenclature) {
        for (ReportUploadPortal p : portals) {
            if (rainbowNomenclature.equals(p.getNomenclature())) {
                return p.getSale();
            }
        }
        return null;
    }

    private Long searchRemanis(String rainbowNomenclature) {
        for (ReportUploadPortal r : remanismarvel) {
            if (rainbowNomenclature.equals(r.getNomenclature())) {
                return r.getSale();
            }
        }
        return null;
    }

    public Object articleImeiList(Date start, Date stop) {
        List<Sales> portals = salesRepositoriy.getSaleClassiferProviderImei(new java.sql.Date(start.getTime()), new java.sql.Date(stop.getTime()), marvelImei, rainbowNomenclature);
        List<ReportUploadPortal> reportUploadPortals = new ArrayList<>();

        for (MarvelClassifier m : marvelClassifierList) {
            for (Sales s : portals) {
                if (m.getRainbowNomenclature().equals(s.getNomenclature())) {
                    reportUploadPortals.add(new ReportUploadPortal(m.getManufacturersArticle(), s.getImeis()));
                }
            }
        }
        return reportUploadPortals;
    }

    public Object forRoma(Date start, Date stop, String poco) {
        List<ReportUploadPortal> reportUploadPortals = new ArrayList<>();
        portals1 = salesRepositoriy.getSaleClassiferRoma(new java.sql.Date(start.getTime()), new java.sql.Date(stop.getTime()), poco);
        remanismarvel1 = remainingPhonesMarwelServise.getRemaisRoma(poco);

        for (ReportUploadPortal s : portals1) {
            reportUploadPortals.add(new ReportUploadPortal(s.getNomenclature(), s.getSale(), searchRemanis1(s.getNomenclature())));
        }
        for (ReportUploadPortal s : remanismarvel1) {
            ReportUploadPortal carnet = reportUploadPortals.stream()
                    .filter(c -> c.getNomenclature().equals(s.getNomenclature()))
                    .findFirst().orElse(null);
            if (carnet == null) {
                reportUploadPortals.add(new ReportUploadPortal(s.getNomenclature(), 0L, s.getSale()));
            }
        }
        return reportUploadPortals;
    }

    private Long searchRemanis1(String rainbowNomenclature) {
        for (ReportUploadPortal r : remanismarvel1) {
            if (rainbowNomenclature.equals(r.getNomenclature())) {
                return r.getSale();
            }
        }
        return 0L;
    }


    public Object forRomaXiaomi(Date start, Date stop, String xiaomi) {
        List<ReportUploadPortal> reportUploadPortals = new ArrayList<>();
        portals11 = salesRepositoriy.getSaleClassiferRomaNoPoco(new java.sql.Date(start.getTime()), new java.sql.Date(stop.getTime()), xiaomi);
        remanismarvel11 = remainingPhonesMarwelServise.getRemaisRomaNoPoco(xiaomi);

        for (ReportUploadPortal s : portals11) {
            reportUploadPortals.add(new ReportUploadPortal(s.getNomenclature(), s.getSale(), searchRemanis11(s.getNomenclature())));
        }
        for (ReportUploadPortal s : remanismarvel11) {
            ReportUploadPortal carnet = reportUploadPortals.stream()
                    .filter(c -> c.getNomenclature().equals(s.getNomenclature()))
                    .findFirst().orElse(null);
            if (carnet == null) {
                reportUploadPortals.add(new ReportUploadPortal(s.getNomenclature(), 0L, s.getSale()));
            }
        }
        return reportUploadPortals;
    }

    private Long searchRemanis11(String rainbowNomenclature) {
        for (ReportUploadPortal r : remanismarvel11) {
            if (rainbowNomenclature.equals(r.getNomenclature())) {
                return r.getSale();
            }
        }
        return 0L;
    }

    public Object forRomaShares(Date start, Date stop) {
        List<ReportUploadPortal> reportUploadPortals = new ArrayList<>();
    List<String> phone = phoneRepositoriy.getPhoneList();
    for(String p:phone){
      Long sale = salesRepositoriy.getModelPhoneData(phoneRepositoriy.getModelListPhone(p),new java.sql.Date(start.getTime()),new java.sql.Date(stop.getTime()));
        reportUploadPortals.add(new ReportUploadPortal(p, sale, null,null,null));
    }


        return reportUploadPortals;
    }
}
