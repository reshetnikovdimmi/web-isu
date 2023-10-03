package com.myisu_1.isu.models.Phone;

import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.SaleSim_1m;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.retail_price;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class ButtonsPhone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private String brend;
    private String group;



    @OneToOne(cascade = CascadeType.REFRESH)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "model",referencedColumnName = "name",
            insertable = false, updatable = false,
            foreignKey = @javax.persistence
                    .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    public retail_price prices;

    @OneToMany(targetEntity = RemanisSim.class, cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "nameSimAndModem", referencedColumnName = "model", insertable = false, updatable = false)

    private List<RemanisSim> remanisSims;

    @OneToMany(targetEntity = SaleSim_1m.class, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "nameSimAndModem",referencedColumnName = "model", insertable = false, updatable = false)

    private List<SaleSim_1m> saleSim_1m;

    @OneToMany(targetEntity = SaleSim_6m.class, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "nameSimAndModem",referencedColumnName = "model", insertable = false, updatable = false)

    private List<SaleSim_6m> saleSim_6m;
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
