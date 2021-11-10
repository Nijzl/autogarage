package nl.lnijzink.autogarage.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AppointmentDto {
    @NotNull
    private Long id;

    @NotNull
    private String date;

    public AppointmentDto(){}

    public AppointmentDto(Long id, String date){
        this.id = id;
        this.date = date;
    }

}
