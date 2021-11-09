package nl.lnijzink.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Action")
@Table(name = "actions")
@Getter
@Setter
public class Action {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String name;
    String description;
    Float price;

    public Action(){}

    public Action(String name, String description, Float price){
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
