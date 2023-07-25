package com.myisu_1.isu.models.bonuses;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
}
