package com.myisu_1.isu.models.accessories;

import com.myisu_1.isu.models.retail_price;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
@ToString
@Entity
public class RangeAccessories implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String accessoriesName;
    private String accessoriesCategory;
    boolean tele2;
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "accessoriesName",
            referencedColumnName = "name",
            insertable = false, updatable = false,
            foreignKey = @javax.persistence
                    .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    public retail_price prices;

    public RangeAccessories() {
    }

    public RangeAccessories(int id, String accessoriesName, String accessoriesCategory, boolean tele2) {
        this.id = id;
        this.accessoriesName = accessoriesName;
        this.accessoriesCategory = accessoriesCategory;
        this.tele2 = tele2;
    }

    public RangeAccessories(String accessoriesName, String accessoriesCategory, boolean tele2) {
        this.accessoriesName = accessoriesName;
        this.accessoriesCategory = accessoriesCategory;
        this.tele2 = tele2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessoriesName() {
        return accessoriesName;
    }

    public void setAccessoriesName(String accessoriesName) {
        this.accessoriesName = accessoriesName;
    }

    public String getAccessoriesCategory() {
        return accessoriesCategory;
    }

    public void setAccessoriesCategory(String accessoriesCategory) {
        this.accessoriesCategory = accessoriesCategory;
    }

    public boolean isTele2() {
        return tele2;
    }

    public void setTele2(boolean tele2) {
        this.tele2 = tele2;
    }
}
