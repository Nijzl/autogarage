package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.lnijzink.autogarage.model.Car;

import javax.validation.constraints.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 250)
    private String fullName;

    @NotNull
    @Size(min = 3, max = 100)
    private String address;

    @NotNull
    @Size(min = 10, max = 11)
    private String phoneNumber;

    @NotNull
    @Email
    private String email;

}