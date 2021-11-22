package nl.lnijzink.autogarage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "Car")
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
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

    // relation between customer and car
    @OneToMany(mappedBy= "owner")
    @JsonIgnore
    private Collection<Car> cars = new ArrayList<Car>();

    @OneToMany(mappedBy = "id")
    private Collection<Appointment> appointment = new ArrayList<Appointment>();

    public Car(String licencePlate, String brand, String model, Integer year){
        this.licencePlate = licencePlate;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

}


