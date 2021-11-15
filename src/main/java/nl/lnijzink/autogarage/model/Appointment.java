package nl.lnijzink.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Appointment")
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String date;
    String time;

/*    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "licence_plate", referencedColumnName = "licencePlate")
    private Car car;*/

    public Appointment(){}

    public Appointment(String date, String time){
        this.date = date;
        this.time = time;
    }

}