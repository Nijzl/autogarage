package nl.lnijzink.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentDto {

    @NotNull
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    private Date date;

}
