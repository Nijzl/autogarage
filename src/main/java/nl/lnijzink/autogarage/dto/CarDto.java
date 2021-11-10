package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class CarDto {

    @NotNull
    @Size(min = 3, max = 100)
    private String licencePlate;

    @NotNull
    @Size(min = 3, max = 100)
    private String brand;

    @NotNull
    @Size(min = 3, max = 100)
    private String model;

    @NotNull
    private Integer year;

    public CarDto(){}

    public CarDto(String licencePlate, String brand, String model, Integer year){
        this.licencePlate = licencePlate;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

}