package nl.lnijzink.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;

    public Car(){}

    public Car(String licencePlate, String brand, String model, Integer year){
        this.licencePlate = licencePlate;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

}


