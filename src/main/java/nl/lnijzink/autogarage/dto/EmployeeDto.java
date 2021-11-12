package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class EmployeeDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 100)
    private String role;

    public EmployeeDto(){}

    public EmployeeDto(String name, String role){
        this.name = name;
        this.role = role;
    }

    public EmployeeDto(Long id, String name, String role){
        this.id = id;
        this.name = name;
        this.role = role;
    }

}