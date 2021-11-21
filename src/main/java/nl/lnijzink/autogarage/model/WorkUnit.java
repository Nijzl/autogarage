package nl.lnijzink.autogarage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "WorkUnit")
@Table(name = "workUnits")
@NoArgsConstructor
@AllArgsConstructor
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

    private CustomerStatus customerStatus;

    private RepairStatus repairStatus;

    @OneToOne
    private Invoice invoice;

    public WorkUnit(Type type, Car car, String mechanic, CustomerStatus customerStatus, RepairStatus repairStatus) {
        this.type = type;
        this.car = car;
        this.mechanic = mechanic;
        this.customerStatus = customerStatus;
        this.repairStatus = repairStatus;
    }

}
