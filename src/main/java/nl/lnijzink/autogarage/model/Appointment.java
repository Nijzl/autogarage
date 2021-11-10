package nl.lnijzink.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "Appointment")
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {

    @Id
    @NotNull
    Long id;
    String date;

/*    @OneToOne
    private WorkUnit workUnit;*/

    public Appointment(){}

    public Appointment(String date){
        this.date = date;
    }

}