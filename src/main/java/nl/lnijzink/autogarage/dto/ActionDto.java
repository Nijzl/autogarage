package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class ActionDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 250)
    private String description;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("1.0e6")
    private Float price;

    public ActionDto(){}

    public ActionDto(Long id, String name, String description, Float price){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
