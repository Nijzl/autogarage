package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import javax.validation.constraints.*;

public class CustomerDto {
    @NotNull
    private long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 100)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 100)
    private String address;

    @NotNull
    @Email
    private String email;

    public CustomerDto(){}

    public CustomerDto(long id, String firstName, String lastName, String address, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }


    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    public String getFirstName(){return firstName;}

    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getLastName(){return lastName;}

    public void setLastName(String lastName){this.lastName = lastName;}

    public String getAddress(){return address;}

    public void setAddress(String address){this.address = address;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
}