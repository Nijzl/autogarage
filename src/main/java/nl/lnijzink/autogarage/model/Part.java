package nl.lnijzink.autogarage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Part {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    String name;
    float price;
    long quantity;

    public Part() {}

    public Part(String name, float price, long quantity) {
    }

    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public float getPrice(){return price;}

    public void setPrice(float price){this.price = price;}

    public long getQuantity() {return quantity;}

    public void setQuantity(long quantity) {this.quantity = quantity;}






}
