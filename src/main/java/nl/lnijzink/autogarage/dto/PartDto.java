package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class PartDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("1.0e6")
    private Float price;

    @NotNull
    @Min(1)
    @Max((long) 1.0e6)
    private Long quantity;

    public PartDto(){}

    public PartDto(Long id, String name, Float price, Long quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
