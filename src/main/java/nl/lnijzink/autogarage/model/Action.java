package nl.lnijzink.autogarage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Action")
@Table(name = "actions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Action {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String name;
    String description;
    Double price;


    public Action(String name, String description, Double price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
