package nl.lnijzink.autogarage.model;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String fullName;
    String address;
    String email;

    public Customer() {}

    public Customer(String fullName, String address, String email) {
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

}
