package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.ReportUploadPortal;
import com.myisu_1.isu.models.Marwel.MarvelClassifier;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.models.bonuses.CheckingLists;
import com.myisu_1.isu.models.retail_price;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarwelPromoServise implements CheckingLists {
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
    @Autowired
    private PriceRepositoriy priceRepositoriy;

    List<ReportUploadPortal> portals;
    List<ReportUploadPortal> remanismarvel;
    List<String> marvelImei;
    List<String> rainbowNomenclature;
    List<MarvelClassifier> marvelClassifierList;
    List<ReportUploadPortal> reportUploadPortals;


    public List<String> noPhone() {
        List<String> a = remanisSimRepository.getRemainsSimAndModem();
        List<String> b = phoneRepositoriy.getModelList();
        b.addAll(buttonsPhoneRepositoriy.getModelsButton());
        b.addAll(simAndRtkTableRepositoriy.getNameRainbows());

        return aMinusB(a,b );
    }

    public List<String> NoClassifier() {
        List<String> a = phoneRepositoriy.getModelListXiaomi();
        List<String> b = marvelClassifierRepositoriy.getRainbowNomenclature();

        return aMinusB(a,b );
    }

    public List<ReportUploadPortal> reportUploadPortal(Date start, Date stop) {
        reportUploadPortals = new ArrayList<>();
        marvelImei = suppliersRepositoriy.getListSuppliersProvider("МАРВЕЛ КТ ООО");
        rainbowNomenclature = marvelClassifierRepositoriy.getRainbowNomenclature();

        remanismarvel = remainingPhonesMarwelServise.getRemaisImeiMarvel(marvelImei, rainbowNomenclature);
        marvelClassifierList = marvelClassifierRepositoriy.findAll();
        portals = salesRepositoriy.getSaleClassiferProvider(new java.sql.Date(start.getTime()), new java.sql.Date(stop.getTime()), marvelImei, rainbowNomenclature);

        for (MarvelClassifier m : marvelClassifierList) {
            Long remanis = searchSale(m.getRainbowNomenclature(),remanismarvel);
            Long sale = searchSale(m.getRainbowNomenclature(),portals);
            if (remanis != 0L && sale != 0L) {
                reportUploadPortals.add(new ReportUploadPortal(m.getManufacturersArticle(), m.getName(), sale, remanis));
            }
            if (remanis == 0L && sale != 0L) {
                reportUploadPortals.add(new ReportUploadPortal(m.getManufacturersArticle(), m.getName(), sale, 0L));
            }
            if (remanis != 0L && sale == 0L) {
                reportUploadPortals.add(new ReportUploadPortal(m.getManufacturersArticle(), m.getName(), 0L, remanis));
            }

        }

        return reportUploadPortals;
    }

    private Long searchSale(String rainbowNomenclature, List<ReportUploadPortal> portals) {
        for (ReportUploadPortal p : portals) {
            if (rainbowNomenclature.equals(p.getNomenclature())) {
                return p.getSale();
            }
        }
        return 0L;
    }


    public Object articleImeiList(Date start, Date stop) {
        List<Sales> portals = salesRepositoriy.getSaleClassiferProviderImei(new java.sql.Date(start.getTime()), new java.sql.Date(stop.getTime()), marvelImei, rainbowNomenclature);
        reportUploadPortals = new ArrayList<>();
        for (MarvelClassifier m : marvelClassifierList) {
            for (Sales s : portals) {
                if (m.getRainbowNomenclature().equals(s.getNomenclature())) {
                    reportUploadPortals.add(new ReportUploadPortal(m.getManufacturersArticle(), s.getImeis()));
                }
            }
        }
        return reportUploadPortals;
    }

    public Object forRomaPoco(Date start, Date stop, String poco) {
        reportUploadPortals = new ArrayList<>();
        portals = salesRepositoriy.getSaleClassiferRoma(new java.sql.Date(start.getTime()), new java.sql.Date(stop.getTime()), poco);
        remanismarvel = remainingPhonesMarwelServise.getRemaisRoma(poco);
        return forRomaXiaomiPoco();
    }

    public Object forRomaXiaomi(Date start, Date stop, String xiaomi) {
        reportUploadPortals = new ArrayList<>();
        portals = salesRepositoriy.getSaleClassiferRomaNoPoco(new java.sql.Date(start.getTime()), new java.sql.Date(stop.getTime()), xiaomi);
        remanismarvel = remainingPhonesMarwelServise.getRemaisRomaNoPoco(xiaomi);
         return forRomaXiaomiPoco();
    }

    public Object forRomaXiaomiPoco() {
        reportUploadPortals = new ArrayList<>();

        for (ReportUploadPortal s : portals) {
            reportUploadPortals.add(new ReportUploadPortal(s.getNomenclature(), s.getSale(), searchSale(s.getNomenclature(),remanismarvel)));
        }
        for (ReportUploadPortal s : remanismarvel) {
            ReportUploadPortal carnet = reportUploadPortals.stream()
                    .filter(c -> c.getNomenclature().equals(s.getNomenclature()))
                    .findFirst().orElse(null);
            if (carnet == null) {
                reportUploadPortals.add(new ReportUploadPortal(s.getNomenclature(), 0L, s.getSale()));
            }
        }
        return reportUploadPortals;
    }

    public Object forRomaShares(Date start, Date stop) {
        portals = new ArrayList<>();
        reportUploadPortals = new ArrayList<>();
        List<String> phone = phoneRepositoriy.getPhoneList();
        List<retail_price> price = priceRepositoriy.findAll();
        Long sumsht = 0L;
        Long sumrub = 0L;
        for (String p : phone) {
            List<String> modelPhone = phoneRepositoriy.getModelListPhone(p);
            List<String> sale = salesRepositoriy.getModelPhoneData(modelPhone, new java.sql.Date(start.getTime()), new java.sql.Date(stop.getTime()));
            Long sht = Long.valueOf(sale.size());
            sumsht += sht;
            Long rub = price(sale,price);
            sumrub += rub;
            portals.add(new ReportUploadPortal(p, sht, rub, null, null));
        }
        for (ReportUploadPortal p : portals) {

            reportUploadPortals.add(new ReportUploadPortal(p.getName(), p.getSale(), p.getRemanis(), String.format("%.2f",(double)p.getSale()/(double)sumsht*100), String.format("%.2f",(double)p.getRemanis()/(double)sumrub*100)));
        }
        return reportUploadPortals;
    }

    private Long price(List<String> sale, List<retail_price> price) {
        Long prices = 0L;
        for (String s : sale) {
            for (retail_price p : price) {
                 if (p.getName().equals(s)) {
                    prices += p.getPriceInt();
                }
            }
        }
        return prices;
    }
}
