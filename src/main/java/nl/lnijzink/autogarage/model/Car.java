package nl.lnijzink.autogarage.model;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String licencePlate;
    String model;
    int year;

    @ManyToOne
    private Customer owner;

    public Car(){}

    public Car(String licencePlate, String model, int year){
        this.licencePlate = licencePlate;
        this.model = model;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {return year;}

    public void setYear(int year) {this.year = year;}

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}


