package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import nl.lnijzink.autogarage.model.Car;

import javax.validation.constraints.*;
import java.util.Collection;

public class CustomerDto {
    @NotNull
    private long id;

    @NotNull
    @Size(min = 3, max = 250)
    private String fullName;

    @NotNull
    @Size(min = 3, max = 100)
    private String address;

    @NotNull
    @Email
    private String email;


    public CustomerDto(){}

    public CustomerDto(long id, String fullName, String address, String email){
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
    }


    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    public String getFullName(){return fullName;}

    public void setFullName(String fullName){this.fullName = fullName;}

    public String getAddress(){return address;}

    public void setAddress(String address){this.address = address;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
}