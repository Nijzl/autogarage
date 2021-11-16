package nl.lnijzink.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.lnijzink.autogarage.model.Car;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentDto {

    @NotNull
    private Long id;

    @NotNull
    private String date;

    @NotNull
    private String time;

    private String type;

    private Car car;

}
