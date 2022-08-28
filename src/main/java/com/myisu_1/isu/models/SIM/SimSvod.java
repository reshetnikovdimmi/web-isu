package com.myisu_1.isu.models.SIM;

public class SimSvod {
    private int id;
    private int idPlan;
    private String nameSim;
    private String sale_6;
    private String sale;
    private String remanis;
    private String remanisRarus;
    private int plan;
    private String planVypol;
    private String shop;
    private String view;
    private String toOrder;
    private String distribution;
    private String remanisSkladSIM;
    private String remanisSIM;
    private String averageSalesSIM;
    private String recommendedToOrder;

    public String getRecommendedToOrder() {
        return recommendedToOrder;
    }

    public void setRecommendedToOrder(String recommendedToOrder) {
        this.recommendedToOrder = recommendedToOrder;
    }

    public String getAverageSalesSIM() {
        return averageSalesSIM;
    }

    public void setAverageSalesSIM(String averageSalesSIM) {
        this.averageSalesSIM = averageSalesSIM;
    }

    public String getRemanisSIM() {
        return remanisSIM;
    }

    public void setRemanisSIM(String remanisSIM) {
        this.remanisSIM = remanisSIM;
    }

    public String getRemanisSkladSIM() {
        return remanisSkladSIM;
    }

    public void setRemanisSkladSIM(String remanisSkladSIM) {
        this.remanisSkladSIM = remanisSkladSIM;
    }

    public SimSvod(int id, int idPlan, String nameSim, String sale_6, String sale, String remanis, String remanisRarus, int plan, String planVypol, String shop, String view, String toOrder, String distribution) {
        this.id = id;
        this.idPlan = idPlan;
        this.nameSim = nameSim;
        this.sale_6 = sale_6;
        this.sale = sale;
        this.remanis = remanis;
        this.remanisRarus = remanisRarus;
        this.plan = plan;
        this.planVypol = planVypol;
        this.shop = shop;
        this.view = view;
        this.toOrder = toOrder;
        this.distribution = distribution;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getToOrder() {
        return toOrder;
    }

    public void setToOrder(String toOrder) {
        this.toOrder = toOrder;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public SimSvod() {
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getNameSim() {
        return nameSim;
    }

    public void setNameSim(String nameSim) {
        this.nameSim = nameSim;
    }

    public String getSale_6() {
        return sale_6;
    }

    public void setSale_6(String sale_6) {
        this.sale_6 = sale_6;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getRemanis() {
        return remanis;
    }

    public void setRemanis(String remanis) {
        this.remanis = remanis;
    }

    public String getRemanisRarus() {
        return remanisRarus;
    }

    public void setRemanisRarus(String remanisRarus) {
        this.remanisRarus = remanisRarus;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public String getPlanVypol() {
        return planVypol;
    }

    public void setPlanVypol(String planVypol) {
        this.planVypol = planVypol;
    }
}
