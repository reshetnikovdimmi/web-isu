package com.myisu_1.isu.models;




import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.SIM.SaleSim_1m;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Phone_Smart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Matrix_T2;
    private String Brend;
    
    private String Model;
    private String Model_GB;
    private String Phone;


    @OneToMany(targetEntity = RemanisSim.class, cascade = CascadeType.ALL)

    @JoinColumn(name = "nameSimAndModem",referencedColumnName = "Model")

    private List<RemanisSim> remanisSims;

    @OneToMany(targetEntity = SaleSim_1m.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "nameSimAndModem",referencedColumnName = "Model")

    private List<SaleSim_1m> saleSim_1m;

    @OneToMany(targetEntity = SaleSim_6m.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "nameSimAndModem",referencedColumnName = "Model")

    private List<SaleSim_6m> saleSim_6m;


    public Phone_Smart(int id, String matrix_T2, String brend, String model, String model_GB, String phone, List<RemanisSim> remanisSims) {
        this.id = id;
        Matrix_T2 = matrix_T2;
        Brend = brend;
        Model = model;
        Model_GB = model_GB;
        Phone = phone;
        this.remanisSims = remanisSims;
    }

    public Phone_Smart(String model, List<RemanisSim> remanisSims) {
        Model = model;
        this.remanisSims = remanisSims;
    }

    public Phone_Smart(String matrix_T2, String brend, String model, String model_GB, String phone) {
        Matrix_T2 = matrix_T2;
        Brend = brend;
        Model = model;
        Model_GB = model_GB;
        Phone = phone;
    }

    public Phone_Smart() {
    }

    public Phone_Smart(int id, String matrix_T2, String brend, String model, String model_GB, String phone) {
        this.id = id;
        Matrix_T2 = matrix_T2;
        Brend = brend;
        Model = model;
        Model_GB = model_GB;
        Phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatrix_T2() {
        return Matrix_T2;
    }

    public void setMatrix_T2(String matrix_T2) {
        Matrix_T2 = matrix_T2;
    }
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
    public String getBrend() {
        return Brend;
    }

    public void setBrend(String brend) {
        Brend = brend;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getModel_GB() {
        return Model_GB;
    }

    public void setModel_GB(String model_GB) {
        Model_GB = model_GB;
    }
}
