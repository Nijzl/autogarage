package nl.lnijzink.autogarage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Appointment")
@Table(name = "appointments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String date;
    String time;
    String type;

/*    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "licence_plate", referencedColumnName = "licencePlate")
    private Car car;*/

    public Appointment(String date, String time, String type){
        this.date = date;
        this.time = time;
        this.type = type;
    }

}