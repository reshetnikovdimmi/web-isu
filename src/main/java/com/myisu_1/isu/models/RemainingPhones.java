package com.myisu_1.isu.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class RemainingPhones implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String Nomenclature;
    int Cash;
    int Sim;
    int CashT2;
    int SevernayaT2;
    int BagrationT2;
    int BeethovenT2;
    int ZavertyaevaT2;
    int ZyvaevskDIV;
    int ZyvaevskT2;
    int NeftezavodskayaT2;
    int BolsherechyeT2;
    int ZnamenskoyeMTS;
    int ZnamenskoyeMegaFon;
    int KrutinkaT2;
    int Krutinka;
    int MoskalenkiMTS;
    int MoskalenkiT2;
    int OdessaDIV;
    int BolsherechyeMTS;
    int BigUkiT2;
    int ZnamenskoeT2;
    int LuzinoT2;
    int MaryanovkaDIV;
    int MaryanovkaT2;
    int MuromtsevoT2;
    int MuromtsevoMTS;
    int TavricheskoeT2;
    int TavricheskoeMTS;
    int TaraT2;
    int SherbakulBee;
    int KolosovkaT2;
    int ZyvaevskMTS;
    int SedelnikovoT2;
    int TevrizT2;
    int UstIshimT2;

    public RemainingPhones() {
    }

    public RemainingPhones(String nomenclature, int cash, int sim, int cashT2, int severnayaT2, int bagrationT2, int beethovenT2, int zavertyaevaT2, int zyvaevskDIV, int zyvaevskT2, int neftezavodskayaT2, int bolsherechyeT2, int znamenskoyeMTS, int znamenskoyeMegaFon, int krutinkaT2, int krutinka, int moskalenkiMTS, int moskalenkiT2, int odessaDIV, int bolsherechyeMTS, int bigUkiT2, int znamenskoeT2, int luzinoT2, int maryanovkaDIV, int maryanovkaT2, int muromtsevoT2, int muromtsevoMTS, int tavricheskoeT2, int tavricheskoeMTS, int taraT2, int sherbakulBee, int kolosovkaT2, int zyvaevskMTS, int sedelnikovoT2, int tevrizT2, int ustIshimT2) {
        Nomenclature = nomenclature;
        Cash = cash;
        Sim = sim;
        CashT2 = cashT2;
        SevernayaT2 = severnayaT2;
        BagrationT2 = bagrationT2;
        BeethovenT2 = beethovenT2;
        ZavertyaevaT2 = zavertyaevaT2;
        ZyvaevskDIV = zyvaevskDIV;
        ZyvaevskT2 = zyvaevskT2;
        NeftezavodskayaT2 = neftezavodskayaT2;
        BolsherechyeT2 = bolsherechyeT2;
        ZnamenskoyeMTS = znamenskoyeMTS;
        ZnamenskoyeMegaFon = znamenskoyeMegaFon;
        KrutinkaT2 = krutinkaT2;
        Krutinka = krutinka;
        MoskalenkiMTS = moskalenkiMTS;
        MoskalenkiT2 = moskalenkiT2;
        OdessaDIV = odessaDIV;
        BolsherechyeMTS = bolsherechyeMTS;
        BigUkiT2 = bigUkiT2;
        ZnamenskoeT2 = znamenskoeT2;
        LuzinoT2 = luzinoT2;
        MaryanovkaDIV = maryanovkaDIV;
        MaryanovkaT2 = maryanovkaT2;
        MuromtsevoT2 = muromtsevoT2;
        MuromtsevoMTS = muromtsevoMTS;
        TavricheskoeT2 = tavricheskoeT2;
        TavricheskoeMTS = tavricheskoeMTS;
        TaraT2 = taraT2;
        SherbakulBee = sherbakulBee;
        KolosovkaT2 = kolosovkaT2;
        ZyvaevskMTS = zyvaevskMTS;
        SedelnikovoT2 = sedelnikovoT2;
        TevrizT2 = tevrizT2;
        UstIshimT2 = ustIshimT2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomenclature() {
        return Nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        Nomenclature = nomenclature;
    }

    public int getCash() {
        return Cash;
    }

    public void setCash(int cash) {
        Cash = cash;
    }

    public int getSim() {
        return Sim;
    }

    public void setSim(int sim) {
        Sim = sim;
    }

    public int getCashT2() {
        return CashT2;
    }

    public void setCashT2(int cashT2) {
        CashT2 = cashT2;
    }

    public int getSevernayaT2() {
        return SevernayaT2;
    }

    public void setSevernayaT2(int severnayaT2) {
        SevernayaT2 = severnayaT2;
    }

    public int getBagrationT2() {
        return BagrationT2;
    }

    public void setBagrationT2(int bagrationT2) {
        BagrationT2 = bagrationT2;
    }

    public int getBeethovenT2() {
        return BeethovenT2;
    }

    public void setBeethovenT2(int beethovenT2) {
        BeethovenT2 = beethovenT2;
    }

    public int getZavertyaevaT2() {
        return ZavertyaevaT2;
    }

    public void setZavertyaevaT2(int zavertyaevaT2) {
        ZavertyaevaT2 = zavertyaevaT2;
    }

    public int getZyvaevskDIV() {
        return ZyvaevskDIV;
    }

    public void setZyvaevskDIV(int zyvaevskDIV) {
        ZyvaevskDIV = zyvaevskDIV;
    }

    public int getZyvaevskT2() {
        return ZyvaevskT2;
    }

    public void setZyvaevskT2(int zyvaevskT2) {
        ZyvaevskT2 = zyvaevskT2;
    }

    public int getNeftezavodskayaT2() {
        return NeftezavodskayaT2;
    }

    public void setNeftezavodskayaT2(int neftezavodskayaT2) {
        NeftezavodskayaT2 = neftezavodskayaT2;
    }

    public int getBolsherechyeT2() {
        return BolsherechyeT2;
    }

    public void setBolsherechyeT2(int bolsherechyeT2) {
        BolsherechyeT2 = bolsherechyeT2;
    }

    public int getZnamenskoyeMTS() {
        return ZnamenskoyeMTS;
    }

    public void setZnamenskoyeMTS(int znamenskoyeMTS) {
        ZnamenskoyeMTS = znamenskoyeMTS;
    }

    public int getZnamenskoyeMegaFon() {
        return ZnamenskoyeMegaFon;
    }

    public void setZnamenskoyeMegaFon(int znamenskoyeMegaFon) {
        ZnamenskoyeMegaFon = znamenskoyeMegaFon;
    }

    public int getKrutinkaT2() {
        return KrutinkaT2;
    }

    public void setKrutinkaT2(int krutinkaT2) {
        KrutinkaT2 = krutinkaT2;
    }

    public int getKrutinka() {
        return Krutinka;
    }

    public void setKrutinka(int krutinka) {
        Krutinka = krutinka;
    }

    public int getMoskalenkiMTS() {
        return MoskalenkiMTS;
    }

    public void setMoskalenkiMTS(int moskalenkiMTS) {
        MoskalenkiMTS = moskalenkiMTS;
    }

    public int getMoskalenkiT2() {
        return MoskalenkiT2;
    }

    public void setMoskalenkiT2(int moskalenkiT2) {
        MoskalenkiT2 = moskalenkiT2;
    }

    public int getOdessaDIV() {
        return OdessaDIV;
    }

    public void setOdessaDIV(int odessaDIV) {
        OdessaDIV = odessaDIV;
    }

    public int getBolsherechyeMTS() {
        return BolsherechyeMTS;
    }

    public void setBolsherechyeMTS(int bolsherechyeMTS) {
        BolsherechyeMTS = bolsherechyeMTS;
    }

    public int getBigUkiT2() {
        return BigUkiT2;
    }

    public void setBigUkiT2(int bigUkiT2) {
        BigUkiT2 = bigUkiT2;
    }

    public int getZnamenskoeT2() {
        return ZnamenskoeT2;
    }

    public void setZnamenskoeT2(int znamenskoeT2) {
        ZnamenskoeT2 = znamenskoeT2;
    }

    public int getLuzinoT2() {
        return LuzinoT2;
    }

    public void setLuzinoT2(int luzinoT2) {
        LuzinoT2 = luzinoT2;
    }

    public int getMaryanovkaDIV() {
        return MaryanovkaDIV;
    }

    public void setMaryanovkaDIV(int maryanovkaDIV) {
        MaryanovkaDIV = maryanovkaDIV;
    }

    public int getMaryanovkaT2() {
        return MaryanovkaT2;
    }

    public void setMaryanovkaT2(int maryanovkaT2) {
        MaryanovkaT2 = maryanovkaT2;
    }

    public int getMuromtsevoT2() {
        return MuromtsevoT2;
    }

    public void setMuromtsevoT2(int muromtsevoT2) {
        MuromtsevoT2 = muromtsevoT2;
    }

    public int getMuromtsevoMTS() {
        return MuromtsevoMTS;
    }

    public void setMuromtsevoMTS(int muromtsevoMTS) {
        MuromtsevoMTS = muromtsevoMTS;
    }

    public int getTavricheskoeT2() {
        return TavricheskoeT2;
    }

    public void setTavricheskoeT2(int tavricheskoeT2) {
        TavricheskoeT2 = tavricheskoeT2;
    }

    public int getTavricheskoeMTS() {
        return TavricheskoeMTS;
    }

    public void setTavricheskoeMTS(int tavricheskoeMTS) {
        TavricheskoeMTS = tavricheskoeMTS;
    }

    public int getTaraT2() {
        return TaraT2;
    }

    public void setTaraT2(int taraT2) {
        TaraT2 = taraT2;
    }

    public int getSherbakulBee() {
        return SherbakulBee;
    }

    public void setSherbakulBee(int sherbakulBee) {
        SherbakulBee = sherbakulBee;
    }

    public int getKolosovkaT2() {
        return KolosovkaT2;
    }

    public void setKolosovkaT2(int kolosovkaT2) {
        KolosovkaT2 = kolosovkaT2;
    }

    public int getZyvaevskMTS() {
        return ZyvaevskMTS;
    }

    public void setZyvaevskMTS(int zyvaevskMTS) {
        ZyvaevskMTS = zyvaevskMTS;
    }

    public int getSedelnikovoT2() {
        return SedelnikovoT2;
    }

    public void setSedelnikovoT2(int sedelnikovoT2) {
        SedelnikovoT2 = sedelnikovoT2;
    }

    public int getTevrizT2() {
        return TevrizT2;
    }

    public void setTevrizT2(int tevrizT2) {
        TevrizT2 = tevrizT2;
    }

    public int getUstIshimT2() {
        return UstIshimT2;
    }

    public void setUstIshimT2(int ustIshimT2) {
        UstIshimT2 = ustIshimT2;
    }
}
