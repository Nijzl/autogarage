package nl.lnijzink.autogarage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
public class WorkUnit {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    Type type;
    String mechanic;


    @ManyToOne
    @JoinColumn(name = "car_licencePlate", referencedColumnName = "licencePlate")
    private Car car;

    @OneToMany(mappedBy = "workUnit")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    Collection<WorkUnitAction> workUnitActions;

    @OneToMany(mappedBy = "workUnit")
    @LazyCollection(LazyCollectionOption.FALSE)
//    @JsonIgnore
    Collection<WorkUnitPart> workUnitParts;

    private Boolean checkAgree;

    private Boolean repairPerformed;

//    @OneToOne
//    @Nullable
//    @JoinColumn(name = "workUnit_id", referencedColumnName = "id")
//    private WorkUnit workUnit;

    public WorkUnit(){}

    public WorkUnit(Type type, Car car, String mechanic, Boolean checkAgree, Boolean repairPerformed) {
        this.type = type;
        this.car = car;
        this.mechanic = mechanic;
        this.checkAgree = checkAgree;
        this.repairPerformed = repairPerformed;
    }

}
