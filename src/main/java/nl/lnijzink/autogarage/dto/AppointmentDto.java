package nl.lnijzink.autogarage.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AppointmentDto {
    @NotNull
    private Integer id;

    @NotNull
    private String date;
}
