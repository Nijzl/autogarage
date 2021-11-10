package nl.lnijzink.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String fullName;
    String address;
    String phoneNumber;
    String email;

    // relatie tussen 1 customer en 1 car
    @OneToMany(mappedBy= "owner")
    private Collection<Car> cars = new ArrayList<Car>();


    //getters and setters
    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }

    // constructors
    public Customer(){}

    public Customer(String fullName, String address, String phoneNumber, String email){
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

}
