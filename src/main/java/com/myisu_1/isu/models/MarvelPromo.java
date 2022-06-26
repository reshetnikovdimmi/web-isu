package com.myisu_1.isu.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
    public class MarvelPromo implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private String PromoCode;
        @Temporal(TemporalType.DATE)
        private Date StartPromo;
        @Temporal(TemporalType.DATE)
        private Date EndPromo;
        private String ArticleNumber;
        private int Vision;
        private int NewVision;
        private int Discount;
        private int Compensation;
        @Temporal(TemporalType.DATE)
        private Date Collecting;
        private String Status;

    public MarvelPromo(int id, String promoCode, Date collecting, String status) {
        this.id = id;
        PromoCode = promoCode;
        Collecting = collecting;
        Status = status;
    }

    public MarvelPromo(int id, String promoCode, Date startPromo, Date endPromo, String articleNumber, int vision, int newVision, int discount, int compensation, Date collecting, String status) {
        this.id = id;
        PromoCode = promoCode;
        StartPromo = startPromo;
        EndPromo = endPromo;
        ArticleNumber = articleNumber;
        Vision = vision;
        NewVision = newVision;
        Discount = discount;
        Compensation = compensation;
        Collecting = collecting;
        Status = status;
    }

    public MarvelPromo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromoCode() {
        return PromoCode;
    }

    public void setPromoCode(String promoCode) {
        PromoCode = promoCode;
    }

    public Date getStartPromo() {
        return StartPromo;
    }

    public void setStartPromo(Date startPromo) {
        StartPromo = startPromo;
    }

    public Date getEndPromo() {
        return EndPromo;
    }

    public void setEndPromo(Date endPromo) {
        EndPromo = endPromo;
    }

    public String getArticleNumber() {
        return ArticleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        ArticleNumber = articleNumber;
    }

    public int getVision() {
        return Vision;
    }

    public void setVision(int vision) {
        Vision = vision;
    }

    public int getNewVision() {
        return NewVision;
    }

    public void setNewVision(int newVision) {
        NewVision = newVision;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        Discount = discount;
    }

    public int getCompensation() {
        return Compensation;
    }

    public void setCompensation(int compensation) {
        Compensation = compensation;
    }

    public Date getCollecting() {
        return Collecting;
    }

    public void setCollecting(Date collecting) {
        Collecting = collecting;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
