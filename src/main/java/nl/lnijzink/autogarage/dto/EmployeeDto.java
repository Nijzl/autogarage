package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class EmployeeDto {
    @NotNull
    private long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 100)
    private String role;

    public EmployeeDto(){}

    public EmployeeDto(long id, String name, String role){
        this.id = id;
        this.name = name;
        this.role = role;
    }

}