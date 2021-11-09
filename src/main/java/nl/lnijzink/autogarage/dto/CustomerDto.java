package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import nl.lnijzink.autogarage.model.Car;

import javax.validation.constraints.*;
import java.util.Collection;

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
    @Email
    private String email;


    public CustomerDto() {
    }

    public CustomerDto(long id, String fullName, String address, String email) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
    }
}