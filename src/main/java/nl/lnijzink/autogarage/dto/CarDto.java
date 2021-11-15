package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.lnijzink.autogarage.model.Appointment;
import nl.lnijzink.autogarage.model.Customer;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDto {

    @NotNull
    @Size(min = 3, max = 100)
    private String licencePlate;

    @NotNull
    @Size(min = 2, max = 100)
    private String brand;

    @NotNull
    @Size(min = 3, max = 100)
    private String model;

    @NotNull
    private Integer year;

    @NotNull
    private Customer owner;

/*    private Appointment appointment;*/

}