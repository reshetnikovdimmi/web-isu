package com.myisu_1.isu.models.ClothesForPhones.Glass;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Data
@Entity
public class ClothingMatchingTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String viewClothes;
    private String nameClothes;
    private String phoneClothes;

     @OneToMany(targetEntity = ClothesForPhonesRemanis.class, cascade = CascadeType.ALL)

    @JoinColumn(name = "namesClothes",referencedColumnName = "phoneClothes", updatable = false, insertable = false)
     @Column(updatable = false, insertable = false, unique = true)
    private List<ClothesForPhonesRemanis> clothersPhone;

    @OneToMany(targetEntity = ClothesForPhonesSale6.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "nameClothes",referencedColumnName = "phoneClothes", updatable = false, insertable = false)
    @Column(updatable = false, insertable = false)
    private List<ClothesForPhonesSale6> clothersSale6;

    @OneToMany(targetEntity = ClothesForPhonesSale1.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "nameClothes",referencedColumnName = "phoneClothes" )
    @Column(updatable = false, insertable = false)
    private List<ClothesForPhonesSale1> clothersSale1;

    public ClothingMatchingTable(String viewClothes, String nameClothes, String phoneClothes) {
        this.viewClothes = viewClothes;
        this.nameClothes = nameClothes;
        this.phoneClothes = phoneClothes;
    }

    public ClothingMatchingTable(int id, String viewClothes, String nameClothes, String phoneClothes, List<ClothesForPhonesRemanis> clothersPhone) {
        this.id = id;
        this.viewClothes = viewClothes;
        this.nameClothes = nameClothes;
        this.phoneClothes = phoneClothes;
        this.clothersPhone = clothersPhone;
    }

    public ClothingMatchingTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getViewClothes() {
        return viewClothes;
    }

    public void setViewClothes(String viewClothes) {
        this.viewClothes = viewClothes;
    }

    public String getNameClothes() {
        return nameClothes;
    }

    public void setNameClothes(String nameClothes) {
        this.nameClothes = nameClothes;
    }

    public String getPhoneClothes() {
        return phoneClothes;
    }

    public void setPhoneClothes(String phoneClothes) {
        this.phoneClothes = phoneClothes;
    }

    public List<ClothesForPhonesRemanis> getClothersPhone() {
        return clothersPhone;
    }

    public void setClothersPhone(List<ClothesForPhonesRemanis> clothersPhone) {
        this.clothersPhone = clothersPhone;
    }
}
