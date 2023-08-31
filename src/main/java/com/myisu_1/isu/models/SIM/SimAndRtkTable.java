package com.myisu_1.isu.models.SIM;

import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothesForPhonesRemanis;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothesForPhonesSale1;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothesForPhonesSale6;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class SimAndRtkTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String view;
    private String nameSpark;
    private String nameRarus;
    private String nameRainbow;
    private String distributionModel;
    private String toOrder;

    @OneToMany(targetEntity = ShopPlanSim.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "nameSimModem", referencedColumnName = "nameRainbow")

    private List<ShopPlanSim> shopPlanSims;

    @OneToMany(targetEntity = RemanisSim.class, cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "nameSimAndModem", referencedColumnName = "nameRainbow", insertable = false, updatable = false)

    private List<RemanisSim> remanisSims;

    @OneToMany(targetEntity = SaleSim_6m.class, cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "nameSimAndModem", referencedColumnName = "nameRainbow", insertable = false, updatable = false)

    private List<SaleSim_6m> saleSim6ms;

    @OneToMany(targetEntity = SaleSim_1m.class, cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "nameSimAndModem", referencedColumnName = "nameRainbow", insertable = false, updatable = false)

    private List<SaleSim_1m> saleSim1ms;

    public SimAndRtkTable(int id, String view, String nameSpark, String nameRarus, String nameRainbow, String distributionModel, String toOrder, List<ShopPlanSim> shopPlanSims, List<RemanisSim> remanisSims, List<SaleSim_6m> saleSim6ms, List<SaleSim_1m> saleSim1ms) {
        this.id = id;
        this.view = view;
        this.nameSpark = nameSpark;
        this.nameRarus = nameRarus;
        this.nameRainbow = nameRainbow;
        this.distributionModel = distributionModel;
        this.toOrder = toOrder;
        this.shopPlanSims = shopPlanSims;
        this.remanisSims = remanisSims;
        this.saleSim6ms = saleSim6ms;
        this.saleSim1ms = saleSim1ms;
    }

    public List<RemanisSim> getRemanisSims() {
        return remanisSims;
    }

    public void setRemanisSims(List<RemanisSim> remanisSims) {
        this.remanisSims = remanisSims;
    }

    public List<SaleSim_6m> getSaleSim6ms() {
        return saleSim6ms;
    }

    public void setSaleSim6ms(List<SaleSim_6m> saleSim6ms) {
        this.saleSim6ms = saleSim6ms;
    }

    public List<SaleSim_1m> getSaleSim1ms() {
        return saleSim1ms;
    }

    public void setSaleSim1ms(List<SaleSim_1m> saleSim1ms) {
        this.saleSim1ms = saleSim1ms;
    }

    public List<ShopPlanSim> getShopPlanSims() {
        return shopPlanSims;
    }

    public void setShopPlanSims(List<ShopPlanSim> shopPlanSims) {
        this.shopPlanSims = shopPlanSims;
    }

    public SimAndRtkTable(String view, String nameSpark, String nameRarus, String nameRainbow, String distributionModel, String toOrder) {
        this.view = view;
        this.nameSpark = nameSpark;
        this.nameRarus = nameRarus;
        this.nameRainbow = nameRainbow;
        this.distributionModel = distributionModel;
        this.toOrder = toOrder;
    }

    public SimAndRtkTable(int id, String view, String nameSpark, String nameRarus, String nameRainbow, String distributionModel, String toOrder) {
        this.id = id;
        this.view = view;
        this.nameSpark = nameSpark;
        this.nameRarus = nameRarus;
        this.nameRainbow = nameRainbow;
        this.distributionModel = distributionModel;
        this.toOrder = toOrder;
    }

    public SimAndRtkTable() {
    }

    public String getNameRainbow() {
        return nameRainbow;
    }

    public void setNameRainbow(String nameRainbow) {
        this.nameRainbow = nameRainbow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getNameSpark() {
        return nameSpark;
    }

    public void setNameSpark(String nameSpark) {
        this.nameSpark = nameSpark;
    }

    public String getNameRarus() {
        return nameRarus;
    }

    public void setNameRarus(String nameRarus) {
        this.nameRarus = nameRarus;
    }

    public String getDistributionModel() {
        return distributionModel;
    }

    public void setDistributionModel(String distributionModel) {
        this.distributionModel = distributionModel;
    }

    public String getToOrder() {
        return toOrder;
    }

    public void setToOrder(String toOrder) {
        this.toOrder = toOrder;
    }
}
