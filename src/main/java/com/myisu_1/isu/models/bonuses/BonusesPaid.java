package com.myisu_1.isu.models.bonuses;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor

@Getter
@Setter
@ToString
@Entity
public class BonusesPaid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    public String model;
    @Temporal(TemporalType.DATE)
    public Date startPromo;
    @Temporal(TemporalType.DATE)
    public Date endPromo;
    public Integer amount;
    public String suppliers;

    public BonusesPaid(String model, Date startPromo, Date endPromo, Integer amount, String suppliers) {
        this.model = model;
        this.startPromo = startPromo;
        this.endPromo = endPromo;
        this.amount = amount;
        this.suppliers = suppliers;
    }

    public BonusesPaid(int id, String model, Date startPromo, Date endPromo, Integer amount, String suppliers) {
        this.id = id;
        this.model = model;
        this.startPromo = startPromo;
        this.endPromo = endPromo;
        this.amount = amount;
        this.suppliers = suppliers;
    }
}
