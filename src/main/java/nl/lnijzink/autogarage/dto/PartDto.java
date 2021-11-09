package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class PartDto {
    @NotNull
    private long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("1.0e6")
    private float price;

    @NotNull
    @Min(1)
    @Max((long) 1.0e6)
    private long quantity;

    public PartDto(){}

    public PartDto(long id, String name, float price, long quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
