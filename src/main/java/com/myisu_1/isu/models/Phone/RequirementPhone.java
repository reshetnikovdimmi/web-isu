package com.myisu_1.isu.models.Phone;

public class RequirementPhone {
    String shop;
    String cluster;
    int remanis;
    int requirement;
    Double representation;
    Double percent;

    public RequirementPhone(String shop, String cluster, int remanis, int requirement, Double representation, double percent) {
        this.shop = shop;
        this.cluster = cluster;
        this.remanis = remanis;
        this.requirement = requirement;
        this.representation = representation;
        this.percent = percent;
    }

    public RequirementPhone() {
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public int getRemanis() {
        return remanis;
    }

    public void setRemanis(int remanis) {
        this.remanis = remanis;
    }

    public int getRequirement() {
        return requirement;
    }

    public void setRequirement(int requirement) {
        this.requirement = requirement;
    }

    public Double getRepresentation() {
        return representation;
    }

    public void setRepresentation(Double representation) {
        this.representation = representation;
    }
}
