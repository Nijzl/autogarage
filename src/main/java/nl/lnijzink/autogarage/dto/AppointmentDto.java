package nl.lnijzink.autogarage.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class AppointmentDto {

    @NotNull
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    private Date date;

    public AppointmentDto(){}

    public AppointmentDto(Long id, Date date){
        this.id = id;
        this.date = date;
    }

}
