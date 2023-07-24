package com.myisu_1.isu.models.accessories;

import com.myisu_1.isu.models.retail_price;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity
public class RemanisAccessories implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String shop;
    public String nameAccessories;
    public int remainsAccessories;

    @OneToOne(cascade = CascadeType.REFRESH)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "nameAccessories",
            referencedColumnName = "name",
            insertable = false, updatable = false,
            foreignKey = @javax.persistence
                    .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    public retail_price prices;
}
