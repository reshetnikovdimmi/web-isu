package com.myisu_1.isu.models.SIM;

import com.myisu_1.isu.models.RTK.MatrixRTK;
import com.myisu_1.isu.models.authorization_tt;

import java.util.List;

public abstract class SimList {

    List<SimSvod> simSvodList;
    List<SaleSim_6m> saleSim_6ms;
    List<SaleSim_1m> saleSim_1ms;
    List<RemanisSim> remanisSimList;
    List<authorization_tt> authorization_ttList;
    List<SimAndRtkTable> simAndRtkTables;
    List<MatrixRTK> matrixRTKList;
    List<ShopPlanSim> shopPlanSimsList;

    public List<ShopPlanSim> getShopPlanSimsList() {
        return shopPlanSimsList;
    }

    public void setShopPlanSimsList(List<ShopPlanSim> shopPlanSimsList) {
        this.shopPlanSimsList = shopPlanSimsList;
    }

    public abstract Iterable<SimSvod> parse(String shop, String t2);
    public abstract void parse2();


    public List<MatrixRTK> getMatrixRTKList() {
        return matrixRTKList;
    }

    public void setMatrixRTKList(List<MatrixRTK> matrixRTKList) {
        this.matrixRTKList = matrixRTKList;
    }
    public List<SimSvod> getSimSvodList() {
        return simSvodList;
    }

    public void setSimSvodList(List<SimSvod> simSvodList) {
        this.simSvodList = simSvodList;
    }

    public List<SaleSim_6m> getSaleSim_6ms() {
        return saleSim_6ms;
    }

    public void setSaleSim_6ms(List<SaleSim_6m> saleSim_6ms) {
        this.saleSim_6ms = saleSim_6ms;
    }

    public List<SaleSim_1m> getSaleSim_1ms() {
        return saleSim_1ms;
    }

    public void setSaleSim_1ms(List<SaleSim_1m> saleSim_1ms) {
        this.saleSim_1ms = saleSim_1ms;
    }

    public List<RemanisSim> getRemanisSimList() {
        return remanisSimList;
    }

    public void setRemanisSimList(List<RemanisSim> remanisSimList) {
        this.remanisSimList = remanisSimList;
    }

    public List<authorization_tt> getAuthorization_ttList() {
        return authorization_ttList;
    }

    public void setAuthorization_ttList(List<authorization_tt> authorization_ttList) {
        this.authorization_ttList = authorization_ttList;
    }

    public List<SimAndRtkTable> getSimAndRtkTables() {
        return simAndRtkTables;
    }

    public void setSimAndRtkTables(List<SimAndRtkTable> simAndRtkTables) {
        this.simAndRtkTables = simAndRtkTables;
    }
}
