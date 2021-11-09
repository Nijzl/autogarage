package nl.lnijzink.autogarage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class Workunit
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    Type type;

    @ManyToOne
    @JoinColumn(name = "car_licencePlate", referencedColumnName = "licencePlate")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "mechanic_id", referencedColumnName = "id")
    private Employee mechanic;

    @ManyToOne
    @Nullable
    @MapsId("id")
    @JoinColumn(name = "id")
    Action action;

    @OneToMany(mappedBy = "workunit")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    Collection<WorkUnitPart> workUnitParts;


    @OneToOne
    @Nullable
    @JoinColumn(name = "workunit_id", referencedColumnName = "id")
    private Workunit workunit;

    public Workunit() {
    }

    public Workunit(Type type, Car car, Employee mechanic) {
        this.type = type;
        this.car = car;
        this.mechanic = mechanic;
    }

}
