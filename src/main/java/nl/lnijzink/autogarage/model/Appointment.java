package nl.lnijzink.autogarage.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "Appointment")
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {
    @Id
    @NotNull
    Integer id;
    String date;
}