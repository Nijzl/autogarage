package nl.lnijzink.autogarage.dto;

import com.sun.istack.NotNull;
import javax.validation.constraints.*;

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

    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public float getPrice(){return price;}

    public void setPrice(float price){this.price = price;}

    public long getQuantity() {return quantity;}

    public void setQuantity(long quantity) {this.quantity = quantity;}

}
