package nl.lnijzink.autogarage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "Car")
@Table(name = "cars")
@Getter
@Setter
public class Car {

    @Id
    @NotNull
    String licencePlate;
    String brand;
    String model;
    Integer year;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer owner;

    // relatie tussen 1 customer en 1 car
    @OneToMany(mappedBy= "owner")
    @JsonIgnore
    private Collection<Car> cars = new ArrayList<Car>();

/*    @OneToOne(mappedBy = "id")
    private Appointment appointment;*/

    public Car(){}

    public Car(String licencePlate, String brand, String model, Integer year){
        this.licencePlate = licencePlate;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

}


