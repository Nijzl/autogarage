package nl.lnijzink.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    public Invoice(){}

}
