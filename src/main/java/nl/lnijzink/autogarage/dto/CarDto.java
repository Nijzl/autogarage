package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import javax.validation.constraints.*;

public class CarDto {

    @NotNull
    @Size(min = 3, max = 100)
    private String licencePlate;

    @NotNull
    @Size(min = 3, max = 100)
    private String model;

    @NotNull
    private int year;

    public CarDto(){}

    public CarDto(String licencePlate, String model, int year){
        this.licencePlate = licencePlate;
        this.model = model;
        this.year = year;
    }

    public String getLicencePlate(){return licencePlate;}

    public void setLicencePlate(String licencePlate){this.licencePlate = licencePlate;}

    public String getModel(){return model;}

    public void setModel(String model){this.model = model;}

    public int getYear() {return year;}

    public void setYear(int year) {this.year = year;}
}