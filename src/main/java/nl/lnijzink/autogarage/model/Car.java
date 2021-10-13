package nl.lnijzink.autogarage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String licencePlate;
    String model;
    int year;

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
}


