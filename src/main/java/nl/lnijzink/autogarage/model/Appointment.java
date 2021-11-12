package nl.lnijzink.autogarage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "Appointment")
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {

    @Id
    @NotNull
    Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    Date date;

/*    @OneToOne
    private WorkUnit workUnit;*/

    public Appointment(){}

    public Appointment(Date date){
        this.date = date;
    }

}