package com.myisu_1.isu.models.Phone;

import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothesForPhonesSale6;
import com.myisu_1.isu.models.retail_price;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
public class ButtonsPhone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private String brend;


    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "model",
            referencedColumnName = "name",
            insertable = false, updatable = false,
            foreignKey = @javax.persistence
                    .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    public retail_price prices;



    public ButtonsPhone() {
    }

    public ButtonsPhone(String model, String brend) {
        this.model = model;
        this.brend = brend;
    }

    public ButtonsPhone(int id, String model, String brend) {
        this.id = id;
        this.model = model;
        this.brend = brend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrend() {
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }
}
