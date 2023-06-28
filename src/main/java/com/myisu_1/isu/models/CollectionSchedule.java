package com.myisu_1.isu.models;


import lombok.*;


import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CollectionSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String shop;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;

    public CollectionSchedule(String shop, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday) {
        this.shop = shop;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }
}
