package com.myisu_1.isu.models.RTK;

public enum DistribModelRTK {
    InternalLov("Видеокамера внутрення Low", null),
    InternalMiddle("Видеокамера внутренняя Middle", null),
    InternalHigh("Видеокамера внутренняя High", null),
    ExternalDome("Видеокамера внешняя купольная", null),
    ExternalCylindrical("Видеокамера внешняя цилиндрическая", null),
    ExternalHigh("Видеокамера внешняя High", null),
    ExternalWiFi("Видеокамера внешняя WiFi", null),
    IPTVbox("Приставка IPTV", null),
    MiniColumn("Умная колонка Мини", null),
    CapsuleColumn("Умная колонка Капсула", null),
    RostelecomRouter("Роутер Ростелеком", null),
    PowerInjector("Инжектор питания для видеокамер", null),
    GameController("выведен из матрицы", null);

   final String modelRTK;
    String cluster;

    DistribModelRTK(String modelRTK, String cluster) {
        this.modelRTK = modelRTK;
        this.cluster = cluster;
    }

    public String getModelRTK() {
        return modelRTK;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }


}



