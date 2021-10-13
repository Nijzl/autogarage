package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import javax.validation.constraints.*;

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

    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getRole(){return role;}

    public void setRole(String role){this.role = role;}


}